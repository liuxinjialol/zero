package net.guides.service;

import java.math.BigInteger;
import java.util.List;
import org.springframework.stereotype.Service;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.Query;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.JoinType;
import jakarta.persistence.criteria.Root;
import jakarta.persistence.criteria.Join;
import net.guides.bean.entity.SysUser;
import net.guides.bean.entity.SysUserRole;
import net.guides.dto.ComplexDTO;
import net.guides.dto.EmployeeListViewDTO;

@Service
public class ComplexService {

    @PersistenceContext
    private EntityManager entityManager;

    public void query() {

        String sql = "SELECT * FROM employees WHERE id = ? OR first_name like ?";
        Query query = entityManager.createNativeQuery(sql, EmployeeListViewDTO.class);
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
        Query query = entityManager.createNativeQuery(sql, EmployeeListViewDTO.class);
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

    public void query2() {

        String sql = """
                    SELECT u.id AS userId,u.real_name AS realName,u.login_name AS loginName,
                    GROUP_CONCAT(r.id) AS roleId,GROUP_CONCAT(r.role_name) AS roleName,GROUP_CONCAT(r.role_code) AS roleCode
                    FROM    sys_user u
                    LEFT JOIN sys_user_role ur ON u.id = ur.user_id
                    LEFT JOIN sys_role r ON ur.role_id = r.id
                    GROUP BY   u.id, u.real_name, u.login_name
                """;
        Query query = entityManager.createNativeQuery(sql, ComplexDTO.class);

        List<ComplexDTO> list = query.getResultList();
        for (ComplexDTO complexDTO : list) {
            System.out.println("UserId: " + complexDTO.getUserId());
            System.out.println("RealName: " + complexDTO.getRealName());
            System.out.println("LoginName: " + complexDTO.getLoginName());
            System.out.println("RoleIds: " + complexDTO.getRoleId());
            System.out.println("RoleNames: " + complexDTO.getRoleName());
            System.out.println("RoleCodes: " + complexDTO.getRoleCode());
            System.out.println("-----------------------------");
        }
        
    }

    public void query3() {

       String sql = """
                    SELECT u.id AS userId,u.real_name AS realName,u.login_name AS loginName,
                    GROUP_CONCAT(r.id) AS roleId,GROUP_CONCAT(r.role_name) AS roleName,GROUP_CONCAT(r.role_code) AS roleCode
                    FROM    sys_user u
                    LEFT JOIN sys_user_role ur ON u.id = ur.user_id
                    LEFT JOIN sys_role r ON ur.role_id = r.id
                    GROUP BY   u.id, u.real_name, u.login_name
                """;

        
        Query query1 = entityManager.createNativeQuery(sql, ComplexDTO.class);
        query1.setFirstResult(7);
        query1.setMaxResults(5);
       
        List<ComplexDTO> list = query1.getResultList();
        for (ComplexDTO complexDTO : list) {
            System.out.println("UserId: " + complexDTO.getUserId());
            System.out.println("RealName: " + complexDTO.getRealName());
            System.out.println("LoginName: " + complexDTO.getLoginName());
            System.out.println("RoleIds: " + complexDTO.getRoleId());
            System.out.println("RoleNames: " + complexDTO.getRoleName());
            System.out.println("RoleCodes: " + complexDTO.getRoleCode());
            System.out.println("-----------------------------");
        }

        String countSql = "SELECT COUNT(*) FROM ( " + sql + " ) AS countTable";
        Query query2 = entityManager.createNativeQuery(countSql);
        long max = (Long)query2.getSingleResult();
        System.out.println("Max Results: " + max);
    }




}
