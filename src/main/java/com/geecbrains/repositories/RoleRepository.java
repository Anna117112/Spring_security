package com.geecbrains.repositories;


import com.geecbrains.entities.Role;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;



@Repository
public interface RoleRepository extends CrudRepository<Role, Long> {
}
