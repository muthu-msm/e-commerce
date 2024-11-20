package org.mkt.user.repository;

import org.mkt.user.model.Address;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface AddressRepository extends CrudRepository<Address, Long> {

    Optional<Address> findByUserId(Long id);
}
