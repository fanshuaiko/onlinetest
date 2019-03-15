package com.fanshuaiko.bakage.utils;


public interface ErrorCode {
    String SUCCESS = "0";
    String SUCCESS_MSG = "SUCCESS";
    String FAILOR = "9999";
    String FAILOR_MSG = "操作失败";
    String PAGE_FAILOR = "9000";
    String PAGE_FAILOR_MSG = "分页参数为空";
    String ADD_FAILOR = "9901";
    String ADD_FAILOR_MSG = "新增操作失败";
    String DEL_FAILOR = "9902";
    String DEL_FAILOR_MSG = "删除操作失败";
    String QUERY_FAILOR = "9903";
    String QUERY_FAILOR_MSG = "查询操作失败";
    String UPDATE_FAILOR = "9904";
    String UPDATE_FAILOR_MSG = "更新操作失败";
    String DEL_ID_FAILOR = "9905";
    String DEL_ID_FAILOR_MSG = "删除ID为空";
    String VALIDATION_ERROR = "1000";
}
