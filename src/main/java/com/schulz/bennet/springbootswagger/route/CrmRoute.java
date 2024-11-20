package com.schulz.bennet.springbootswagger.route;

import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.component.jackson.JacksonDataFormat;
import org.apache.camel.model.dataformat.JsonLibrary;
import org.apache.camel.model.rest.RestBindingMode;
import org.apache.camel.model.rest.RestParamType;
import org.springframework.stereotype.Component;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.schulz.bennet.springbootswagger.model.Customer;

@Component
public class CrmRoute extends RouteBuilder {

  @Override
  public void configure() {

    restConfiguration().component("servlet").bindingMode(RestBindingMode.json);

    ObjectMapper objectMapper = new ObjectMapper();
    objectMapper.disable(SerializationFeature.FAIL_ON_EMPTY_BEANS);

    JacksonDataFormat jacksonDataFormat = new JacksonDataFormat();
    jacksonDataFormat.setObjectMapper(objectMapper);

    // created new customer
    rest("/crm").post("v1/customer_session").type(Customer.class).outType(Customer.class)
         .to("direct:postCrmCustomerSession");

    from("direct:postCrmCustomerSession")
        .routeId("postCrmCustomerSessionRoute")
        .setHeader("CamelHttpMethod", constant("POST"))
        .log(">> - ${body}")
        .marshal().json(JsonLibrary.Jackson)
        .to("http://localhost:5555/api/crm/customer_session?bridgeEndpoint=true")
        .unmarshal().json(JsonLibrary.Jackson)
        .end();
    
    rest("/crm").put("v1/customer_session/{customerId}")
        .param().name("customerId").type(RestParamType.path)
        .description("The id of the customer to update").dataType("string").endParam()
        .type(Customer.class).outType(Customer.class)
        .to("direct:putCrmCustomerSession");
    
    from("direct:putCrmCustomerSession")
        .routeId("putCrmCustomerSessionRoute")
        .setHeader("CamelHttpMethod", constant("PUT"))
        .log(">> - ${body}")
        .marshal().json(JsonLibrary.Jackson)
        .toD("http://localhost:5555/api/crm/customer_session/${header.customerId}?bridgeEndpoint=true")
        .unmarshal().json(JsonLibrary.Jackson)
        .end();
        

    /*
     * from("direct:forwardCustomerSession") .routeId("forwardCustomerSessionRoute")
     * .setHeader("CamelHttpMethod", constant("POST")) .log(">> - ${body}")
     * .to("http://localhost:5555/api/crm/customer_session?bridgeEndpoint=true")
     * .convertBodyTo(Customer.class) .marshal().json(JsonLibrary.Jackson);
     */

  }
}