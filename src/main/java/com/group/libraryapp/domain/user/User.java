package com.group.libraryapp.domain.user;

import com.group.libraryapp.domain.user.loanHistory.UserLoanHistory;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity // 스프링이 User객체와 user테이블을 같은 것으로 바라본다.
public class User {

    @Id // 이 필드를 primary key로 간주한다.
    @GeneratedValue(strategy = GenerationType.IDENTITY) // primary key는 자동 생성되는 값이다.
    private Long id = null;

    @Column(nullable = false, length = 20, name = "name") // name varchar(20)
    private String name;

    private Integer age; // DB의 컬럼과 완전 동일하기 때문에 어노테이션 불필요

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true) // 연관관계의 주인이 아닌 쪽에 mappedBy를 설정
    // orphanRemoval 옵션 => 객체간 관계가 끊어진 데이터를 자동으로 DB에서도 제거하는 옵션
    private List<UserLoanHistory> userLoanHistories = new ArrayList<>();

    public Long getId() {
        return id;
    }

    protected User() { // 생성자
    }

    public User(String name, Integer age) {
        if (name == null || name.isEmpty()) {
            throw new IllegalArgumentException(String.format("잘못된 name(%s)이 들어왔습니다.", name));
        }
        this.name = name;
        this.age = age;
    }

    public String getName() {
        return name;
    }

    public Integer getAge() {
        return age;
    }

    public void updateName(String name) {
        this.name = name;
    }

    public void loanBook(String bookName){
        this.userLoanHistories.add(new UserLoanHistory(this, bookName));
    }

    public void returnBook(String bookName) {
        UserLoanHistory targetHistory = this.userLoanHistories.stream()
                .filter(history -> history.getBookName().equals(bookName))
                .findFirst()
                .orElseThrow(IllegalArgumentException::new);
        targetHistory.doReturn();
    }

}
