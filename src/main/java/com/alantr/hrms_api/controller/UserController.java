package com.alantr.hrms_api.controller;

import com.alantr.hrms_api.helper.ApiResponse;
import com.alantr.hrms_api.model.User;
import com.alantr.hrms_api.service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/users")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    // GET /users - Lấy tất cả users: 200 OK
    @GetMapping
    public ResponseEntity<ApiResponse<List<User>>> getAllUsers() {
        List<User> users = userService.findAll();
        return ResponseEntity.ok(ApiResponse.success("Lấy danh sách người dùng thành công", users));
    }

    // GET /users/{id} - Lấy user theo id: 200 OK nếu tìm thấy, 404 NOT_FOUND nếu không
    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse<User>> getUserById(@PathVariable Long id) {
        User user = userService.findById(id);
        if (user == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(ApiResponse.notFound("Không tìm thấy người dùng với ID: " + id));
        }
        return ResponseEntity.ok(ApiResponse.success("Lấy thông tin người dùng thành công", user));
    }

    // POST /users - Tạo user mới: 201 Created với header Location, 409 CONFLICT nếu trùng lặp
    @PostMapping
    public ResponseEntity<ApiResponse<User>> createUser(@RequestBody User user) {
        if (user.getEmail() != null && userService.existsByEmail(user.getEmail())) {
            return ResponseEntity.status(HttpStatus.CONFLICT)
                    .body(ApiResponse.conflict("Email '" + user.getEmail() + "' đã tồn tại trong hệ thống"));
        }
        User createdUser = userService.create(user);
        URI location = URI.create("/users/" + createdUser.getId());
        return ResponseEntity.created(location)
                .body(ApiResponse.created("Tạo mới người dùng thành công", createdUser));
    }

    // PUT /users/{id} - Cập nhật user: 200 OK nếu tìm thấy, 404 NOT_FOUND nếu không
    @PutMapping("/{id}")
    public ResponseEntity<ApiResponse<User>> updateUser(@PathVariable Long id, @RequestBody User user) {
        User updatedUser = userService.update(id, user);
        if (updatedUser == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(ApiResponse.notFound("Không tìm thấy người dùng với ID: " + id));
        }
        return ResponseEntity.ok(ApiResponse.success("Cập nhật thông tin người dùng thành công", updatedUser));
    }

    // DELETE /users/{id} - Xóa user: 204 No Content nếu tìm thấy, 404 NOT_FOUND nếu không
    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse<Void>> deleteUser(@PathVariable Long id) {
        if (!userService.existsById(id)) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(ApiResponse.notFound("Không tìm thấy người dùng với ID: " + id + " để xóa"));
        }
        userService.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}
