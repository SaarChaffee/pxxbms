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
  /**
   * 获取用户列表
   *
   * @param connection
   * @param userName
   * @param userRole
   * @param currentPageNo
   * @param pageSize
   * @return List<User>
   * @throws SQLException
   */
  public List<User> getUserList( Connection connection, String userName, int userRole, int currentPageNo,
                                 int pageSize ) throws SQLException;
  
  /**
   * 获取登录的用户
   *
   * @param connection
   * @param userCode
   * @return User
   * @throws SQLException
   */
  public User getLoginUser( Connection connection, String userCode ) throws SQLException;
  
  /**
   * 修改用户数据
   *
   * @param connection
   * @param id
   * @param user
   * @return int
   * @throws SQLException
   */
  public int updateUser( Connection connection, int id, User user ) throws SQLException;
  
  /**
   * 修改密码
   *
   * @param connection
   * @param id
   * @param userPassword
   * @return int
   * @throws SQLException
   */
  public int updatePwd( Connection connection, int id, String userPassword ) throws SQLException;
  
  /**
   * 获取用户数量
   *
   * @param connection
   * @param userName
   * @param userRole
   * @return int
   * @throws SQLException
   */
  public int getUserCount( Connection connection, String userName, int userRole ) throws SQLException;
  
  /**
   * 增加用户
   *
   * @param connection
   * @param user
   * @return int
   * @throws SQLException
   */
  public int addUser( Connection connection, User user ) throws SQLException;
  
  /**
   * 删除用户
   *
   * @param connection
   * @param id
   * @return int
   * @throws SQLException
   */
  public int deleteUser( Connection connection, int id ) throws SQLException;
  
  /**
   * 通过Id获取用户
   *
   * @param connection
   * @param id
   * @return User
   * @throws SQLException
   */
  public User getUserById( Connection connection, int id ) throws SQLException;
  
  /**
   * 通过用户名获取用户
   *
   * @param connection
   * @param userName
   * @return
   * @throws SQLException
   */
  public User getUserByName( Connection connection, String userName ) throws SQLException;
  
}
