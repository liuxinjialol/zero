package net.guides.service;

import java.util.List;
import org.springframework.stereotype.Service;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.Query;
import net.guides.dto.EmployeeListViewDTO;

@Service
public class ComplexService {

    @PersistenceContext
    private EntityManager entityManager;
    
    public void query() {
        
        String sql = "SELECT * FROM employees WHERE id = ? OR first_name like ?";
        Query  query = entityManager.createNativeQuery(sql, EmployeeListViewDTO.class);
        query.setParameter(1, "1");
        query.setParameter(2, "%a%");
        List<EmployeeListViewDTO> list = query.getResultList();

        for (EmployeeListViewDTO object : list) {
           System.out.println(object.getId());
           System.out.println(object.getFirstName());   
           System.out.println(object.getLastName());
        }
    }

    public void query1() {
        
        String sql = "SELECT * FROM employees WHERE id = :id OR first_name like :firstName";
        Query  query = entityManager.createNativeQuery(sql, EmployeeListViewDTO.class);
        query.setParameter("id", "1");
        query.setParameter("firstName", "%a%");
        
        List<EmployeeListViewDTO> list = query.getResultList();
        System.out.println(list.size());
        for (EmployeeListViewDTO object : list) {
           System.out.println(object.getId());
           System.out.println(object.getFirstName());   
           System.out.println(object.getLastName());
        }
    }
}
