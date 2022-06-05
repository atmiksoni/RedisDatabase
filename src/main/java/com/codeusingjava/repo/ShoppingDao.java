package com.codeusingjava.repo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Repository;

import com.codeusingjava.model.Shopping;

import java.util.List;

@Repository
public class ShoppingDao  {

    public static final String HASH_KEY = "Shopping";
    @Autowired
    private RedisTemplate template;

    public Shopping save(Shopping shopping){ 
    	
        template.opsForHash().put(HASH_KEY,shopping.getId(),shopping);
        return shopping;
    }

    public List<Shopping> findAll(){ 
    	System.out.println(" data coming from database");
        return template.opsForHash().values(HASH_KEY);
    }

    public Shopping findProductById(int id){
    	System.out.println("data coming from database");
        return (Shopping) template.opsForHash().get(HASH_KEY,id);
    }


    public String deleteProduct(int id){
         template.opsForHash().delete(HASH_KEY,id);
         System.out.println("item with id"+id+"deleted");
        return "shopping item deleted successfully !!";
    }
}
