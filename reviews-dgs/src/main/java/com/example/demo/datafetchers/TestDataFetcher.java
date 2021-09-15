package com.example.demo.datafetchers;

import com.example.demo.generated.DgsConstants;
import com.example.demo.generated.types.Show;
import com.example.demo.generated.types.Test;
import com.netflix.graphql.dgs.DgsComponent;
import com.netflix.graphql.dgs.DgsData;
import com.netflix.graphql.dgs.DgsEntityFetcher;
import java.rmi.dgc.DGC;
import java.util.ArrayList;
import java.util.List;

@DgsComponent
public class TestDataFetcher
{

   List<Show> shows = new ArrayList<>();

   TestDataFetcher() {
      this.shows.add(Show.newBuilder().id("1").build());
   }

   @DgsData(parentType = "Query", field = "test")
   public Test testFetcher () {
      return new Test.Builder().name("Adam").build();
   }

   @DgsData(parentType = "Test", field = "shows")
   public List<Show> showFetcher(){
      return this.shows;
   }
}
