package com.example.demo;


import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@SpringBootApplication
public class DemoApplication {

	public static void main(String[] args) {
		SpringApplication.run(DemoApplication.class, args);
//		RestTemplate temp = new RestTemplate();
		
		//GET
		//ResponseEntity<String> entity = temp.getForEntity("http://localhost:8080/hello-world?name=eieiei", String.class);
		
		//POST
//		HttpHeaders headers = new HttpHeaders();
//		headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);
//
//		MultiValueMap<String, String> map= new LinkedMultiValueMap<String, String>();
//		map.add("name", "WASSUP");
//
//		HttpEntity<MultiValueMap<String, String>> request = new HttpEntity<MultiValueMap<String, String>>(map, headers);
//		ResponseEntity<String> entity = temp.postForEntity("http://localhost:8080/hello-custom", request, String.class);
//		System.out.println(entity.toString());
		
		
	}
	@Bean
    public WebMvcConfigurer corsConfigurer() {
        return new WebMvcConfigurer() {
            @Override
            public void addCorsMappings(CorsRegistry registry) {
                registry.addMapping("/greeting-javaconfig").allowedOrigins("http://localhost:9000");
            }
        };
    }
}
