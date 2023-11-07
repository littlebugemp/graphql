package com.limbo.graphql.component.fake;

import com.limbo.graphql.datasource.fake.FakeBookDataSource;
import com.limbo.graphql.datasource.fake.FakeHelloDataSource;
import com.limbo.graphql.generated.DgsConstants;
import com.limbo.graphql.generated.types.Hello;
import com.limbo.graphql.generated.types.HelloInput;
import com.netflix.graphql.dgs.DgsComponent;
import com.netflix.graphql.dgs.DgsData;
import com.netflix.graphql.dgs.DgsMutation;
import com.netflix.graphql.dgs.InputArgument;

import java.util.List;

@DgsComponent
public class FakeHelloMutation {

    @DgsMutation
    public int addHello(@InputArgument(name = "helloInput")HelloInput helloInput){
        var hello = Hello.newBuilder().text(helloInput.getText())
                .randomNumber(helloInput.getNumber())
                .build();
        FakeHelloDataSource.HELLO_LIST.add(hello);
        return FakeHelloDataSource.HELLO_LIST.size();
    }

//    @DgsMutation
    @DgsData(parentType = DgsConstants.MUTATION.TYPE_NAME, field = DgsConstants.MUTATION.ReplaceHelloText)
    public List<Hello> replaceHelloText(@InputArgument(name = "helloInput")HelloInput helloInput){
        FakeHelloDataSource.HELLO_LIST.stream()
                .filter(h->h.getRandomNumber() == helloInput.getNumber())
                .forEach(h->h.setText(helloInput.getText()));
        return FakeHelloDataSource.HELLO_LIST;
    }

    @DgsMutation
    public int deleteHello(int number){
        FakeHelloDataSource.HELLO_LIST.removeIf(h-> h.getRandomNumber() == number);
        return FakeHelloDataSource.HELLO_LIST.size();
    }
}
