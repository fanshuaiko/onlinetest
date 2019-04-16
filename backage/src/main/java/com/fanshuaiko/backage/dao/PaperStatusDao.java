package com.fanshuaiko.backage.dao;

import com.fanshuaiko.backage.entity.PaperStatus;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface PaperStatusDao {
    int deleteByPrimaryKey(Integer id);

    int insert(PaperStatus record);

    int insertSelective(PaperStatus record);

    PaperStatus selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(PaperStatus record);

    int updateByPrimaryKey(PaperStatus record);

    List<PaperStatus> selectAll();//查询所有

    int updateChoiceStatusByTestNo(long testNo);//更新选择题批改状态为已批改

    int updateSubjectiveStatusByTestNo(long testNo);//更新主观题批改状态为已批改
}