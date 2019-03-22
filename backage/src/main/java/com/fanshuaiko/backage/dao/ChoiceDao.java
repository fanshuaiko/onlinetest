package com.fanshuaiko.backage.dao;

import com.fanshuaiko.backage.entity.Choice;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;
@Mapper
@Repository
public interface ChoiceDao {
    int deleteByPrimaryKey(Long id);

    int insert(Choice record);

    int insertSelective(Choice record);

    Choice selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(Choice record);

    int updateByPrimaryKey(Choice record);

}