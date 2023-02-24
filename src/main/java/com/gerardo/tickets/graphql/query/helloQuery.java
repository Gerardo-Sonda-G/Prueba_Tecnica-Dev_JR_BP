package com.gerardo.tickets.graphql.query;


import lombok.AllArgsConstructor;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.stereotype.Controller;

@Controller
@AllArgsConstructor
public class helloQuery  {
    @QueryMapping
    public String hello() {
        return "Hello World!";
    }
    @QueryMapping
    public String greeting(@Argument("name") String name) {
        return String.format("Hello, %s", name);
    }
}