/**
 * @Name: pxxbms
 * @Author: SaarChaffee
 * @Code: UTF-8
 * @Date: Created in 2022 2022/3/1
 */
package com.chaffee.dao.user;

import com.chaffee.entity.User;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

public interface UserDao {
  
  public List<User> getUserList( Connection connection, String userName, int userRole, int currentPageNo,
                                 int pageSize ) throws SQLException;
  
  public User getLoginUser( Connection connection, String userCode ) throws SQLException;
  
  public int updateUser( Connection connection, int id, User user ) throws SQLException;
  
  public int updatePwd( Connection connection, int id, String userPassword ) throws SQLException;
  
  public int getUserCount( Connection connection, String userName, int userRole ) throws SQLException;
  
  public int addUser( Connection connection, User user ) throws SQLException;
  
  public int deleteUser( Connection connection, int id ) throws SQLException;
  
  public User getUserById( Connection connection, int id ) throws SQLException;
  
}
