package com.fanshuaiko.backage.dao;

import com.fanshuaiko.backage.entity.PaperStatus;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

@Mapper
@Repository
public interface PaperStatusDao {
    int deleteByPrimaryKey(Integer id);

    int insert(PaperStatus record);

    int insertSelective(PaperStatus record);

    PaperStatus selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(PaperStatus record);

    int updateByPrimaryKey(PaperStatus record);
}