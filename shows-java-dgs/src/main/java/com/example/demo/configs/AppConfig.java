package com.example.demo.configs;

import com.example.demo.datafetchers.ShowsDatafetcher;
import com.netflix.graphql.dgs.internal.DgsSchemaProvider;
import org.slf4j.event.Level;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import java.util.logging.Logger;

@Configuration
public class AppConfig
{

   private static Logger LOGGER = Logger.getLogger(AppConfig.class.getName());
   @Bean
   public String something(DgsSchemaProvider dgsSchemaProvider) {
      System.out.println("**************************************************");
      for (String key: dgsSchemaProvider.getEntityFetchers().keySet()) {
         System.out.println(key);
         System.out.println(dgsSchemaProvider.getEntityFetchers().get(key));
      }
      System.out.println("**************************************************");
      return "Hello";
   }
}
