package com.doublexman.microservice.sample.test.repository;

import com.doublexman.microservice.sample.Application;
import com.doublexman.microservice.sample.domain.User;
import com.doublexman.microservice.sample.domain.UserRoleView;
import com.doublexman.microservice.sample.repository.UserRepository;
import com.doublexman.microservice.sample.repository.UserRoleRepository;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = Application.class)
public class RepositoryTests {

    @Autowired
    UserRepository userRepository;

    @Autowired
    UserRoleRepository userRoleRepository;

    @Test
    public void testFindUser() {
        User user = userRepository.findOneByUserId("av001");
        Assert.assertNotNull(user);
        Assert.assertEquals(user.getUserName(), "John");
    }

    @Test
    public void testFindPageableUsers() {
        Page<User> users = userRepository.findAll(PageRequest.of(0, 5, new Sort(Sort.Direction.DESC, "userName")));
        Assert.assertNotNull(users);
        Assert.assertEquals(users.getTotalPages(), 3);
        Assert.assertEquals(users.getTotalElements(), 11);
        Assert.assertEquals(users.getContent().size(), 5);
        Assert.assertEquals(users.getContent().get(0).getUserName(), "Tom");
    }

    @Test
    public void testSearchUsers() {
        List<UserRoleView> userRoles = userRoleRepository.searchUsers("user", PageRequest.of(0, 4));
        Assert.assertNotNull(userRoles);
        Assert.assertEquals(userRoles.size(), 4);
    }

}
