package com.group.libraryapp;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication // 어노테이션이라는 친구 (자동으로 설정이 됨)
public class LibraryAppApplication {

  public static void main(String[] args) {
    // 서버를 실행시키는 코드 (run)
    SpringApplication.run(LibraryAppApplication.class, args);
  }

}
