package com.ecommerce.controller;

import com.ecommerce.ApiUrls;
import com.ecommerce.entity.AppUser;
import com.ecommerce.exception.ResourceNotFoundException;
import com.ecommerce.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping(ApiUrls.VERSION_1 + ApiUrls.ROOT_URL_USERS)
public class UserRestController {
    private static final Logger logger = LoggerFactory.getLogger(UserRestController.class);

    private final UserService userService;

    public UserRestController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping
    public ResponseEntity<?> findAll() {
        List<AppUser> userList = userService.findAll();
        return ResponseEntity.ok(userList);
    }

    @GetMapping(ApiUrls.URL_USERS_USER)
    public ResponseEntity<?> findOne(@PathVariable(value = "userId") Long id) {
        return ResponseEntity.ok(userService.findOne(id));
    }

    @PostMapping
    public ResponseEntity<?> save(@RequestBody AppUser user) {
        logger.trace("save");

        user = userService.save(user);
        URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(user.getId()).toUri();
        return ResponseEntity.created(location).body(user);
    }

    @PutMapping(ApiUrls.URL_USERS_USER)
    public ResponseEntity<?> update(@PathVariable(value = "userId") Long id, @RequestBody AppUser user) {
        if(!userService.exists(id)) {
            throw new ResourceNotFoundException("User with id " + id + " not found");
        }
        return ResponseEntity.ok(userService.update(id, user));
    }

    @DeleteMapping(ApiUrls.URL_USERS_USER)
    public ResponseEntity<?> delete(@PathVariable(value = "userId") Long id) {
        if(!userService.exists(id)) {
            throw new ResourceNotFoundException("User with id " + id + " not found");
        }
        userService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
