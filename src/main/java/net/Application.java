package net;

import java.util.Arrays;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.ApplicationContext;


@SpringBootApplication
@EnableCaching
public class Application {
	
	public static void main(String[] args) {
		
		SpringApplication sa = new SpringApplication(Application.class);
		ApplicationContext context = sa.run(args);
		// Arrays.stream(context.getBeanDefinitionNames()).forEach(System.out::println);
		
	}
}
