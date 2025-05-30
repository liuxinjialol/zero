package net.guides.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import jakarta.persistence.criteria.Path;
import jakarta.persistence.criteria.Predicate;
import net.guides.bean.entity.Employee;
import net.guides.dto.EmployeeListViewDTO;
import net.guides.repository.EmployeeRepository;

@Service
public class EmployeeService {

	@Autowired
	EmployeeRepository repository;

	public List<Employee> searchByIdorName(String id, String name) {
		return repository.searchByIdorName(id, name);
	}

	@Transactional
	public void demo() {
		this.repository.deleteById("498");
		System.out.println(1 / 0);
		this.repository.deleteById("500");
	}

	public Page<Employee> spage() {
		Pageable pageable = PageRequest.of(1, 10);
		return this.repository.findByPage("zhang", pageable);
	}

	@Transactional
	public void delete(Employee employee) {
		this.repository.delete(employee);
	}

	public Optional<Employee> findById(String id) {
		return this.repository.findById(id);
	}

	@Transactional
	public Employee save(Employee employee) {
		this.repository.save(employee);
		return employee;
	}

	public List<Employee> findAll() {
		return this.repository.findAll();
	}

	public List<Employee> searchByIn(List<String> ids) {
		return this.repository.searchByIn(ids);
	}

	public List<Employee> findList(EmployeeListViewDTO queryDTO) {

		Specification<Employee> specification = (Specification<Employee>) (root, query, cb) -> {
	
			Path<String> path1 = root.get("id");
			Predicate p1 = cb.equal(path1, queryDTO.getId());

			Path<String> path2 = root.get("firstName");
			Predicate p2 = cb.like(path2, "%" + queryDTO.getFirstName() + "%");

			return cb.and(p1, p2);

		};
		return this.repository.findAll(specification);
	}


	public Page<Employee> findPage(EmployeeListViewDTO queryDTO, Pageable pageable) {

		Specification<Employee> specification = (Specification<Employee>) (root, query, cb) -> {
	
			Path<String> path1 = root.get("id");
			Predicate p1 = cb.equal(path1, queryDTO.getId());

			Path<String> path2 = root.get("firstName");
			Predicate p2 = cb.like(path2, "%" + queryDTO.getFirstName() + "%");

			return cb.and(p1, p2);

		};
		return this.repository.findAll(specification,pageable);

	}

}
