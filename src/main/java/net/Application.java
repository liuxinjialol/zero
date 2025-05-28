package net;

import java.util.Date;
import java.util.UUID;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.ApplicationContext;
import net.guides.dto.SysUserCreateDTO;
import net.guides.dto.SysUserViewDTO;
import net.guides.service.ComplexService;
import net.guides.service.SysUserService;

@SpringBootApplication
@EnableCaching
public class Application {
	
	public static void main(String[] args) {
		
		SpringApplication sa = new SpringApplication(Application.class);
		ApplicationContext context = sa.run(args);
		// Arrays.stream(context.getBeanDefinitionNames()).forEach(System.out::println);

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
		service.query1();

	}
}
