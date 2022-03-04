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
  
  @Test
  public void test() throws Exception {
    Connection connection = DaoUtils.getConnection();
    UserDao userDao = new UserDaoImpl();
    System.out.println( userDao.getUserList( connection, null, 0, 1, 5 ) );
  }
}
