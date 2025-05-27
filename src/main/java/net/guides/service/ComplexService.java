package net.guides.service;

import java.util.List;

import org.hibernate.query.sql.internal.NativeQueryImpl;
import org.hibernate.transform.Transformers;
import org.springframework.stereotype.Service;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.Query;
import net.guides.dto.EmployeeQueryDTO;

@Service
public class ComplexService {

    @PersistenceContext
    private EntityManager entityManager;
    
    public void query() {
        
        String sql = "SELECT * FROM employees WHERE id = ? OR first_name like ?";
        Query  query = entityManager.createNativeQuery(sql, EmployeeQueryDTO.class);
        query.setParameter(1, "1");
        query.setParameter(2, "%a%");
        List<EmployeeQueryDTO> list = query.getResultList();

        for (EmployeeQueryDTO object : list) {
           System.out.println(object.getId());
           System.out.println(object.getFirstName());   
           System.out.println(object.getLastName());
        }
    }





        public void query1() {
        
        String sql = "SELECT * FROM employees WHERE id = :id OR first_name like :firstName";
        Query  query = entityManager.createNativeQuery(sql, EmployeeQueryDTO.class);
        query.setParameter("id", "1");
        query.setParameter("firstName", "%a%");
        
        
        List<EmployeeQueryDTO> list = query.getResultList();
        System.out.println(list.size());
        for (EmployeeQueryDTO object : list) {
           System.out.println(object.getId());
           System.out.println(object.getFirstName());   
           System.out.println(object.getLastName());
        }
    }
}
