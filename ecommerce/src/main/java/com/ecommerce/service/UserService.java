package com.ecommerce.service;

import com.ecommerce.entity.AppUser;
import com.ecommerce.exception.ResourceNotFoundException;
import com.ecommerce.repository.UserRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional(readOnly = true)
public class UserService {
    private static final Logger logger = LoggerFactory.getLogger(UserService.class);

    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public List<AppUser> findAll() {
        return userRepository.findAll();
    }

    public AppUser findOne(Long id) {
        return userRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("User with id = " + id + " not found"));
    }

    public AppUser save(AppUser user) {
        return userRepository.save(user);
    }

    @Transactional
    public AppUser update(Long id, AppUser user) {
        AppUser mUser = userRepository.findById(id).orElseThrow();

        mUser.setFirstName(user.getFirstName());
        mUser.setLastName(user.getLastName());
        mUser.setFullName(user.getFullName());
        mUser.setUsername(user.getUsername());
        mUser.setEmployeeId(user.getEmployeeId());
        mUser.setEmail(user.getEmail());
        mUser.setMobile(user.getMobile());
        mUser.setRoles(user.getRoles());

        return mUser;
    }

    public void delete(Long id) {
        userRepository.deleteById(id);
    }

    public boolean exists(Long id) {
        return userRepository.existsById(id);
    }


}
