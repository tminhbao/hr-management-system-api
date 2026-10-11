package com.alantr.hrms_api.service;

import com.alantr.hrms_api.model.User;
import com.alantr.hrms_api.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;

    // Lấy tất cả users
    public List<User> findAll() {
        return userRepository.findAll();
    }

    // Lấy user theo id
    public User findById(Long id) {
        return userRepository.findById(id);
    }

    // Tạo mới user
    public User save(User user) {
        return userRepository.save(user);
    }

    // Cập nhật user theo id
    public User update(Long id, User user) {
        return userRepository.update(id, user);
    }

    // Xóa user theo id
    public boolean deleteById(Long id) {
        return userRepository.deleteById(id);
    }
}

