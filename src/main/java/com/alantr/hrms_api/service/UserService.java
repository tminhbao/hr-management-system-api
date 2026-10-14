package com.alantr.hrms_api.service;

import com.alantr.hrms_api.model.User;
import com.alantr.hrms_api.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {

    private final UserRepository userRepository;

    // Constructor Injection
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    // Lấy tất cả users
    public List<User> findAll() {
        return userRepository.findAll();
    }

    // Lấy user theo id
    public User findById(Long id) {
        return userRepository.findById(id).orElse(null);
    }

    // Tạo mới user
    public User save(User user) {
        return userRepository.save(user);
    }

    public User create(User user) {
        return save(user);
    }

    public boolean existsById(Long id) {
        return userRepository.existsById(id);
    }

    // Cập nhật user theo id
    public User update(Long id, User user) {
        User existingUser = findById(id);
        if (existingUser != null) {
            existingUser.setName(user.getName());
            existingUser.setEmail(user.getEmail());
            existingUser.setPassword(user.getPassword());
            existingUser.setAge(user.getAge());
            existingUser.setAddress(user.getAddress());
            existingUser.setGender(user.getGender());
            existingUser.setAvatar(user.getAvatar());
            return userRepository.save(existingUser);
        }
        return null;
    }

    // Xóa user theo id
    public boolean deleteById(Long id) {
        if (userRepository.existsById(id)) {
            userRepository.deleteById(id);
            return true;
        }
        return false;
    }
}
