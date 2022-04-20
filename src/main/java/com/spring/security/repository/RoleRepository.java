package com.spring.security.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.spring.security.model.Role;

@Repository
public interface RoleRepository extends JpaRepository<Role, Integer> {

}
