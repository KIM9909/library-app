package com.group.libraryapp.service.user;

import com.group.libraryapp.domain.user.User;
import com.group.libraryapp.domain.user.UserRepository;
import com.group.libraryapp.dto.user.request.UserCreateRequest;
import com.group.libraryapp.dto.user.request.UserUpdateRequest;
import com.group.libraryapp.dto.user.response.UserResponse;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

// JPA를 활용하여 자동으로 쿼리 날리기
@Service
public class UserServiceV2 {

    private final UserRepository userRepository;

    public UserServiceV2(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    // create
    @Transactional
    // 아래 있는 함수가 시작될 때 start transaction;을 해준다.
    // 함수가 예외 없이 잘 끝났다면 commit
    // 혹시라도 문제가 있다면 rollback
    public void saveUser(UserCreateRequest request) {
        userRepository.save(new User(request.getName(), request.getAge()));
//        throw new IllegalArgumentException();
    }

    // read
    @Transactional(readOnly = true)
    public List<UserResponse> getUsers() {
        return userRepository.findAll().stream()
                .map(user -> new UserResponse(user.getId(), user.getName(), user.getAge()))
                .collect(Collectors.toList());
    }

    // update
    @Transactional
    public void updateUser(UserUpdateRequest request) {
        User user = userRepository.findById(request.getId())
                .orElseThrow(IllegalArgumentException::new);

        user.updateName(request.getName());
        // userRepository.save(user);
        // 영속성 컨텍스트 안에서는 변경을 감지해 자동 저장 됨
    }

    // delete
    @Transactional
    public void deleteUser(String name) {
        // SELECT * FROM user WHERE name = ?
        User user = userRepository.findByName(name).orElseThrow(IllegalArgumentException::new);

        userRepository.delete(user);
    }
}
