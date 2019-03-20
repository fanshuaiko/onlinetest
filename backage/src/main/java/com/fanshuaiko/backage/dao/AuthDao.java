package com.fanshuaiko.backage.dao;

import com.fanshuaiko.backage.entity.Auth;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

/**
 * @ClassName AuthDao
 * @Description
 * @Author fanshuaiko
 * @Date 19-3-20 下午4:09
 * @Version 1.0
 **/
@Mapper
@Repository
public interface AuthDao {

    @Select("select password from auth where no = #{no}")
    String getPassByNo(String no);

    @Select("select * from auth where no = #{no}")
    Auth getAuthByNo(String no);
}
