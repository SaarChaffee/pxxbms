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
import org.junit.Test;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

public class UserServiceImpl implements UserService {
  private final UserDao userDao;
  
  public UserServiceImpl() {
    userDao = new UserDaoImpl();
  }
  
  @Override
  public User Login( String userCode, String userPwd ) {
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
  
  @Override
  public int getUserCount( String userName, int userRole ) {
    Connection connection = null;
    int count = 0;
    
    try{
      connection = DaoUtils.getConnection();
      count = userDao.getUserCount( connection, userName, userRole );
    }catch( SQLException e ){
      e.printStackTrace();
    }finally{
      System.out.println( "------------getUserCount-service--------------" );
      System.out.println( count );
      System.out.println( "==============================================" );
      System.out.println();
      DaoUtils.close( connection, null, null );
    }
    
    return count;
  }
  
  @Override
  public List<User> getUserList( String userName, int userRole, int currentPage, int pageSize ) {
    Connection connection = null;
    List<User> userList = null;
    
    try{
      connection = DaoUtils.getConnection();
      userList = userDao.getUserList( connection, userName, userRole, currentPage, pageSize );
    }catch( SQLException e ){
      e.printStackTrace();
    }finally{
      System.out.println( "------------getUserList-service--------------" );
      System.out.println( userList );
      System.out.println( "=============================================" );
      System.out.println();
      DaoUtils.close( connection, null, null );
    }
    
    return userList;
  }
  
  @Test
  public void test() {
    System.out.println( this.getUserList( null, 0, 1, 5 ) );
  }
}
