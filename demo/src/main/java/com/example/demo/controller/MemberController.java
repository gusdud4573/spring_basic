package com.example.demo.controller;

import com.example.demo.domain.Member;
import com.example.demo.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
public class MemberController {

// controller -> service -> repository
// MemberSerivce를 주입
    @Autowired
    private MemberService memberService;


    @GetMapping("members/new")
    public String memberCreateForm(){
        return "member/member-register";
    }


// PostMapping을 통해 MemberService 호출하는 Method 생성
    @PostMapping("members/new")
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
/*
    @ResponseBody
    // input 값을 json으로 받는 방식
    public String memberCreate(@RequestBody Member member){
//      Member 객체를 만들어서 MemberService 매개변수로 전달
        Member member1 = new Member();
        member1.setName(member.getName());
        member1.setEmail(member.getEmail());
        member1.setPassword(member.getPassword());
        memberService.create(member1);
        return "ok";
    }
*/
    @GetMapping("members")
    public String memberFindAll(Model model){
        List<Member> members = memberService.findAll();
        model.addAttribute("memberList", members);
        return "member/member-list";
    }

    @GetMapping("member")
    public String memberDetail(@RequestParam(value = "id")Long myId, Model model){
        Member member = memberService.findById(myId);
        model.addAttribute("member", member);
        return "member/member-detail";
    }

/*
    @GetMapping("members")
    @ResponseBody
    public List<Member> memberFindAll(){
        List<Member> members = memberService.findAll();
        return members;
    }
*/
/*
    @GetMapping("member")
    @ResponseBody
    public Member memberFindSelect(@RequestParam(value = "id")Long myId){
        Member members = memberService.findById(myId);
        return members;
    }
*/

}
