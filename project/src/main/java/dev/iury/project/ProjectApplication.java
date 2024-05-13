package dev.iury.project;

import dev.iury.project.model.Person;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.crypto.password.DelegatingPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.crypto.password.Pbkdf2PasswordEncoder;

import java.util.HashMap;
import java.util.Map;

@SpringBootApplication
public class ProjectApplication {

	public static void main(String[] args) {
		SpringApplication.run(ProjectApplication.class, args);


		 Map<String, PasswordEncoder> encoders = new HashMap<>();

		 Pbkdf2PasswordEncoder pbkdf2Encoder =
		 new Pbkdf2PasswordEncoder(
		 "", 8, 185000,
		 Pbkdf2PasswordEncoder.SecretKeyFactoryAlgorithm.PBKDF2WithHmacSHA256);

		 encoders.put("pbkdf2", pbkdf2Encoder);
		 DelegatingPasswordEncoder passwordEncoder = new DelegatingPasswordEncoder("pbkdf2", encoders);
		 passwordEncoder.setDefaultPasswordEncoderForMatches(pbkdf2Encoder);

		 String result1 = passwordEncoder.encode("coffee123");
		 System.out.println("My hash result1 " + result1);

	}

}
