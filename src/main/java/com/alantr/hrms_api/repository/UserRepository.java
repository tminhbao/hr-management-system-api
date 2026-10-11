package com.alantr.hrms_api.repository;

import com.alantr.hrms_api.model.User;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public class UserRepository {
    // Lưu trữ danh sách User trong bộ nhớ (RAM)
    private final List<User> users = new ArrayList<>();
    private Long nextId = 1L;

    // Khởi tạo sẵn 3 user mẫu
    public UserRepository() {
        save(new User(null, "Nguyen Van A", "nguyenvana@gmail.com"));
        save(new User(null, "Tran Thi B", "tranthib@gmail.com"));
        save(new User(null, "Le Van C", "levanc@gmail.com"));
    }

    // Lấy toàn bộ danh sách users
    public List<User> findAll() {
        return new ArrayList<>(users);
    }

    // Tìm user theo id
    public User findById(Long id) {
        for (User user : users) {
            if (user.getId().equals(id)) {
                return user;
            }
        }
        return null; // Trả về null nếu không tìm thấy
    }

    // Thêm mới user
    public User save(User user) {
        if (user.getId() == null) {
            user.setId(nextId++);
        } else if (user.getId() >= nextId) {
            nextId = user.getId() + 1;
        }
        users.add(user);
        return user;
    }

    // Cập nhật thông tin user theo id
    public User update(Long id, User updatedUser) {
        User existingUser = findById(id);
        if (existingUser != null) {
            existingUser.setName(updatedUser.getName());
            existingUser.setEmail(updatedUser.getEmail());
            return existingUser;
        }
        return null; // Trả về null nếu không tìm thấy user cần update
    }

    // Xóa user theo id
    public boolean deleteById(Long id) {
        User user = findById(id);
        if (user != null) {
            users.remove(user);
            return true;
        }
        return false;
    }
}

