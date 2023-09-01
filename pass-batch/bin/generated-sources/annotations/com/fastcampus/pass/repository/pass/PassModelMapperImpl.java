package com.fastcampus.pass.repository.pass;

import javax.annotation.processing.Generated;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2023-09-01T17:04:39+0900",
    comments = "version: 1.5.2.Final, compiler: Eclipse JDT (IDE) 3.35.0.v20230721-1147, environment: Java 17.0.8 (Eclipse Adoptium)"
)
public class PassModelMapperImpl implements PassModelMapper {

    @Override
    public PassEntity toPassEntity(BulkPassEntity bulkPassEntity, String userId) {
        if ( bulkPassEntity == null && userId == null ) {
            return null;
        }

        PassEntity passEntity = new PassEntity();

        if ( bulkPassEntity != null ) {
            passEntity.setStatus( status( bulkPassEntity.getStatus() ) );
            passEntity.setRemainingCount( bulkPassEntity.getCount() );
            passEntity.setEndedAt( bulkPassEntity.getEndedAt() );
            passEntity.setPackageSeq( bulkPassEntity.getPackageSeq() );
            passEntity.setStartedAt( bulkPassEntity.getStartedAt() );
        }
        passEntity.setUserId( userId );

        return passEntity;
    }
}
