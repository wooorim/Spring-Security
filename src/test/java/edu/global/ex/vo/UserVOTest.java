package edu.global.ex.vo;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@SpringBootTest
//VO단 테스트
class UserVOTest {

	@Test
	void userVOtest() {
		UserVO vo = new UserVO();
		log.info("객체 생성" + vo);
		
		System.out.println(vo);
		
		assertNotNull(vo); //vo가 NotNull이면 성공(녹색창)
		
		vo.setPassword("1234");
		vo.setUsername("홍길동");
		
		System.out.println(vo);
		
		assertEquals(vo.getUsername(), "홍길동"); //setter함수로 홍길동을 세팅했기 때문에 비교해서 성공!
		assertEquals(vo.getUsername(), "홍길동1"); //비교했을 때 홍길동　≠ 홍길동1 이니까 실패(빨간창)
	}

}
