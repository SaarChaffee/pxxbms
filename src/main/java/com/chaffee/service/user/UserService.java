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
  public User Login( String userCode, String userPwd );
  
  public boolean updatePwd(  int id, String userPwd );
  
  public int getUserCount( String userName, int userRole );
  
  public List<User> getUserList( String userName, int userRole, int currentPage, int pageSize );
  
  public boolean addUser( User user );
  
  public boolean delUser( int id );
  
  public boolean updateUser( int id, User user );
  
  public User getUserById( int id );
  
  public User getUserByName(String userName);
  
}
