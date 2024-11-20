package org.mkt.user.service;

import lombok.RequiredArgsConstructor;
import org.mkt.common.dto.AddressRequest;
import org.mkt.common.dto.AddressResponse;
import org.mkt.common.dto.Response;
import org.mkt.user.mapper.AddressMapper;
import org.mkt.user.model.Address;
import org.mkt.user.repository.AddressRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class AddressServiceImpl implements AddressService {

    private final AddressRepository addressRepository;
    public final PasswordEncoder passwordEncoder;

    @Override
    public Response<AddressResponse> create(AddressRequest request) {

        Address address = addressRepository.save(AddressMapper.INSTANCE.requestToEntity(request));

        return new Response<>(1, AddressMapper.INSTANCE.entityToRequest(address));
    }

    @Override
    public Response<AddressResponse> update(AddressRequest request) {
        Optional<Address> optionalUser = addressRepository.findById(request.getId());

        return optionalUser.map(value -> new Response<>(1, AddressMapper.INSTANCE.entityToRequest(
                addressRepository.save(AddressMapper.INSTANCE.requestToEntity(request)))))
                .orElseGet(() -> new Response<>(404, "Not found"));
    }


    @Override
    public Response<Long> delete(Long id) {
        Optional<Address> optionalUser = addressRepository.findById(id);

        return optionalUser.map(value -> {
                    optionalUser.get().setStatus("D");
                    addressRepository.save(optionalUser.get());
                    return new Response<>(1, id);
                })
                .orElseGet(() -> new Response<>(404, "Not found"));
    }

    @Override
    public Response<AddressResponse> get(Long id) {
        Optional<Address> order = addressRepository.findById(id);

        return order.map(value -> new Response<>(1, AddressMapper.INSTANCE.entityToRequest(value)))
                .orElseGet(() -> new Response<>(404, "Not found", null));

    }
}
