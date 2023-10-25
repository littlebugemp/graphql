package com.limbo.graphql;

import com.netflix.graphql.dgs.DgsQueryExecutor;
import org.junit.jupiter.api.Test;
import org.junit.platform.commons.util.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
public class FakeHelloDataResolverTest {

    @Autowired
    DgsQueryExecutor dgsQueryExecutor;

    @Test
    public void oneHelloTest(){
        var query = """
                {
                    oneHello{
                        text
                        randomNumber
                    }
                }
                """;
        String text = dgsQueryExecutor.executeAndExtractJsonPath(query,"data.oneHello.text");
        Integer randomNumber = dgsQueryExecutor.executeAndExtractJsonPath(query, "data.oneHello.randomNumber");

        assertFalse(StringUtils.isBlank(text));
        assertNotNull(randomNumber);
    }

    @Test
    public void allHellosTest(){
        var query = """
                { 
                    allHellos{
                        randomNumber
                        text
                    }
                }
                """;
        List<String> texts = dgsQueryExecutor.executeAndExtractJsonPath(
                query,"data.allHellos[*].text");
        List<Integer> randomNumbers = dgsQueryExecutor.executeAndExtractJsonPath(query,
                "data.allHellos[*].randomNumber");

        assertNotNull(texts);
        assertFalse(texts.isEmpty());
        assertNotNull(randomNumbers);
        assertEquals(texts.size(),randomNumbers.size());
    }
}
