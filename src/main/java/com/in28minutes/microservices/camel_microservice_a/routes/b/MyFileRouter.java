package com.in28minutes.microservices.camel_microservice_a.routes.b;

import org.apache.camel.builder.RouteBuilder;
import org.springframework.stereotype.Component;

@Component
public class MyFileRouter extends RouteBuilder {

    @Override
    public void configure() throws Exception {
        // from("file:files/input")
        // .log("${body}")
        // .to("file:files/output");

        from("file:files/input")
        .routeId("Files-Input-Route")
        .choice()
            .when(simple("${file:ext} ends with 'xml'"))
                .log("XML FILE")
            .otherwise()
                .log("Not an XML FILE")
        .end()
        .log("${body}")
        .to("file:files/output");
    }
    
}
