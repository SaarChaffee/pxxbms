/**
 * @Name: pxxbms
 * @Author: SaarChaffee
 * @Code: UTF-8
 * @Date: Created in 2022 2022/3/10
 */
package com.chaffee.service.user;

import com.chaffee.dao.DaoUtils;
import com.chaffee.dao.user.UserRoleDao;
import com.chaffee.dao.user.UserRoleDaoImpl;
import com.chaffee.entity.UserRole;
import org.junit.Test;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

public class UserRoleServiceImpl implements UserRoleService {
  private final UserRoleDao roleDao;
  
  public UserRoleServiceImpl() {
    roleDao = new UserRoleDaoImpl();
  }
  
  @Override
  public List<UserRole> getRoleList() {
    Connection connection = null;
    List<UserRole> roleList = null;
    try{
      connection = DaoUtils.getConnection();
      roleList = roleDao.getRoleList( connection );
    }catch( SQLException e ){
      e.printStackTrace();
    }finally{
      System.out.println( "------------getRoleList-service--------------" );
      System.out.println( roleList );
      System.out.println( "=============================================" );
      System.out.println();
      DaoUtils.close( connection, null, null );
    }
    return roleList;
  }
  
  @Test
  public void test() {
    UserRoleService roleService = new UserRoleServiceImpl();
    System.out.println( roleService.getRoleList() );
    
  }
}
