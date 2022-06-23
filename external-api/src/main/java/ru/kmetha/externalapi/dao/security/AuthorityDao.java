package ru.kmetha.externalapi.dao.security;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.kmetha.externalapi.entity.security.Authority;

public interface AuthorityDao extends JpaRepository<Authority, Long> {

}
