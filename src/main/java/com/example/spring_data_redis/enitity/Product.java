package com.example.spring_data_redis.enitity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.redis.core.RedisHash;

import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
//Product should be store as hash key value in redis for that we use redis annotation
@RedisHash("Product")
public class Product implements Serializable {

    @Id
    private int id;
    private String name;
    private int qty;
    private long price;
}
