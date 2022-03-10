/**
 * @Name: pxxbms
 * @Author: SaarChaffee
 * @Code: UTF-8
 * @Date: Created in 2022 2022/3/9
 */
package com.chaffee.service.user;

import com.chaffee.entity.User;

import java.util.List;

public interface UserService {
  public User Login(String userCode,String userPwd);
  
  public boolean updatePwd(String userCode,int id,String userPwd);
  
  public int getUserCount( String userName, int userRole );
  
  public List<User> getUserList( String userName, int userRole, int currentPage, int pageSize );
}
