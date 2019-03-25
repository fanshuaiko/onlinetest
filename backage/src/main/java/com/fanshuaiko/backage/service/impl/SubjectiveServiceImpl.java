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
    public ResultData importChoice(MultipartFile file, String type) {
        ImportParams importParams = new ImportParams();
        importParams.setTitleRows(1);
        importParams.setHeadRows(1);
        try {
            List<Subjective> subjectiveList = ExcelImportUtil.importExcel(file.getInputStream(), Subjective.class, importParams);
            //去除空元素
            subjectiveList.removeAll(Collections.singleton(null));
            //判断上传文件是否为空
            if (subjectiveList.isEmpty()) {
                return ResultData.newResultData(ErrorCode.FAILOR, "导入数据失败，数据不能为空，请检查后重新导入");
            }
            for (Subjective subjective : subjectiveList) {
                ResultData<String> data = checkNull(subjective, type);
                if (data != null) {
                    return data;
                }
                subjective.setId(SnowflakeIdWorker.nextId());
                subjective.setType(type);
            }
            int count = subjectiveDao.batchAdd(subjectiveList);
            return ResultData.newSuccessResultData(count);
        } catch (Exception e) {
            e.printStackTrace();
            return ResultData.newResultData(ErrorCode.ADD_FAILOR_MSG, ErrorCode.ADD_FAILOR_MSG);
        }

    }

    //判断上传的选择题表格的每一列是否为空
    public static ResultData<String> checkNull(Subjective subjective, String type) {
        if (StringUtils.isEmpty(subjective.getQuestion())) {
            return ResultData.newResultData(ErrorCode.FAILOR, "题目不能为空！");
        } else if (StringUtils.isEmpty(subjective.getAnswer())) {
            return ResultData.newResultData(ErrorCode.FAILOR, "题目：\'" + subjective.getQuestion() + "\'的答案不能为空！");
        } else if (StringUtils.isEmpty(subjective.getCourseName())) {
            return ResultData.newResultData(ErrorCode.FAILOR, "题目：\'" + subjective.getQuestion() + "\'的所属课程不能为空！");
        }
        return null;
    }
}
