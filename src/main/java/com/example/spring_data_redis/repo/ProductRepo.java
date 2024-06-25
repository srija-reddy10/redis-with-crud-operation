package com.example.spring_data_redis.repo;

import com.example.spring_data_redis.enitity.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Repository;

import java.security.SecureRandom;
import java.util.List;

@Repository
public class ProductRepo {

    //first inject the redis temlete
    @Autowired
    private RedisTemplate template;

    //Method to save product through redis templete
    public static final String Hash_Key="Product";
    public Product save(Product product){
        template.opsForHash().put(Hash_Key,product.getId(),product);
        return product;
    }

    //Method to return all products
    public List<Product> findAll(){
        return template.opsForHash().values(Hash_Key);
    }

    //method to fetch product by id
    public Product findProductById(int id){
        return (Product) template.opsForHash().get(Hash_Key,id);
    }

    //method to delete the product by id
    public String deleteById(int id){
        template.opsForHash().delete(Hash_Key,id);
        return "Product removed !!";
    }
}
