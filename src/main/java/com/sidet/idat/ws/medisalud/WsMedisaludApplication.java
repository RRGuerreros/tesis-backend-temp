package com.sidet.idat.ws.medisalud;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
//import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@SpringBootApplication
public class WsMedisaludApplication implements CommandLineRunner {

	public static void main(String[] args) {
		SpringApplication.run(WsMedisaludApplication.class, args);
	}
	
	@Override
	public void run(String... args) throws Exception {
		/*BCryptPasswordEncoder encode=new BCryptPasswordEncoder();
		String code = encode.encode("1234");
		System.out.println("code: " + code);*/
		
		//String json = "200,{dato1:'hola',dato2:'chau'},";
		
		
	}
}
