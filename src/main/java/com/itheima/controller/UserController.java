package com.itheima.controller;

import com.itheima.entity.User;
import com.itheima.mapper.UserMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/user")
@Slf4j
public class UserController {

    @Autowired
    private UserMapper userMapper;


    /**
     * CachePut：将方法返回值放入缓存
     * cacheNames：缓存的名称，每个缓存名称下面可以有多个key
     * key：缓存的key
     */
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


    /**
     * CacheEvict：清理指定缓存
     * cacheNames：缓存的名称，每个缓存名称下面可以有多个key
     * key：缓存的key
     */
    @DeleteMapping
    @CacheEvict(cacheNames = "userCache",key = "#id") // key的生成:userCache::id
    public void deleteById(Long id){
        userMapper.deleteById(id);
    }

	@DeleteMapping("/delAll")
    @CacheEvict(cacheNames = "userCache",allEntries=true)
    public void deleteAll(){
        userMapper.deleteAll();
    }

    @GetMapping
    public User getById(Long id){
        User user = userMapper.getById(id);
        return user;
    }

}
