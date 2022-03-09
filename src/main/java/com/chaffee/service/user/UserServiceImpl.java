/**
 * @Name: pxxbms
 * @Author: SaarChaffee
 * @Code: UTF-8
 * @Date: Created in 2022 2022/3/9
 */
package com.chaffee.service.user;

import com.chaffee.dao.DaoUtils;
import com.chaffee.dao.user.UserDao;
import com.chaffee.dao.user.UserDaoImpl;
import com.chaffee.entity.User;

import java.sql.Connection;
import java.sql.SQLException;

public class UserServiceImpl implements UserService {
  private final UserDao userDao;
  
  public UserServiceImpl() {
    userDao = new UserDaoImpl();
  }
  
  @Override
  public User userLogin( String userCode, String userPwd ) {
    Connection connection = null;
    User user = null;
    
    if( userCode != null ){
      try{
        connection = DaoUtils.getConnection();
        user = userDao.getLoginUser( connection, userCode );
      }catch( SQLException e ){
        e.printStackTrace();
      }finally{
        DaoUtils.close( connection, null, null );
      }
    }
    
    if( user != null && user.getUserPassword().equals( userPwd ) ){
      return user;
    }
    
    return null;
  }
  
  @Override
  public boolean updatePwd( String userCode, int id, String userPwd ) {
    boolean flag = false;
    Connection connection = null;
    int result = 0;
    
    if( userCode != null ){
      try{
        connection = DaoUtils.getConnection();
        connection.setAutoCommit( false );
        result = userDao.updatePwd( connection, id, userPwd );
      }catch( SQLException e ){
        e.printStackTrace();
      }finally{
        try{
          assert connection != null;
          connection.setAutoCommit( true );
        }catch( SQLException e ){
          e.printStackTrace();
        }
        DaoUtils.close( connection, null, null );
      }
    }
    if( result > 0 ) flag = true;
    
    return flag;
  }
}
