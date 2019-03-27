package com.fanshuaiko.backage.service.impl;

import cn.afterturn.easypoi.excel.ExcelImportUtil;
import cn.afterturn.easypoi.excel.entity.ImportParams;
import com.fanshuaiko.backage.dao.CourseDao;
import com.fanshuaiko.backage.dao.SubjectiveDao;
import com.fanshuaiko.backage.dict.QuestionType;
import com.fanshuaiko.backage.entity.Choice;
import com.fanshuaiko.backage.entity.QuestionQueryTerm;
import com.fanshuaiko.backage.entity.Subjective;
import com.fanshuaiko.backage.service.SubjectiveService;
import com.fanshuaiko.backage.utils.ErrorCode;
import com.fanshuaiko.backage.utils.ImportUtil;
import com.fanshuaiko.backage.utils.ResultData;
import com.fanshuaiko.backage.utils.SnowflakeIdWorker;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import java.util.Collections;
import java.util.List;

/**
 * @ClassName SubjectiveServiceImpl
 * @Description
 * @Author fanshuaiko
 * @Date 19-3-25 下午4:35
 * @Version 1.0
 **/
@Service
public class SubjectiveServiceImpl implements SubjectiveService {
    @Autowired
    private SubjectiveDao subjectiveDao;

    @Autowired
    CourseDao courseDao;

    @Override
    public ResultData insert(Subjective subjective) {
        subjective.setId(SnowflakeIdWorker.nextId());//生成id
        int count = subjectiveDao.insert(subjective);
        return ResultData.newSuccessResultData(count);
    }

    @Override
    public ResultData deleteByPrimaryKey(long id) {
        int count = subjectiveDao.deleteByPrimaryKey(id);
        return ResultData.newSuccessResultData(count);
    }

    @Override
    public ResultData updateByPrimaryKey(Subjective subjective) {
        int count = subjectiveDao.updateByPrimaryKeySelective(subjective);
        return ResultData.newSuccessResultData(count);
    }

    @Override
    public ResultData<PageInfo<Subjective>> subjectivePageQuery(QuestionQueryTerm queryTerm) {
        PageHelper.startPage(queryTerm.getPageNum(), queryTerm.getPageSize());
        List<Subjective> subjectiveList = subjectiveDao.queryByConditions(queryTerm);
        PageInfo<Subjective> pageInfo = new PageInfo<>(subjectiveList);
        return ResultData.newSuccessResultData(pageInfo);
    }
    @Override

    public ResultData importChoice(MultipartFile file, String type){
        try {
            ResultData resultData = ImportUtil.checkImportSubjective(file, type);
            if (resultData.getData() == null) {
                return resultData;
            }
            int count = subjectiveDao.batchAdd((List<Subjective>) resultData.getData());
            return ResultData.newSuccessResultData(count);
        } catch (Exception e) {
            e.printStackTrace();
            return ResultData.newResultData(ErrorCode.ADD_FAILOR_MSG, ErrorCode.ADD_FAILOR_MSG);
        }
    }


}
