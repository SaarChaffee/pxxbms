/**
 * @Name: pxxbms
 * @Author: SaarChaffee
 * @Code: UTF-8
 * @Date: Created in 2022 2022/3/1
 */
package com.chaffee.dao.user;

import com.alibaba.druid.util.StringUtils;
import com.chaffee.dao.DaoUtils;
import com.chaffee.entity.User;
import org.junit.Test;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class UserDaoImpl implements UserDao {
  @Override
  public List<User> getUserList( Connection connection, String userName, int userRole, int currentPageNo,
                                 int pageSize ) throws SQLException {
    PreparedStatement pstm = null;
    ResultSet rs = null;
    List<Object> list = new ArrayList<>();
    List<User> userList = new ArrayList<>();
    StringBuffer sql = new StringBuffer();
    
    if( connection != null ){
      sql.append( "select u.*, ur.roleName from user u, user_role ur where u.userRole = ur.roleCode" );
      if( !StringUtils.isEmpty( userName ) ){
        sql.append( " and u.userName like ?" );
        list.add( "%" + userName + "%" );
      }
      if( userRole != 0 ){
        sql.append( " and u.userRole = ?" );
        list.add( userRole );
      }
      sql.append( " order by u.id ASC limit ?,?" );
      currentPageNo = ( currentPageNo - 1 ) * pageSize;
      list.add( currentPageNo );
      list.add( pageSize );
      
      Object[] param = list.toArray();
      String s = sql.toString();
      rs = DaoUtils.execute( connection, pstm, rs, s, param );
      while( rs.next() ){
        User _user = new User();
        _user.setId( rs.getInt( "id" ) );
        _user.setUserCode( rs.getString( "userCode" ) );
        _user.setUserName( rs.getString( "userName" ) );
        _user.setUserPassword( rs.getString( "userPassword" ) );
        _user.setGender( rs.getInt( "gender" ) );
        _user.setBirthday( rs.getDate( "birthday" ) );
        _user.setPhone( rs.getString( "phone" ) );
        _user.setAddress( rs.getString( "address" ) );
        _user.setUserRole( rs.getInt( "userRole" ) );
        _user.setCreatedBy( rs.getInt( "createdBy" ) );
        _user.setCreationDate( rs.getDate( "creationDate" ) );
        _user.setModifyBy( rs.getInt( "modifyBy" ) );
        _user.setModifyDate( rs.getDate( "modifyDate" ) );
        _user.setUserRoleName( rs.getString( "roleName" ) );
        userList.add( _user );
      }
      DaoUtils.close( null, pstm, rs );
    }
    
    return userList;
  }
  
  @Override
  public User getUserById( Connection connection, String userCode ) throws SQLException {
    
    PreparedStatement pstm = null;
    ResultSet rs = null;
    User user = null;
    String sql = "select u.*,ur.roleName as roleName " +
        "from user u,user_role ur " +
        "where u.userRole = ur.roleCode and u.userCode = ?";
    
    if( connection != null ){
      Object[] param = { userCode };
      rs = DaoUtils.execute( connection, pstm, rs, sql, param );
      if( rs.next() ){
        user = new User();
        user.setId( rs.getInt( "id" ) );
        user.setUserCode( rs.getString( "userCode" ) );
        user.setUserName( rs.getString( "userName" ) );
        user.setUserPassword( rs.getString( "userPassword" ) );
        user.setGender( rs.getInt( "gender" ) );
        user.setBirthday( rs.getDate( "birthday" ) );
        user.setPhone( rs.getString( "phone" ) );
        user.setAddress( rs.getString( "address" ) );
        user.setUserRole( rs.getInt( "userRole" ) );
        user.setCreatedBy( rs.getInt( "createdBy" ) );
        user.setCreationDate( rs.getDate( "creationDate" ) );
        user.setModifyBy( rs.getInt( "modifyBy" ) );
        user.setModifyDate( rs.getDate( "modifyDate" ) );
        user.setUserRoleName( rs.getString( "roleName" ) );
      }
      DaoUtils.close( null, pstm, rs );
    }
    
    return user;
  }
  
  @Override
  public int updateUser( Connection connection, int id, User user ) throws SQLException {
    PreparedStatement pstm = null;
    int result = 0;
    
    if( connection != null ){
      String sql = "update smbms_user u set u.userCode = ?,u.userName = ?,u.userPassword = ?,u.gender = ?,u.birthday " +
          "= ?,u.phone = ?,u.address = ?,u.userRole = ?,u.createdBy = ?,u.creationDate = ?,u.modifyBy = ?,u" +
          ".modifyDate = ? where u.id = ?";
      Object[] param = { user.getUserCode(), user.getUserName(), user.getUserPassword(),
          user.getGender(), user.getBirthday(), user.getPhone(), user.getAddress(), user.getUserRole(),
          user.getCreatedBy(), user.getCreationDate(), id, new Date( System.currentTimeMillis() ), user.getId() };
      result = DaoUtils.execute( connection, pstm, sql, param );
    }
    DaoUtils.close( null, pstm, null );
    
    return result;
  }
  
  @Test
  public void test() throws Exception {
    Connection connection = DaoUtils.getConnection();
    UserDao userDao = new UserDaoImpl();
    List<User> users = new ArrayList<>();
    //users = userDao.getUserList( connection, null, 0, 1, 5 );
    User user = getUserById( connection, "100001" );
    //users.forEach( user -> System.out.println( user ) );
    System.out.println( user );
  }
}
