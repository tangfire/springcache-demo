package com.itheima.controller;

import com.itheima.entity.User;
import com.itheima.mapper.UserMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CachePut;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/user")
@Slf4j
public class UserController {

    @Autowired
    private UserMapper userMapper;

    @PostMapping
//    @CachePut(cacheNames = "userCache",key = "#result.id") // 从返回值拿到key  -> key = "#result.id"
//    @CachePut(cacheNames = "userCache",key = "#p0.id") // 从第一个参数中拿到key -> key = "#p0.id"
//    @CachePut(cacheNames = "userCache",key = "#a0.id") // 从第一个参数中拿到key -> key = "#a0.id"
//    @CachePut(cacheNames = "userCache",key = "#root.args[0].id") // 从第一个参数中拿到key -> key = "#root.args[0].id"
    @CachePut(cacheNames = "userCache",key = "#user.id") // 如果使用Spring Cache缓存数据,key的生成:userCache::user.id  从参数中拿到key -> key = "#user.id"
    public User save(@RequestBody User user){
        userMapper.insert(user);
        return user;
    }

    @DeleteMapping
    public void deleteById(Long id){
        userMapper.deleteById(id);
    }

	@DeleteMapping("/delAll")
    public void deleteAll(){
        userMapper.deleteAll();
    }

    @GetMapping
    public User getById(Long id){
        User user = userMapper.getById(id);
        return user;
    }

}
