package edu.global.ex.service;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import edu.global.ex.vo.UserVO;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@SpringBootTest
//서비스단 테스트
class UserServiceTest {

	@Autowired
	private UserService userService;

	@Test
	void addUserTest() {

		UserVO vo = new UserVO();
		log.info("객체 생성" + vo);

		vo.setUsername("홍길동2");
		vo.setPassword("1234");

		System.out.println(vo);

		assertNotNull(vo);
		assertEquals(vo.getUsername(), "홍길동2"); // setter함수로 홍길동을 세팅했기 때문에 비교해서 성공!
		
		userService.addUser(vo);
	}

}
