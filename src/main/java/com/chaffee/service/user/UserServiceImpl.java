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
import java.util.Date;
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
  public boolean updatePwd( int id, String userPwd ) {
    boolean flag = false;
    Connection connection = null;
    int result = 0;
    
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
  
  @Override
  public boolean addUser( User user ) {
    Connection connection = null;
    boolean flag = false;
    
    try{
      connection = DaoUtils.getConnection();
      connection.setAutoCommit( false );
      System.out.println( "'''''''''addUser''''Open transaction''''''''''" );
      int i = userDao.addUser( connection, user );
      if( i > 0 ){
        flag = true;
        System.out.println( "'''''''''addUser''''success''''''''''" );
      }
      else{
        System.out.println( "'''''''''addUser''''failed''''''''''" );
      }
    }catch( SQLException e ){
      try{
        connection.rollback();
        System.out.println( "'''''''''addUser''''rollback''''''''''" );
      }catch( SQLException ex ){
        ex.printStackTrace();
      }
      e.printStackTrace();
    }finally{
      try{
        assert connection != null;
        connection.setAutoCommit( true );
        System.out.println( "'''''''''addUser''''Close transaction''''''''''" );
      }catch( SQLException e ){
        e.printStackTrace();
      }
      DaoUtils.close( connection, null, null );
    }
    
    return flag;
  }
  
  @Override
  public boolean delUser( int id ) {
    Connection connection = null;
    boolean flag = false;
    
    try{
      connection = DaoUtils.getConnection();
      connection.setAutoCommit( false );
      System.out.println( "'''''''''delUser''''Open transaction''''''''''" );
      int i = userDao.deleteUser( connection, id );
      if( i > 0 ){
        flag = true;
        System.out.println( "'''''''''delUser''''success''''''''''" );
      }
      else{
        System.out.println( "'''''''''delUser''''failed''''''''''" );
      }
    }catch( SQLException e ){
      try{
        connection.rollback();
        System.out.println( "'''''''''delUser''''rollback''''''''''" );
      }catch( SQLException ex ){
        ex.printStackTrace();
      }
      e.printStackTrace();
    }finally{
      try{
        assert connection != null;
        connection.setAutoCommit( true );
        System.out.println( "'''''''''delUser''''Close transaction''''''''''" );
      }catch( SQLException e ){
        e.printStackTrace();
      }
      DaoUtils.close( connection, null, null );
    }
    
    return flag;
  }
  
  @Override
  public boolean updateUser( int id, User user ) {
    Connection connection = null;
    boolean flag = false;
    
    try{
      connection = DaoUtils.getConnection();
      connection.setAutoCommit( false );
      System.out.println( "'''''''''updateUser''''Open transaction''''''''''" );
      int i = userDao.updateUser( connection, id, user );
      if( i > 0 ){
        flag = true;
        System.out.println( "'''''''''updateUser''''success''''''''''" );
      }
      else{
        System.out.println( "'''''''''updateUser''''failed''''''''''" );
      }
    }catch( SQLException e ){
      try{
        connection.rollback();
        System.out.println( "'''''''''updateUser''''rollback''''''''''" );
      }catch( SQLException ex ){
        ex.printStackTrace();
      }
      e.printStackTrace();
    }finally{
      try{
        assert connection != null;
        connection.setAutoCommit( true );
        System.out.println( "'''''''''updateUser''''Close transaction''''''''''" );
      }catch( SQLException e ){
        e.printStackTrace();
      }
      DaoUtils.close( connection, null, null );
    }
    
    return flag;
    
  }
  
  @Override
  public User getUserById( int id ) {
    Connection connection = null;
    User user = null;
    
    try{
      connection = DaoUtils.getConnection();
      user = userDao.getUserById( connection, id );
    }catch( SQLException e ){
      e.printStackTrace();
    }finally{
      System.out.println( "------------getUserById-service--------------" );
      System.out.println( user );
      System.out.println( "=======================================" );
      System.out.println();
      DaoUtils.close( connection, null, null );
    }
    
    return user;
  }
  
  @Override
  public User getUserByName( String userName ) {
    Connection connection = null;
    User user = null;
    
    try{
      connection = DaoUtils.getConnection();
      user = userDao.getUserByName( connection, userName );
    }catch( SQLException e ){
      e.printStackTrace();
    }finally{
      DaoUtils.close( connection, null, null );
    }
    
    return user;
    
  }
  
  @Override
  public User getUserByCode( String userCode ) {
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
    
    return user;
    
    
  }
  
  @Test
  public void test() {
    User user = new User();
    user.setId( 5 );
    user.setUserCode( "test1" );
    user.setUserPassword( "123" );
    user.setUserRole( 1 );
    user.setBirthday( new Date( System.currentTimeMillis() ) );
    System.out.println( this.updatePwd( 1, "123" ) );
  }
}
