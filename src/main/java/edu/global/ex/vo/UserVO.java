package edu.global.ex.vo;

import java.util.List;

import org.springframework.stereotype.Service;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString

public class UserVO {
	private String username;
	private String password;
	private int enabled; //1이 활성화 0이 비활성화
	
	private List<AuthVO> authList;
}
