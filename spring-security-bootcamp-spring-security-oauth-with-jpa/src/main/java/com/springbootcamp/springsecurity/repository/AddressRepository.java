package com.springbootcamp.springsecurity.repository;

import com.springbootcamp.springsecurity.entity.Address;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface AddressRepository extends CrudRepository<Address,Long> {

    @Query(value = "select user_id from address a where a.user_id=:user_id",nativeQuery = true)
    Long fetchAddresses(@Param("user_id") Long user_id);
}
