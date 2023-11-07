package com.limbo.graphql.component.fake;

import com.limbo.graphql.generated.DgsConstants;
import com.netflix.graphql.dgs.DgsComponent;
import com.netflix.graphql.dgs.DgsData;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestParam;

@DgsComponent
public class FakeAdditionalOnRequestResolver {

    @DgsData(parentType = DgsConstants.QUERY_TYPE, field = DgsConstants.QUERY.AdditionalOnRequest)
    public String additionalOnRequest(
           @RequestHeader(name = "optionalHeader", required = false) String optionalHeader,
           @RequestHeader(name = "mandatoryHeader") String mandatoryHeader,
           @RequestParam(name = "optionalParam", required = false) String optionalParam,
           @RequestParam(name = "mandatoryParam") String mandatoryParam
    ){
        var sb = new StringBuilder();
        sb.append("Optional Header : "+optionalHeader);
        sb.append(",");
        sb.append("Mandatory Header : "+optionalHeader);
        sb.append(",");
        sb.append("Optional Param : "+optionalParam);
        sb.append(",");
        sb.append("Mandatory Param : "+mandatoryParam);
        return sb.toString();
    }

}
