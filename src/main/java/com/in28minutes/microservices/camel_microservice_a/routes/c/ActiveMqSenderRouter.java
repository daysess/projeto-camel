package com.in28minutes.microservices.camel_microservice_a.routes.c;

import org.apache.camel.builder.RouteBuilder;
import org.springframework.stereotype.Component;

@Component
public class ActiveMqSenderRouter extends RouteBuilder {

    @Override
    public void configure() throws Exception {
        //timer
        // from("timer:active-mq-timer?period=10000")
        // .log("${body}")
        // .transform().constant("My messege for Active MQ")
        // .log("${body}")
        // .to("activemq:my-activemq-queue");
        //queue

        from("file:files/json")
        .log("${body}")
        .to("activemq:my-activemq-queue")
        .to("file:files/output");

        from("file:files/xml")
        .log("${body}")
        .to("activemq:my-activemq-xml-queue")
        .to("file:files/output");
    }
    
}
