package com.limbo.graphql.component.fake;

import com.limbo.graphql.datasource.fake.FakePetDataSource;
import com.limbo.graphql.generated.DgsConstants;
import com.limbo.graphql.generated.types.Cat;
import com.limbo.graphql.generated.types.Dog;
import com.limbo.graphql.generated.types.Pet;
import com.limbo.graphql.generated.types.PetFilter;
import com.netflix.graphql.dgs.DgsComponent;
import com.netflix.graphql.dgs.DgsData;
import com.netflix.graphql.dgs.InputArgument;
import org.apache.commons.lang3.StringUtils;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@DgsComponent
public class FakePetDataResolver {

    @DgsData(parentType = DgsConstants.QUERY_TYPE, field = DgsConstants.QUERY.Pets)
    public List<Pet> getPets(@InputArgument(name = "petFilter", collectionType = PetFilter.class)
                             Optional<PetFilter> filter){

        if(filter.isEmpty()){
            return FakePetDataSource.PET_LIST;
        }

        return FakePetDataSource.PET_LIST.stream()
                .filter(pet-> this.matchFilter(filter.get(), pet)
                ).collect(Collectors.toList());
    }

    private boolean matchFilter(PetFilter filter, Pet pet){
        if(StringUtils.isBlank(filter.getPetType())){
            return true;
        }

        if(filter.getPetType().equalsIgnoreCase(Dog.class.getSimpleName())){
            return pet instanceof Dog;
        }else if(filter.getPetType().equalsIgnoreCase(Cat.class.getSimpleName())){
            return pet instanceof Cat;
        }else{
            return false;
        }

    }
}
