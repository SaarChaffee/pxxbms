/**
 * @Name: pxxbms
 * @Author: SaarChaffee
 * @Code: UTF-8
 * @Date: Created in 2022 2022/3/1
 */
package com.chaffee.dao.user;

import com.chaffee.entity.UserRole;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

public interface UserRoleDao {
  /**
   * 获取用户角色列表
   *
   * @param connection
   * @return
   * @throws SQLException
   */
  public List<UserRole> getRoleList( Connection connection ) throws SQLException;
}
