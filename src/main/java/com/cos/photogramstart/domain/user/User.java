package com.cos.photogramstart.domain.user;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;

// ORM
// JPA - JavaPersistence API (자바로 데이터를 영구적으로 저장(DB)할 수 있는 API를 제공)

@Builder
@AllArgsConstructor // 전체 생성자
@NoArgsConstructor // bean 생성자
@Data
@Entity // 디비에 테이블을 생성
public class User {

    @Id // 기본키
    @GeneratedValue(strategy = GenerationType.IDENTITY) // 번호 증가 전략이 데이터베이스를 따라간다.
    private Long id;

    @Column(length = 20, unique = true)
    private String username;
    @Column(nullable = false) // null 불가능
    private String password;

    @Column(nullable = false) // null 불가능
    private String name;
    private String website; // 웹사이트
    private String bio; // 자기소개
    @Column(nullable = false) // null 불가능
    private String email;
    private String phone;
    private String gender;

    private String profileImageUrl; // 사진
    private String role; // 권한

    private LocalDateTime createDate;

    @PrePersist // 디비에 insert 되기 직전에 실행
    public void createDate(){
        this.createDate = LocalDateTime.now();
    }
}
