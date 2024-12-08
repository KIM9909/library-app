package com.group.libraryapp.controller.user;

import com.group.libraryapp.dto.user.request.UserCreateRequest;
import com.group.libraryapp.dto.user.request.UserUpdateRequest;
import com.group.libraryapp.dto.user.response.UserResponse;
import com.group.libraryapp.service.user.UserServiceV1;
import com.group.libraryapp.service.user.UserServiceV2;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class UserController {

    private final UserServiceV2 userService;;

    public UserController(UserServiceV2 userService) {
        this.userService = userService;
    }

    // 삽입
    @PostMapping("/user") // POST /user
    public void saveUser(@RequestBody UserCreateRequest request) {
        // 데이터 베이스에 저장
//        String sql = "INSERT INTO user (name, age) VALUES (?, ?)"; // (?, ?)는 유동적으로 값을 넣을 수 있도록
//        jdbcTemplate.update(sql, request.getName(), request.getAge());
        userService.saveUser(request);
    }

    // 조회
    @GetMapping("/user") // GET /user
    public List<UserResponse> getUsers() {
//        String sql = "SELECT * FROM user";
//        return jdbcTemplate.query(sql, new RowMapper<UserResponse>() {
//            // RowMapper 후 ctrl + o
//            @Override
//            public UserResponse mapRow(ResultSet rs, int rowNum) throws SQLException {
//                 long id = rs.getLong("id");
//                 String name = rs.getString("name");
//                 int age = rs.getInt("age");
//                 return new UserResponse(id, name, age);
//            }
//        });
        return userService.getUsers();
    }

    // 수정
    @PutMapping("/user")
    public void updateUser(@RequestBody UserUpdateRequest request) {
//        // id 를 기준으로 유저가 존재하는 지 확인
//        String readSql = "SELECT * FROM user WHERE id = ?";
//        // SQL을 날려 DB에 데이터가 있는지 확인
//        // 해당 아이디를 가진 유저가 있다면 0을 반환 후 리스트에 담김
//        // 없다면 0을 반환 못함 -> isUserNotExist가 True가 되고 예외처리가 됨
//        boolean isUserNotExist =  jdbcTemplate.query(readSql, (rs, rowNum) -> 0, request.getId()).isEmpty();
//        if (isUserNotExist) {
//            throw new IllegalArgumentException();
//        }
//
//        String sql = "UPDATE user SET name = ? WHERE id = ?";
//        jdbcTemplate.update(sql, request.getName(), request.getId());
        userService.updateUser(request);
    }

    // 삭제
    @DeleteMapping("/user")
    public void deleteUser(@RequestParam String name) {
        // id 를 기준으로 유저가 존재하는 지 확인
//        String readSql = "SELECT * FROM user WHERE name = ?";
//        // SQL을 날려 DB에 데이터가 있는지 확인
//        // 해당 아이디를 가진 유저가 있다면 0을 반환 후 리스트에 담김
//        // 없다면 0을 반환 못함 -> isUserNotExist가 True가 되고 예외처리가 됨
//        boolean isUserNotExist =  jdbcTemplate.query(readSql, (rs, rowNum) -> 0, name).isEmpty();
//        if (isUserNotExist) {
//            throw new IllegalArgumentException();
//        }
//
//        String sql = "DELETE FROM user WHERE name = ?";
//        jdbcTemplate.update(sql, name);
        userService.deleteUser(name);
    }

//    @GetMapping("/user/error-test")
//    public void errorTest() {
//        throw new IllegalArgumentException();
//    }
}