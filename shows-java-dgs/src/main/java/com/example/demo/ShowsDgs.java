package com.example.demo;

import graphql.execution.instrumentation.tracing.TracingInstrumentation;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import graphql.execution.instrumentation.Instrumentation;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class ShowsDgs {

    private static ApplicationContext applicationContext;

    public static void main(String[] args) {
        applicationContext = SpringApplication.run(ShowsDgs.class, args);
        displayAllBeans();
    }

    public static void displayAllBeans() {
        String[] allBeanNames = applicationContext.getBeanDefinitionNames();
        for(String beanName : allBeanNames) {
            System.out.println(beanName);
        }
    }

    /**
     * If you wan to leverage Apollo Tracing, as supported by java-graphql, you can just create a bean of type {@link TracingInstrumentation}.
     * In this example we added a conditional property on the bean to enable/disable the Apollo Tracing.
     * Enabled by default, you can turn it off by setting `graphql.tracing.enabled=false` in your application properties.
     *
     * @see <a href="https://github.com/apollographql/apollo-tracing">Apollo Tracing</a>
     */
    @Bean
    @ConditionalOnProperty( prefix = "graphql.tracing", name = "enabled", matchIfMissing = true)
    public Instrumentation tracingInstrumentation(){
        return new TracingInstrumentation();
    }



}
