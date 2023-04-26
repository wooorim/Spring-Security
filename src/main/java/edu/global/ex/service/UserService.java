package edu.global.ex.service;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import edu.global.ex.mapper.UserMapper;
import edu.global.ex.vo.UserVO;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service

public class UserService {
	@Autowired
	private UserMapper userMapper;
	
	   // 1.정상 동작 코드 => User를 insert를 넣은후 권한 까지 insert 해야 됨 => 2개가 정상적인 동작이 되어야 됨(트랜잭션 단위)
	   public void addUser(UserVO user) {
	      log.info("addUser()..");

	      userMapper.insertUser(user); // User를 insert를 넣은후
	      userMapper.insertAuthorities(user); // 권한 설정

	   }
	   
	   //2. 아래는 select * from users에는 들어가고 select * from authorities 권한에는 안들어감.
	   public void addUser2(UserVO user) {
		   log.info("addUser2()..");
		   
		   userMapper.insertUser(user); // User를 insert를 넣은후
		   //일부러 에러냄
		   user = null;
		   
		   userMapper.insertAuthorities(user); //권한설정
	   }
	   
	   // 3.아래 처럼 @Transactional 을 붙임 //롤백에 되어 아무런 데이터가 들어 가지 않음
	   @Transactional
	   public void addUser3(UserVO user) {
	      log.info("addUser3()..");

	      userMapper.insertUser(user); // User를 insert를 넣은후

	      // 일부러 에러냄
	      user = null;
	      userMapper.insertAuthorities(user); // 권한 설정
	   }
	   
		// 4.checked Exception을 강제로 시켜 봄 //롤백 되지 않음
		@Transactional
		public void addUser4(UserVO user) throws Exception {
			log.info("addUser4()..");

			userMapper.insertUser(user); // User를 insert를 넣은후
			userMapper.insertAuthorities(user);

			// throw Checked Exception
			throw new Exception("Exception (Checked Exception)"); //강제로 에러를 냄
		}
		
		// 5.checked Exception을 강제로 시켜 봄 //롤백 되지 않음
		@Transactional
		public void addUser5(UserVO user) throws Exception {
			log.info("addUser5()..");

			userMapper.insertUser(user); // User를 insert를 넣은후
			userMapper.insertAuthorities(user);

			// throw UnChecked Exception
			throw new RuntimeException("Runtime Exception (UnChecked Exception)"); // 강제로 에러를 냄
		}
		
		// 6.rollbackFor 옵션을 줌 //롤백 됨
		@Transactional(rollbackFor = Exception.class)
		public void addUser6(UserVO user) throws Exception {
			log.info("addUser()..");

			userMapper.insertUser(user); // User를 insert를 넣은후
			userMapper.insertAuthorities(user);

			// throw Checked Exception
			throw new Exception("RuntimeException (Unchecked Exception)");
		}
	   
//  트랜잭션 전 코드
//	public int addUser(UserVO user) {
//		log.info("addUser()..");
//		return userMapper.insertUser(user);
//	}
}
