/**
 * @Name: pxxbms
 * @Author: SaarChaffee
 * @Code: UTF-8
 * @Date: Created in 2022 2022/3/1
 */
package com.chaffee.dao.user;

import com.chaffee.dao.DaoUtils;
import com.chaffee.entity.User;
import com.chaffee.entity.UserRole;
import org.junit.Test;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class UserRoleDaoImpl implements UserRoleDao {
  @Override
  public List<UserRole> getRoleList( Connection connection ) throws SQLException {
    PreparedStatement pstm = null;
    ResultSet rs = null;
    Object[] param = {};
    List<UserRole> roleList = new ArrayList<>();
    
    if( connection != null ){
      String sql = "select * from user_role";
      rs = DaoUtils.execute( connection, pstm, rs, sql, param );
      while( rs.next() ){
        UserRole _role = new UserRole();
        _role.setId( rs.getInt( "id" ) );
        _role.setRoleCode( rs.getInt( "roleCode" ) );
        _role.setRoleName( rs.getString( "roleName" ) );
        _role.setCreatedBy( rs.getInt( "createdBy" ) );
        _role.setCreationDate( rs.getDate( "creationDate" ) );
        _role.setModifyBy( rs.getInt( "modifyBy" ) );
        _role.setModifyDate( rs.getDate( "modifyDate" ) );
        roleList.add( _role );
      }
    }
    DaoUtils.close( null, pstm, rs );
    
    return roleList;
  }
  
  @Test
  public void test() throws SQLException {
    UserRoleDao roleDao = new UserRoleDaoImpl();
    List<UserRole> roleList = new ArrayList<>();
    PreparedStatement pstm = null;
    ResultSet rs = null;
    User user = null;
    String userCode = null;
    int role = 0;
    Connection connection = DaoUtils.getConnection();
    roleList = roleDao.getRoleList( connection );
    for( UserRole role1 : roleList ){
      System.out.println( role1.toString() );
    }
  }
}
