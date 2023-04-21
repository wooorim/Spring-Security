package edu.global.ex.vo;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

public class UserDetailsVO implements UserDetails {

   //////////////////////////////////////////////////////////////////
   private String username; // ID
   private String password; // PW
   private List<GrantedAuthority> authorities;
   
   public UserDetailsVO(UserVO user) {
      this.setAuthorities(user);
      this.setPassword(user.getPassword());
      this.setUsername(user.getUsername());
   }
   
   // setter
   public void setUsername(String username) {
      this.username = username;
   }
   
   // setter
   public void setPassword(String password) {
      this.password = password;
   }

   // setter
   public void setAuthorities(UserVO userVO) {

      List<GrantedAuthority> authorities = new ArrayList<GrantedAuthority>();

      for (AuthVO auth : userVO.getAuthList()) {
         authorities.add(new SimpleGrantedAuthority(auth.getAuthority()));
      }
      
      this.authorities = authorities;
   }
   //////////////////////////////////////////////////////////////////////////////////
   public String getCart() {
	   return "이것은 장바구니다.";
   }
   @Override
   public Collection<? extends GrantedAuthority> getAuthorities() {
      // TODO Auto-generated method stub
      return authorities;
   }

   @Override
   public String getPassword() {
      // TODO Auto-generated method stub
      return password;
   }

   @Override
   public String getUsername() {
      // TODO Auto-generated method stub
      return username;
   }

   // 계정이 만료 되지 않았는가?
   @Override
   public boolean isAccountNonExpired() {
      // TODO Auto-generated method stub
      return true;
   }

   // 계정이 잠기지 않았는가?
   @Override
   public boolean isAccountNonLocked() {
      // TODO Auto-generated method stub
      return true;
   }

   // 패스워드가 만료되지 않았는가?
   @Override
   public boolean isCredentialsNonExpired() {
      // TODO Auto-generated method stub
      return true;
   }
   
   // 계정이 활성화 되었는가?
   @Override
   public boolean isEnabled() {
      // TODO Auto-generated method stub
      return true;
   }


}