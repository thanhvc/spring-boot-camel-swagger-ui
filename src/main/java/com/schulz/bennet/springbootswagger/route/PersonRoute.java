package com.schulz.bennet.springbootswagger.route;

import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.model.rest.RestBindingMode;
import org.springframework.stereotype.Component;

import com.schulz.bennet.springbootswagger.model.Person;

@Component
public class PersonRoute extends RouteBuilder {

    @Override
    public void configure() {
        restConfiguration().component("servlet").bindingMode(RestBindingMode.json);

        rest("/person").get().type(Person.class).outType(Person.class)
                .to("direct:talk");
        from("direct:talk")
                .process(exchange -> {
                    Person p = new Person();
                    p.setFirstname("Bennet");
                    p.setLastname("Schulz");
                    exchange.getIn().setBody(p);
                });
    }
}
