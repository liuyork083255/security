package liu.york;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

@SpringBootApplication
@RestController
public class SpringSecurityApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringSecurityApplication.class, args);
	}

	@GetMapping("/hello")
	public String hello(){
		System.out.println("hello");
		return "hello spring security";
	}

	@RequestMapping("/")
	public String root(){
		System.out.println("root");
		return "root";
	}

	@RequestMapping(value = "/json/login", method = RequestMethod.POST)
	public String resource(HttpServletRequest request){
		System.out.println("json login ...");
		return "json login";
	}
}
