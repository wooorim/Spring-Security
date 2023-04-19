package edu.global.ex.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import edu.global.ex.security.CustomUserDetailsService;

@Configuration //@Component(객체생성) + 의미(설정할 수 있는 파일)
@EnableMethodSecurity //스프링 시큐리티 필터가 스프링 필터체인에 등록됨 = 스프링 시큐리티를 작동 시키는 파일이라는 걸 알려줌->스프링한테
public class SecurityConfig extends WebSecurityConfigurerAdapter{
	
	@Autowired
	private CustomUserDetailsService customUserDetailsService;
	
	@Override
	protected void configure(HttpSecurity http) throws Exception{
		//우선 CSRF설정을 해제한다.
		//초기 개발시만 해주는게 좋다.
		http.csrf().disable();
		
		 http.authorizeRequests() //권한 체크
	      .antMatchers("/user/**").hasAnyRole("USER") // '/user'로 치고 들어갈 수 있는 권한을 가진 사람은 "USER"
	      .antMatchers("/admin/**").hasAnyRole("ADMIN")
	      .antMatchers("/**").permitAll();
//	      .antMatchers("/**").hasAnyRole("ADMIN"); // "/"로 치고들어오는 모든 파일 -> 치고들어올 수 있는 권한을 가진 것은 admin
		 
	      
	     http.formLogin(); //스프링 시큐리티에 있는 기본 로그인 폼을 사용하겠다.
	}
	
	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
       
//      auth.inMemoryAuthentication()
//           .withUser("user").password("{noop}user").roles("USER").and()
//           .withUser("admin").password("{noop}admin").roles("ADMIN"); //roles 권한
		
		auth.userDetailsService(customUserDetailsService).passwordEncoder(new BCryptPasswordEncoder()); //BCryptPasswordEncoder() PW암호화!
	}
}
