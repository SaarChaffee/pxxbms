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
  

}
