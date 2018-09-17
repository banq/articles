package com.doublexman.uum.rest.web;

import com.doublexman.uum.domain.User;
import com.doublexman.uum.service.UserRoleService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/web-api/user")
public class UserResource {

    private final UserRoleService userRoleService;

    public UserResource(UserRoleService userRoleService) {
        this.userRoleService = userRoleService;
    }

    @PostMapping(value = "/findall", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<?> findAll(@RequestParam("size") int size, @RequestParam("page") int page) {
        Page<User> users = userRoleService.findAll(PageRequest.of(page, size));
        return new ResponseEntity<>(users, HttpStatus.OK);
    }
}
