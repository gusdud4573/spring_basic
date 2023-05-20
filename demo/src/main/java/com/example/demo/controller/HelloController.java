package com.example.demo.controller;

//import org.springframework.security.access.AccessDeniedException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;


/*
메시지 : request, response
get요청 : parameter(url에 포함), form, json
request : header & body(눈에 보이지 않는다 - 민감정보가 있을 수 있음)
response : header & body
 */

/*
1번 방식 : 화면을 받고 + 데이터 요청
1-1. 화면을 받을때, 별도의 프론트 서버로부터 recieve
1-2. 화면을 받을때, 백엔드서버에서 화면도 recieve
2번방식 : 화면 + 데이터 한번에 받는다
*/

@Controller
public class HelloController {

// http://localhost:8080/hello로 요청시 hello 메서드에서 처리
// http는 국제 통신 프로토콜이다. https는 s(secure) 보안이 강화된 통신 프로토콜
// port란 한 ip 내에 여러프로그램을 구분짓는 단위, 집주소가 IP, 각집의 문이 port번호
// data만을 return 할때는 ResponseBody를 사용한다.
    @GetMapping("hello")
    @ResponseBody
    public String hello(){
        return "hello world";
    }

// jsp/thymeleaf같은 템플릿엔진을 사용하며 화면을 return할때는 responsebody를 사용하면 안된다.
// 그리고 Model이라는 객체에 data를 담아 return xxx를 하면 xxx.html파일로 데이터를 보낸다.
    @GetMapping("hello_thymeleaf")
    public String hello2(Model model){
        model.addAttribute("getdata","hello2 world");
        return "hello-th";
    }

//  데이터를 첨부시키지 않고, 화면만을 랜더링(준다).
    @GetMapping("hello-html")
    public String helloHtml(){
        return "hello-get-req";
    }

//  get 요청에 param에 id값을 넣어서 data를 요청
    @GetMapping("hello-param")
    @ResponseBody
    public String helloParam(@RequestParam(value = "id")String id){
//        if(true){
//            throw new AccessDeniedException("권한이 없습니다.");
//        }
        System.out.println(id);
        return "ok";
    }

    @GetMapping("hello-post-form-req")
    public String helloGetFormReq(){
        return "hello-post-form-req";
    }

//  html의 form 형식으로 post 요청
//  form 형식의 경우 parameter로 데이터가 넘어오므로, RequestParam으로 받아줘야 한다.
    @PostMapping("hello-post-form-req")     //post 요청으로 달고 들어와야함
    @ResponseBody
    public String helloPostFormReq(@RequestParam(value="name")String myname,
                                 @RequestParam(value="email")String myemail,
                                 @RequestParam(value="password")String mypassword){         //사용자가 달라고 하는 요청이 아니라서 return 값이 없음(void)
        System.out.println("이름 : "+myname);
        System.out.println("이메일 : "+myemail);
        System.out.println("비밀번호 : "+mypassword);

        return "ok";    //responsebody를 통해 ok라는 답변 출력
    }

//사용자가 서버로 데이터를 보내는 방식 3가지
/*
  //1. ?를 통해 파라미터에 값을 넣어 보내는 방식 : 대부분 get 요청시 사용
    //  테스트를 할 때에, localhost:8080/hello-parameter?test=hello
    @GetMapping("hello-parameter")
    @ResponseBody
    public String helloParameter(@RequestParam(value = "test")String mytest){
        System.out.println("클라이언트가 보내온 parameter는? "+mytest);
        return "ok";
    }
*/
/*
  //2. form 태그 안에 데이터를 넣어 보내는 방식 : post 요청시 사용
   -> 보안 강화, url에 데이터가 찍히지 않음 but 내부적으로는 ?key=value&key2=value2의 형식을 취함
    @GetMapping("hello-parameter")
    @ResponseBody
    public String helloParameter(@RequestParam(value="name")String myname,
                                 @RequestParam(value="email")String myemail,
                                 @RequestParam(value="password")String mypassword){         //사용자가 달라고 하는 요청이 아니라서 return 값이 없음(void)
        System.out.println("이름 : "+myname);
        System.out.println("이메일 : "+myemail);
        System.out.println("비밀번호 : "+mypassword);
        return "ok";
    }
*/

  //3. json데이터 형식으로 서버로 보내는 방식 : post 요청시 사용  json데이터란? {"key1:value1", "key2:value2"}의 형식을 취하는 데이터이다.
  //현대적인 web서비스에서 대부분 데이터를 주고 받을 때 json을 사용한다.
  //json html의 form 태그에 넣어서 보내는 방식이 아니다 보니, Ajax, react 이런 javascript 프레임워크를 사용하게 된다.
  //json으로 POST 요청을 하기 위한 화면 return
    @GetMapping("hello-get-json-req")
    public String helloGetJsonReq(){
       return "hello-post-json-req";
    }

  //json으로 POST요청이 들어왔을 때는 data를 꺼내기 위해 RequestBody 사용
    @PostMapping("hello-json")
    @ResponseBody
    public String helloJson(@RequestBody Hello hello){
        System.out.println("이름 : "+hello.getName());
        System.out.println("이메일 : "+hello.getEmail());
        System.out.println("비밀번호 : "+hello.getPassword());
        return "ok";
    }

  //  json으로 입력받은 데이터를 json 형태로 내보내줌
  //  ResponseBody어노테이션이 붙어있고, return 타입이 객체이면, Spring이 Json형태로 변환해준다.
    @PostMapping("hello-json-response")
    @ResponseBody
    public GoodBye helloJsonResponse(@RequestBody Hello hello){
        System.out.println("이름 : "+hello.getName());
        System.out.println("이메일 : "+hello.getEmail());
        System.out.println("비밀번호 : "+hello.getPassword());
        GoodBye goodbye1 = new GoodBye();
        goodbye1.setName(hello.getName());
        goodbye1.setEmail(hello.getEmail());
        goodbye1.setComments("Thank you");
        return goodbye1;

    }
    //.jsp 파일의 기본경로는 resources/webapp/WEB-INF/views

}
