package com.example.BankincCardSystem;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class BankincCardSystemApplication {

	public static void main(String[] args) {
		SpringApplication.run(BankincCardSystemApplication.class, args);
	}
        
        @Bean
        public OpenAPI customOpenAPI(){
            return new OpenAPI()
                    .info(new Info()
                    .title("Bank Inc Card System")
                    .version("1.0")
                    .description("Prueba Técnica para la vacante Desarrollador Java"));
                    
        }

}
