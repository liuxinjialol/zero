package net;

import java.util.Arrays;
import java.util.Date;
import java.util.UUID;

import org.hibernate.query.Page;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.ApplicationContext;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

import net.guides.dto.ComplexDTO;
import net.guides.dto.SysUserCreateDTO;
import net.guides.dto.SysUserViewDTO;
import net.guides.repository.EmployeeRepository;
import net.guides.service.ComplexService;
import net.guides.service.SysUserService;

@SpringBootApplication
@EnableCaching
public class Application {
	
	public static void main(String[] args) {
		
		SpringApplication sa = new SpringApplication(Application.class);
		ApplicationContext context = sa.run(args);
		// Arrays.stream(context.getBeanDefinitionNames()).forEach(System.out::println);
		// Object object=context.getBean("transactionManager");
		// System.out.println(object.getClass().getName());

		// SysUserService  service = context.getBean(SysUserService.class);
		// SysUserCreateDTO sysUser = new SysUserCreateDTO();
		// sysUser.setId(UUID.randomUUID().toString());
		// sysUser.setLoginName("test");
		// sysUser.setLoginPassword("123456");
		// sysUser.setRealName("测试用户");
		// sysUser.setMobile("12345678901");
		// sysUser.setCreatedBy("admin");
		// sysUser.setUpdatedBy("admin");
		// sysUser.setStatus(1);
		// sysUser.setCreatedAt(new Date());
		// sysUser.setUpdatedAt(new Date());
		// SysUserViewDTO viewDTO=service.create(sysUser);
		// System.out.println(viewDTO);

		ComplexService service=context.getBean(ComplexService.class);
		// service.query1();
		// service.query2();
		service.query3();


		// EmployeeRepository  service = context.getBean(EmployeeRepository.class);
		// PageRequest pageable = PageRequest.of(0, 2);

		// org.springframework.data.domain.Page<ComplexDTO> result = service.findbyPage(pageable);
		// System.out.println("Total Elements: " + result.getTotalElements());
		// System.out.println("Total Pages: " + result.getTotalPages());
		// System.out.println("Page Number: " + result.getNumber());
		// System.out.println("Page Size: " + result.getSize());
		// System.out.println("Content:");
		// for (ComplexDTO dto : result.getContent()) {
		// 	System.out.println("UserId: " + dto.getUserId());
		// 	System.out.println("RealName: " + dto.getRealName());
		// 	System.out.println("LoginName: " + dto.getLoginName());
		// 	System.out.println("RoleId: " + dto.getRoleId());
		// 	System.out.println("RoleName: " + dto.getRoleName());
		// 	System.out.println("RoleCode: " + dto.getRoleCode());
		// }
	}
}
