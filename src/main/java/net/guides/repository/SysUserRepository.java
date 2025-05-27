package net.guides.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import net.guides.bean.entity.SysUser;

@Repository
public interface SysUserRepository extends JpaRepository<SysUser, String> {

}
