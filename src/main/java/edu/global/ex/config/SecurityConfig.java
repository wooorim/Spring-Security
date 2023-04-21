package edu.global.ex.config;

//시큐리티 설정 파일
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import edu.global.ex.security.CustomUserDetailsService;
import edu.global.ex.vo.UserVO;

@Configuration //@Component(객체생성) + 의미(설정할 수 있는 파일)
@EnableMethodSecurity //스프링 시큐리티 필터가 스프링 필터체인에 등록됨 = 스프링 시큐리티를 작동 시키는 파일이라는 걸 알려줌->스프링한테
public class SecurityConfig extends WebSecurityConfigurerAdapter{
	
	@Autowired
	private CustomUserDetailsService customUserDetailsService;
	
	@Bean //Spring안(IOC컨테이너)에 객체 생성 -> @Autowired를 사용해 주입시킬 수 있음
	public PasswordEncoder bCyPasswordEncoder() {
		return new BCryptPasswordEncoder();
	}
	
	@Bean //UserVO는 공용적으로 사용하는 객체이고, 팔요할 때만 불러서 사용할 때는 Bean이 좋다.
	public UserVO userVO() {
		UserVO userVO = new UserVO();
		userVO.setUsername("메롱");
		userVO.setPassword("메롱");
		return userVO;
	}
	
	@Override
	protected void configure(HttpSecurity http) throws Exception{
		//우선 CSRF설정을 해제한다. 초기 개발시만 해주는게 좋다.
		//http.csrf().disable();
		
		http.authorizeRequests() //권한 체크
	      .antMatchers("/user/**").hasAnyRole("USER") // '/user'로 치고 들어갈 수 있는 권한을 가진 사람은 "USER"
	      .antMatchers("/admin/**").hasAnyRole("ADMIN")
	      .antMatchers("/**").permitAll(); 
//	      .antMatchers("/**").hasAnyRole("ADMIN"); // "/"로 치고들어오는 모든 파일 -> 치고들어올 수 있는 권한을 가진 것은 admin
		 
	      
	     //http.formLogin(); //스프링 시큐리티에 있는 기본 로그인 폼을 사용하겠다.
		http.formLogin()
			.loginPage("/login") //loginPage()는 로그인 할 때 페이지 url이고
			.usernameParameter("id")
			.passwordParameter("pw")
			.permitAll(); //모든 유저가 로그인 화면을 볼 수 있게 한다.
		
	}
	
	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
       
//      auth.inMemoryAuthentication()
//           .withUser("user").password("{noop}user").roles("USER").and()
//           .withUser("admin").password("{noop}admin").roles("ADMIN"); //roles 권한
		
		auth.userDetailsService(customUserDetailsService).passwordEncoder(new BCryptPasswordEncoder()); //BCryptPasswordEncoder() 암호화 모듈. PW암호화!
		//passwordEncoder(new BCryptPasswordEncoder()) ->  BCryptPasswordEncoder객체 생성 시키기만하면 시튜리티가 자동으로 match()함수를 이용해 PW를 비교한다.
	}
}
