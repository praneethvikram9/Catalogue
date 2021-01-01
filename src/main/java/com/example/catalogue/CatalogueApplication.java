package com.example.catalogue;

import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.example.catalogue.domain.Product;
import com.example.catalogue.domain.User;
import com.example.catalogue.repository.ProductRepository;
import com.example.catalogue.repository.UserRepository;

@SpringBootApplication
@Configuration
@EntityScan
public class CatalogueApplication {
	
	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private ProductRepository productRepository;

	public static void main(String[] args) {
		SpringApplication.run(CatalogueApplication.class, args);
	}
	@Bean
	InitializingBean sendDatabase() {
	    return () -> {
	    	//initial users
	        userRepository.save(new User("pv@gmail.com","abcdef",24,"pv","M","",""));
	        userRepository.save(new User("pv1@gmail.com","abcdef1",24,"pv1","M","",""));
	        userRepository.save(new User("pv2@gmail.com","abcdef1",24,"pv2","M","",""));
	        userRepository.save(new User("pv3@gmail.com","abcdef1",24,"pv3","M","",""));
	        userRepository.save(new User("pv4@gmail.com","abcdef1",24,"pv4","M","",""));
	        userRepository.save(new User("pv5@gmail.com","abcdef1",24,"pv5","M","",""));
	        userRepository.save(new User("pv6@gmail.com","abcdef1",24,"pv6","M","",""));
	        userRepository.save(new User("pv7@gmail.com","abcdef1",24,"pv7","M","",""));
	        userRepository.save(new User("pv8@gmail.com","abcdef1",24,"pv8","M","",""));
	        userRepository.save(new User("pv9@gmail.com","abcdef1",24,"pv9","M","",""));
	        
	        //initial products
	        
	        productRepository.save(new Product(1,"Dell i5","Laptops",76500.00,4.3,100));
	        productRepository.save(new Product(2,"Lenovo i7","Laptops",56500.00,3.2,72));
	        productRepository.save(new Product(3,"Mac 512GB","Laptops",176500.00,4.7,97));
	        productRepository.save(new Product(4,"Ipad 256GB","Mobile & Tablets",26500.00,3.3,170));
	        productRepository.save(new Product(5,"iphone X 256GB","Mobile & Tablets",106500.00,4.9,200));
	        productRepository.save(new Product(6,"One plus 7t 128GB","Mobile & Tablets",36500.00,4.3,500));
	        productRepository.save(new Product(7,"One plus 7 pro 128GB","Mobile & Tablets",46500.00,4.3,270));
	        productRepository.save(new Product(8,"iphone 12","Mobile & Tablets",76000.00,3.3,69));
	        productRepository.save(new Product(9,"iphone x 64GB","Mobile & Tablets",66500.00,4.1,10));
	        productRepository.save(new Product(10,"One plus 7 pro 64GB","Mobile & Tablets",29500.00,4.01,180));
	        productRepository.save(new Product(11,"Puma nx hybrid size 10","Fashion",6500.00,4.5,100));
	        productRepository.save(new Product(12,"Puma nx hybrid size 9","Fashion",6300.00,4.3,150));
	        productRepository.save(new Product(13,"Nike walking shoes","Fashion",4500.00,4.05,200));
	        productRepository.save(new Product(14,"CK sweatshirt Yellow Size : L","Fashion",8500.00,4.75,50));
	        productRepository.save(new Product(15,"Dell i7 512GB SSD","Laptops",106500.00,3.75,10));
	        productRepository.save(new Product(16,"Dell i9 512GB SSD","Laptops",14500.00,4.25,60));	        
	      };
	   }

}
