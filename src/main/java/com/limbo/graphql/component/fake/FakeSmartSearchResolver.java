package com.limbo.graphql.component.fake;

import com.limbo.graphql.datasource.fake.FakeBookDataSource;
import com.limbo.graphql.datasource.fake.FakeHelloDataSource;
import com.limbo.graphql.generated.DgsConstants;
import com.limbo.graphql.generated.types.SmartSearchResult;
import com.netflix.graphql.dgs.DgsComponent;
import com.netflix.graphql.dgs.DgsData;
import com.netflix.graphql.dgs.InputArgument;
import org.apache.commons.lang3.StringUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@DgsComponent
public class FakeSmartSearchResolver {

    @DgsData(parentType = DgsConstants.QUERY_TYPE, field = DgsConstants.QUERY.SmartSearch)
    public List<SmartSearchResult> getSmartSearch(@InputArgument(name = "keyword")Optional<String> keyword){
        var smartSearchResultList = new ArrayList<SmartSearchResult>();

        if(keyword.isEmpty()){
            smartSearchResultList.addAll(FakeBookDataSource.BOOK_LIST);
            smartSearchResultList.addAll(FakeHelloDataSource.HELLO_LIST);
        }else{
            var keywordString = keyword.get();
            FakeHelloDataSource.HELLO_LIST.stream()
                    .filter(h-> StringUtils.containsIgnoreCase(h.getText(),keywordString))
                    .forEach(smartSearchResultList::add);
            FakeBookDataSource.BOOK_LIST.stream()
                    .filter(b-> StringUtils.containsIgnoreCase(b.getTitle(),keywordString))
                    .forEach(smartSearchResultList::add);
        }
        return smartSearchResultList;
    }


}
