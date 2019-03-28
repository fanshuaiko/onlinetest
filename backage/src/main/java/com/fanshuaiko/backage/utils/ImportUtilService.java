package com.fanshuaiko.backage.utils;

import org.springframework.web.multipart.MultipartFile;

/**
 * @ClassName ImportUtilServive
 * @Description
 * @Author fanshuaiko
 * @Date 19-3-28 下午2:04
 * @Version 1.0
 *
 * 为什么这样写：
 *      因为在ImportUtil中注入了CourseDao并使用其方法来对上传文件中的所属课程与数据对比看是否正确
 *      所以在使用ImportUtil的时候就不能使用new方法或者使用将方法static提供给其他类使用
 *      不然会导致CourseDao注入失败
 *      所以目前我的解决办法是继承interface，在需要用的地方注入ImportUtilService来调用相关的方法
 **/
public interface ImportUtilService {
    ResultData checkImportChoice(MultipartFile file, String type);

    ResultData checkImportSubjective(MultipartFile file, String type);
}
