package org.mkt.user.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;
import org.mkt.common.dto.AddressRequest;
import org.mkt.common.dto.AddressResponse;
import org.mkt.user.model.Address;

@Mapper
public interface AddressMapper {
    AddressMapper INSTANCE = Mappers.getMapper(AddressMapper.class);

    Address requestToEntity(AddressRequest request);

    AddressResponse entityToRequest(Address request);
}
