package ru.kmetha.externalapi.dao.security;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.kmetha.externalapi.entity.security.AccountRole;

public interface AccountRoleDao extends JpaRepository<AccountRole, Long> {

}
