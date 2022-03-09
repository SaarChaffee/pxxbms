/**
 * @Name: pxxbms
 * @Author: SaarChaffee
 * @Code: UTF-8
 * @Date: Created in 2022 2022/3/9
 */
package com.chaffee.service.user;

import com.chaffee.entity.User;

public interface UserService {
  public User userLogin(String userCode,String userPwd);
  
  public boolean updatePwd(String userCode,int id,String userPwd);
}
