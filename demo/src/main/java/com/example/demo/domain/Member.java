package com.example.demo.domain;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

// entity는 mysql의 테이블 및 컬럼을 자동생성해주는 역할 = create table member;
@Entity
public class Member {

//  pk + auto generated 설정, Long은 mySQL에서 bigint로 변환되어 생성
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

// String은 mySQL에서 varchar로 변환되어 생성
    private String name;
    private String email;
    private String password;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
