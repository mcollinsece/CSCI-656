package com.bezkoder.spring.security.postgresql.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.bezkoder.spring.security.postgresql.models.ERole;
import com.bezkoder.spring.security.postgresql.models.Role;

@Repository
//Was <Role, Long>
public interface RoleRepository extends JpaRepository<Role, Integer> {
  Optional<Role> findByName(ERole name);
}
