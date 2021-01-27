package com.xishan.store.activity.server.mapper;


import com.xishan.store.activity.api.model.Recepit;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface RecepitMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Recepit record);

    int insertSelective(Recepit record);

    Recepit selectByPrimaryKey(Integer id);

    List<Recepit> listByCondition(Recepit record);

    int updateByPrimaryKeySelective(Recepit record);

    int updateByPrimaryKey(Recepit record);
}