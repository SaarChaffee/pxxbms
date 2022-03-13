/**
 * @Name: pxxbms
 * @Author: SaarChaffee
 * @Code: UTF-8
 * @Date: Created in 2022 2022/3/12
 */
package com.chaffee.service.good;

import com.chaffee.entity.Good;

import java.util.List;

public interface GoodService {
  public List<Good> getGoodList( String goodName, String ownerName, int goodType, int currentPageNo, int pageSize );
  
  public int getGoodCount( String goodName, String ownerName, int goodType );
  
  public Good getGoodById(int id);
  
  public boolean updateGood(int id,Good good);
}
