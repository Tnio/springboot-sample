package com.evan.demo.redis.web;

import com.alibaba.fastjson.JSON;
import com.evan.demo.redis.config.MyRedisTemplate;
import com.evan.demo.redis.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 */
@RestController
@RequestMapping("/users")
public class UserController {

    @Autowired
    private MyRedisTemplate myRedisTemplate;

    @RequestMapping(value = "/user", method = RequestMethod.GET)
    public User getUser() {
        User user = new User();
        user.setAge(20);
        user.setUsername("foo");
        return user;
    }

    @RequestMapping(value = "/testJedisCluster", method = RequestMethod.GET)
    public User testJedisCluster(@RequestParam("username") String username) {
        String value = myRedisTemplate.get("foo-user:", username);
        if (StringUtils.isEmpty(value)) {
            myRedisTemplate.set("foo-user:", username, JSON.toJSONString(getUser()));
            return null;
        }
        return JSON.parseObject(value, User.class);
    }


}