package com.fanshuaiko.backage.dao;

import com.fanshuaiko.backage.entity.Institute;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface InstituteDao {
    int deleteByPrimaryKey(Integer id);

    int insert(Institute record);

    int insertSelective(Institute record);

    Institute selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Institute record);

    int updateByPrimaryKey(Institute record);

    List<Institute> selectByCollegeNo(String collegeNo); //根据学校编码查询学院
}