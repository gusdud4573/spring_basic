package com.example.demo.repository;

//mybatis라는 DB Connect 기술을 쓰기 이전에는 ibatis라는 게 존재했었기에, Mapper 어노테이션은 ibatis 패키지 안에 들어있다.
import com.example.demo.domain.Member;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface MemberMybatisRepository {
//    JPA와는 다르게, 사용하고자 하는 메서드를 사전에 정의해야 한다.
    void save(Member member);
    List<Member> findAll();
    Member findById(Long id);
}
