package com.example.demo.controller;

import com.example.demo.domain.Member;
import com.example.demo.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller()
public class MemberJsonController {
    @Autowired
    private MemberService memberService;


    // PostMapping을 통해 MemberService 호출하는 Method 생성
    @PostMapping("json/members/new")
    @ResponseBody
    // input 값을 form-data로 받는 방식
    public String memberCreate(@RequestParam(value="name")String myname,
                               @RequestParam(value="email")String myemail,
                               @RequestParam(value="password")String mypassword){
        Member member1 = new Member();
        member1.setName(myname);
        member1.setEmail(myemail);
        member1.setPassword(mypassword);
        memberService.create(member1);
        return "ok";
    }

    @GetMapping("json/members")
    @ResponseBody
    public List<Member> memberFindAll(){
        List<Member> members = memberService.findAll();
        return members;
    }

    @GetMapping("json/member")
    @ResponseBody
    public Member memberDetail(@RequestParam(value = "id")Long myId){
        Member member = memberService.findById(myId);
        return member;
    }

}
