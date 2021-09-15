package com.example.demo.datafetchers;

import com.example.demo.generated.types.Review;
import com.example.demo.generated.types.Show;
import com.netflix.graphql.dgs.DgsComponent;
import com.netflix.graphql.dgs.DgsData;
import com.netflix.graphql.dgs.context.DgsContext;
import com.netflix.graphql.dgs.DgsDataFetchingEnvironment;
import com.netflix.graphql.dgs.DgsEntityFetcher;


import java.util.*;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.stream.Collectors;

@DgsComponent
public class ReviewsDatafetcher {

    private static Logger LOGGER = Logger.getLogger(ReviewsDatafetcher.class.getName());

    Map<String, List<Review>> reviews = new HashMap<>();

    public ReviewsDatafetcher() {
        List<Review> review1 = new ArrayList<>();
        review1.add(new Review(5));
        review1.add(new Review(4));
        review1.add(new Review(5));
        reviews.put("1", review1);

        List<Review> review2 = new ArrayList<>();
        review2.add(new Review(3));
        review2.add(new Review(5));
        reviews.put("2", review2);
    }

    @DgsEntityFetcher(name = "Show")
    public Show movie(Map<String, Object> values) {
        return new Show((String) values.get("id"), null);
    }

    @DgsData(parentType = "Show", field = "reviews")
    public List<Review> reviewsFetcher(DgsDataFetchingEnvironment dataFetchingEnvironment)  {
        LOGGER.log(Level.INFO, "********************************");
        LOGGER.log(Level.INFO, "TESTING");
        LOGGER.log(Level.INFO, dataFetchingEnvironment.toString());
        LOGGER.log(Level.INFO, DgsContext.getRequestData(dataFetchingEnvironment).toString());
        LOGGER.log(Level.INFO, dataFetchingEnvironment.getVariables().toString());
        LOGGER.log(Level.INFO, dataFetchingEnvironment.getArguments().toString());

        // possibly a request param

        // LOGGER.log(Level.INFO, dataFetchingEnvironment.getContext);
        // LOGGER.log(Level.INFO, dataFetchingEnvironment.getVariables());
        Show show = dataFetchingEnvironment.getSource();
        LOGGER.log(Level.INFO, show.toString());
        LOGGER.log(Level.INFO, "********************************");
        return reviews.get(show.getId());
    }
}
