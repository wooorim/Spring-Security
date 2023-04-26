package edu.global.ex.controller;

import java.security.Principal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import edu.global.ex.service.UserService;
import edu.global.ex.vo.UserVO;
import lombok.extern.slf4j.Slf4j;


@Slf4j
@Controller
public class LoginController {

	@Autowired
	private UserService userService;
	
	@Autowired
	private PasswordEncoder encoder;
	
	@GetMapping("/login")
	public String login() {
		log.info("login()..");

		return "login/login";
	}

	@RequestMapping(value = "/loginInfo", method = RequestMethod.GET)
	public String loginInfo(Authentication authentication, Principal principal, Model model) { //Authentication authentication, Principal principal -> 세션에 저장된 시큐리티 객체

		String user_id;

		// 1.SpringContextHolder를 통하여 가져오는 방법(일반적인 빈에서 사용 할수있음 )
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		user_id = auth.getName();
		System.out.println("유저 아이디:" + user_id);

		// 2.authentication 객체로 가져오는 방법(많은 정보)
		System.out.println("authentication 유저 아이디:" + authentication.getName());
		System.out.println("authentication 권한들:" + authentication.getAuthorities());

		// 3.Principal 객체로 가져오는 방법(가져올수 있는게 getName() 정도
		System.out.println("Principal 유저 아이디:" + principal.getName());

		return "redirect:/";
	}
	
	//restful방식 Transaction 테스트
	@GetMapping("/addUser/{id}/{pw}") 
	public String addUser(@PathVariable String id, @PathVariable String pw) throws Exception{
		System.out.println(id + ":" + pw);
		
		UserVO user = new UserVO();
		user.setEnabled(1);
		user.setUsername(id);
		user.setPassword(encoder.encode(pw.toString().trim()));
		
		System.out.println(user);
		
//		userService.addUser(user); //예제1
//		userService.addUser2(user); //예제2
//		userService.addUser3(user); //예제3
//		userService.addUser4(user); //예제4
		userService.addUser5(user);
		return "redirect:/";
	}
	


}
