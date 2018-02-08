import net.logstash.logback.appender.LogstashTcpSocketAppender
import net.logstash.logback.encoder.LogstashEncoder
import net.logstash.logback.encoder.LoggingEventCompositeJsonEncoder
import net.logstash.logback.composite.loggingevent.*
scan("3 seconds")
/*to make dynamic reload work with spring boot, we need to include the spring boot logback configuration */
//include(resource="org/springframework/boot/logging/logback/base.xml")
/*instructions here:
http://stackoverflow.com/questions/33844580/how-do-i-change-log-level-in-runtime-without-restarting-spring-boot-application
unfortunately there is no include for groovy at the moment. when https://github.com/qos-ch/logback/pull/298 is merged,
we will have it.*/

//below is the configuration to use the console appender class to send log output to the console
appender("CONSOLE", ConsoleAppender) {
	
	encoder(net.logstash.logback.encoder.LogstashEncoder)
}
//root(INFO, ["CONSOLE"])*/

//configuration to send log output to logstash
/*appender("LOGSTASH", ch.qos.logback.core.rolling.RollingFileAppender) {
	file = "documentservice.log"
	encoder(net.logstash.logback.encoder.LogstashEncoder)
	rollingPolicy(ch.qos.logback.core.rolling.TimeBasedRollingPolicy) {
	   //what to do
        maxHistory = 30
        fileNamePattern = "documentservice.%d{yyyy-MM-dd}.log.zip"
    }
   
}*/
//appender("LOGSTASH", LogstashTcpSocketAppender) {
  //destination = "usmi-logstash.genworth.net:10514"
  //destination = "ad0163bbly:5044"
  //encoder(LogstashEncoder)
//}
root(INFO, ["CONSOLE"])