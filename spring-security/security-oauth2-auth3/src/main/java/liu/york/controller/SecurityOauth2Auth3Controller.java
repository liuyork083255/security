package liu.york.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class SecurityOauth2Auth3Controller {
    @RequestMapping("/not/auth")
    public String fun1(){
        System.out.println("not auth");
        return "not auth";
    }

    @RequestMapping("/need/auth")
    public String fun2(){
        System.out.println("need auth");
        return "need auth";
    }

    @RequestMapping("/admin")
    public String fun3(){
        System.out.println("admin");
        return "admin";
    }

    @RequestMapping("/user")
    public String fun4(){
        System.out.println("user");
        return "user";
    }

}