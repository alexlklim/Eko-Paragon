package com.alex.eko.paragon;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@EnableTransactionManagement
@EnableJpaAuditing
@EnableAspectJAutoProxy
@SpringBootApplication
public class EkoParagonApplication {

	public static void main(String[] args) {
		SpringApplication.run(EkoParagonApplication.class, args);
	}

}
