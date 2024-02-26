package com.gussoft.farmaciabe.core.repository;

import com.gussoft.farmaciabe.core.models.Role;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoleRepository extends JpaRepository<Role, Long> {

    Optional<Role> findByAuthority(String auth);

}
