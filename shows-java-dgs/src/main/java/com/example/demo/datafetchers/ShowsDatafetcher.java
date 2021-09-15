package com.example.demo.datafetchers;

import com.example.demo.generated.client.ShowRepresentation;
import com.example.demo.generated.types.Show;
import com.netflix.graphql.dgs.DgsComponent;
import com.netflix.graphql.dgs.DgsData;
import com.netflix.graphql.dgs.DgsDefaultTypeResolver;
import com.netflix.graphql.dgs.DgsTypeResolver;
import com.netflix.graphql.dgs.context.DgsContext;
import com.netflix.graphql.dgs.DgsDataFetchingEnvironment;
import com.netflix.graphql.dgs.DgsEntityFetcher;
import com.netflix.graphql.dgs.internal.DgsSchemaProvider;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.*;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.stream.Collectors;

@DgsComponent
public class ShowsDatafetcher {

    private static Logger LOGGER = Logger.getLogger(ShowsDatafetcher.class.getName());

    @Autowired
    DgsSchemaProvider dgsSchemaProvider;

    List<Show> shows = new ArrayList<>();

    public ShowsDatafetcher() {
        this.shows.add(new Show("1","Stranger Things", 2016));
        this.shows.add(new Show("2","Ozark", 2017));
        this.shows.add(new Show("3","The Crown", 2016));
        this.shows.add(new Show("4","Dead to Me", 2019));
        this.shows.add(new Show("5","Orange is the New Black", 2013));
    }

    @DgsData(parentType = "Query", field = "shows")
    public List<Show> getShows() {
        System.out.println("**************************************************");
        for (String key: dgsSchemaProvider.getEntityFetchers().keySet()) {
            System.out.println(key);
            System.out.println(dgsSchemaProvider.getEntityFetchers().get(key));
        }
        System.out.println("**************************************************");
        return this.shows;
    }

    @DgsEntityFetcher(name = "Show")
    public Show getShow(Map<String, Object> cool) {
        System.out.println("**************************************************");
        // {__typename=Show, id=1}
        System.out.println(cool);
        System.out.println("**************************************************");

        return this.shows.get(0);
    }
}
