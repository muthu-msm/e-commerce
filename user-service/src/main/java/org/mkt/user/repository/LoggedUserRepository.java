package org.mkt.user.repository;

import org.mkt.user.model.LoggedUser;
import org.mkt.user.model.User;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface LoggedUserRepository extends CrudRepository<LoggedUser, Long> {

    Optional<LoggedUser> findByUserId(Long id);

    Optional<LoggedUser> findByToken(String token);
}
