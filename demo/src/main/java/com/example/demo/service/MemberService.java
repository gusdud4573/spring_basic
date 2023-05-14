package com.example.demo.service;

import com.example.demo.domain.Member;
import com.example.demo.repository.MemberRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.OptionalInt;

@Service
public class MemberService {

// controller -> service -> repository
    // service에서 repository를 주입받기 위해서, Autowired를 사용
    @Autowired
    private MemberRepository memberRepository;

    //회원가입(등록)_save : db에 insert/update 시키는 기능
    public void create(Member member){
        memberRepository.save(member);
    }

    //회원목록조회
    //memberRepository.findAll()의 기본 return 타입은 List<해당객체>
    public List<Member> findAll(){
        List<Member> members = memberRepository.findAll();
        return members;
    }

    public Member findById(Long myId){
        //값이 있을지 없을지 모르는 상태여서(optional) -> orElse(null)
        Member members = memberRepository.findById(myId).orElse(null);
        return members;
    }

}
