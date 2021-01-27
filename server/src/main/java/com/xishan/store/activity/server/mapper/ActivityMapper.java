package com.xishan.store.activity.server.mapper;


import com.xishan.store.activity.api.model.Activity;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface ActivityMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Activity record);

    int insertSelective(Activity record);

    Activity selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Activity record);

    int updateByPrimaryKey(Activity record);

    List<Activity> listByConditionValid(Activity record);
    List<Activity> listByConditionInValid(Activity record);
}