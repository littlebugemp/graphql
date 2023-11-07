package com.limbo.graphql.component.fake;

import com.limbo.graphql.datasource.fake.FakeStockDataSource;
import com.limbo.graphql.generated.types.Stock;
import com.netflix.graphql.dgs.DgsComponent;
import com.netflix.graphql.dgs.DgsSubscription;
import org.reactivestreams.Publisher;
import org.springframework.beans.factory.annotation.Autowired;
import reactor.core.publisher.Flux;

import java.time.Duration;

@DgsComponent
public class FakeStockDataResolver {

    @Autowired
    FakeStockDataSource dataSource;

    @DgsSubscription
    public Publisher<Stock> randomStock(){
        return Flux.interval(Duration.ofSeconds(3))
                .map(t->dataSource.randomStock());
    }

}
