package com.example.spring_data_redis;

import com.example.spring_data_redis.enitity.Product;
import com.example.spring_data_redis.repo.ProductRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.*;

import java.security.PublicKey;
import java.util.List;

@SpringBootApplication
//using main class as controller
@RestController
@RequestMapping("/product")
public class SpringDataRedisApplication {

	@Autowired
	private ProductRepo repo;

	@PostMapping
	public Product save(@RequestBody Product product){
		return repo.save(product);
	}

	@GetMapping
	public List<Product> getAllProducts(){
		return repo.findAll();
	}

	@GetMapping("/{id}")
	public Product findProduct(@PathVariable int id){
		return repo.findProductById(id);
	}

	@DeleteMapping("/{id}")
	public String remove(@PathVariable int id){
		return repo.deleteById(id);
	}

	public static void main(String[] args) {
		SpringApplication.run(SpringDataRedisApplication.class, args);
	}

}
