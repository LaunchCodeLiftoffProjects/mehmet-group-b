package com.UberMassage.UberMassage.data;

import com.UberMassage.UberMassage.models.User;
import org.springframework.data.repository.CrudRepository;

public interface UserRepository extends CrudRepository<User, Integer> {

    User findByUsername(String username);
}
