package net.guides.repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import net.guides.bean.entity.Employee;

@Repository
public interface EmployeeRepository extends JpaRepository<Employee, String>{
	
	@Query(value="select * from employees where id=? or first_name=?",nativeQuery=true)
	public  List<Employee>  searchByIdorName(String id,String name);
	
	@Query(value="select * from employees where id in (?1)",nativeQuery=true)
	public  List<Employee>  searchByIn(List<String> ids);
	
	@Query(value="select * from employees where first_name like ?% order by id",countQuery="select count(*) from employees where first_name like ?% order by id",nativeQuery=true)
	public  Page<Employee>  findByPage(String name,Pageable pageable);

}
