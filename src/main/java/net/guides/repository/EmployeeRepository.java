package net.guides.repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.NativeQuery;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import net.guides.bean.entity.Employee;
import net.guides.dto.ComplexDTO;

@Repository
public interface EmployeeRepository extends JpaRepository<Employee, String>, JpaSpecificationExecutor<Employee> {

	@Query(value = "select * from employees where id=? or first_name=?", nativeQuery = true)
	public List<Employee> searchByIdorName(String id, String name);

	@Query(value = "select * from employees where id in (?1)", nativeQuery = true)
	public List<Employee> searchByIn(List<String> ids);

	@Query(value = "select * from employees where first_name like ?% order by id", countQuery = "select count(*) from employees where first_name like ?% order by id", nativeQuery = true)
	public Page<Employee> findByPage(String name, Pageable pageable);

	@NativeQuery(value = "SELECT u.id AS userId,u.real_name AS realName,u.login_name AS loginName,GROUP_CONCAT(r.id) AS roleId,GROUP_CONCAT(r.role_name) AS roleName,GROUP_CONCAT(r.role_code) AS roleCode FROM    sys_user u LEFT JOIN sys_user_role_rel ur ON u.id = ur.user_id  LEFT JOIN sys_role r ON ur.role_id = r.id  GROUP BY   u.id, u.real_name, u.login_name", countQuery = "SELECT u.id AS userId,u.real_name AS realName,u.login_name AS loginName,GROUP_CONCAT(r.id) AS roleId,GROUP_CONCAT(r.role_name) AS roleName,GROUP_CONCAT(r.role_code) AS roleCode FROM    sys_user u LEFT JOIN sys_user_role_rel ur ON u.id = ur.user_id  LEFT JOIN sys_role r ON ur.role_id = r.id  GROUP BY   u.id, u.real_name, u.login_name")
	public Page<ComplexDTO> findbyPage(Pageable pageable);

}
