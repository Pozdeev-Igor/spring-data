package com.spring_data_new.service;

import com.spring_data_new.model.User;
import com.spring_data_new.repository.UserRepo;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class UserServiceImpl implements UserService {
    private final UserRepo userRepository;

    //    @Autowired
    public UserServiceImpl(UserRepo userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public List<User> findAll() {
        return userRepository.findAll();
    }
    @Override
    public User saveUser(User employee) {
        return userRepository.save(employee);
    }

}
