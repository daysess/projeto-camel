package com.in28minutes.microservices.camel_microservice_a.routes;

import java.time.LocalDateTime;

import org.apache.camel.Exchange;
import org.apache.camel.Processor;
import org.apache.camel.builder.RouteBuilder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class MyFirstTimeRouter extends RouteBuilder{

    @Autowired
    private GetCurrentTimeBean getCurrentTimeBean;

    @Autowired
    private SimpleLoggingProcessingCompenent loggingComponent;

    @Override
    public void configure() throws Exception {
        // timer
        // transformation
        // log
        // Exchange[ExchangePattern: InOnly, BodyType: null, Body: [Body is null]]
        from("timer:first-timer")
         .transform().constant("My constant message")
        // .transform().constant("Time now is "+LocalDateTime.now())
        // .bean("getCurrentTimeBean")
        .log("${body}")
        .bean(loggingComponent)
        .log("${body}")

        // Processing
        // Transformation
        .bean(getCurrentTimeBean)
        .process(new SimpleLoggingProcessor())
        .to("log:first-timer");
    }
    
}

@Component
class GetCurrentTimeBean {
    public String getCurrentTime(){
        return "Time now is "+LocalDateTime.now();
    } 
}

@Component
class SimpleLoggingProcessingCompenent {
    private final Logger logger = LoggerFactory.getLogger(SimpleLoggingProcessingCompenent.class);
    public void process(String message){
        logger.info("SimpleLoggingProcessingCompenent {}", message);
    } 
}


class SimpleLoggingProcessor implements Processor {
    private final Logger logger = LoggerFactory.getLogger(SimpleLoggingProcessingCompenent.class);

    @Override
    public void process(Exchange exchange) throws Exception {
        logger.info("SimpleLoggingProcessor {}", exchange);
    }
    
}