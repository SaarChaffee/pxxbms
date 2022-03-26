/**
 * @Name: pxxbms
 * @Author: SaarChaffee
 * @Code: UTF-8
 * @Date: Created in 2022 2022/3/12
 */
package com.chaffee.service.good;

import com.chaffee.entity.GoodType;

import java.util.List;

public interface GoodTypeService {
  public List<GoodType> getGoodTypeList();
  
  public GoodType getGoodTypeById( int id );
  
  public GoodType getGoodTypeByCode( String typeCode );
  
  public boolean addGoodType( int id, GoodType goodType );
  
  public boolean updGoodType( int id, GoodType goodType );
  
  public boolean delGoodType( int id );
}
