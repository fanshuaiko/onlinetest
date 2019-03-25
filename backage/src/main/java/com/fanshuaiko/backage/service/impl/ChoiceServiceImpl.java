package com.fanshuaiko.backage.service.impl;

import cn.afterturn.easypoi.excel.ExcelImportUtil;
import cn.afterturn.easypoi.excel.entity.ImportParams;
import com.fanshuaiko.backage.dao.ChoiceDao;
import com.fanshuaiko.backage.dao.CourseDao;
import com.fanshuaiko.backage.dict.QuestionType;
import com.fanshuaiko.backage.entity.Choice;
import com.fanshuaiko.backage.entity.QuestionQueryTerm;
import com.fanshuaiko.backage.service.ChoiceService;
import com.fanshuaiko.backage.utils.ErrorCode;
import com.fanshuaiko.backage.utils.ResultData;
import com.fanshuaiko.backage.utils.SnowflakeIdWorker;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import java.util.*;

/**
 * @ClassName ChoiceServiceImpl
 * @Description
 * @Author fanshuaiko
 * @Date 19-3-22 上午10:08
 * @Version 1.0
 **/
@Service
public class ChoiceServiceImpl implements ChoiceService {

    @Autowired
    private ChoiceDao choiceDao;

    @Autowired
    CourseDao courseDao;

    @Override
    public ResultData insert(Choice choice) {
        choice.setId(SnowflakeIdWorker.nextId());//生成id
        int count = choiceDao.insert(choice);
        return ResultData.newSuccessResultData(count);
    }

    @Override
    public ResultData deleteByPrimaryKey(long id) {
        int count = choiceDao.deleteByPrimaryKey(id);
        return ResultData.newSuccessResultData(count);
    }

    public ResultData updateByPrimaryKey(Choice choice) {
        int count = choiceDao.updateByPrimaryKey(choice);
        return ResultData.newSuccessResultData(count);
    }

    public ResultData<PageInfo<Choice>> choicePageQuery(QuestionQueryTerm queryTerm) {
        PageHelper.startPage(queryTerm.getPageNum(), queryTerm.getPageSize());
        List<Choice> choiceList = choiceDao.queryByConditions(queryTerm);
        PageInfo<Choice> pageInfo = new PageInfo<>(choiceList);
        return ResultData.newSuccessResultData(pageInfo);
    }

    @Override
    public ResultData importChoice(MultipartFile file, String type) {
        ImportParams importParams = new ImportParams();
        importParams.setTitleRows(1);
        importParams.setHeadRows(1);
        try {
            List<Choice> choiceList = ExcelImportUtil.importExcel(file.getInputStream(), Choice.class, importParams);
            //去除空元素
            choiceList.removeAll(Collections.singleton(null));
            //判断上传文件是否为空
            if (choiceList.isEmpty()) {
                return ResultData.newResultData(ErrorCode.FAILOR, "导入数据失败，数据不能为空，请检查后重新导入");
            }
           //校验
            List<String> answerCheck = Arrays.asList("A", "B", "C", "D");
            for (Choice choice : choiceList) {
                //判断表格没列的值是否为空
                ResultData<String> data = checkNull(choice, type);
                if(data!=null){
                    return data;
                }
                //对答案的合法性进行校验
                String answer = choice.getAnswer().toUpperCase();
                if ((courseDao.selectByName(choice.getCourseName()) == null)) {
                    return ResultData.newResultData(ErrorCode.FAILOR, "单选题：\'" + choice.getQuestion() + "\'所属课程\'" + choice.getCourseName() + "\'不存在");
                } else if (type.equals(QuestionType.SingleChoice.getCODE())) {
                    if (answer.length() != 1) {
                        return ResultData.newResultData(ErrorCode.FAILOR, "单选题：\'" + choice.getQuestion() + "\'的答案只能为一个选项");
                    } else if (!answerCheck.contains(answer)) {
                        return ResultData.newResultData(ErrorCode.FAILOR, "题目：\'" + choice.getQuestion() + "\'的答案不能为其他字符！");
                    }
                } else if (type.equals(QuestionType.JudgeChoice.getCODE())) {
                    if (answer.length() != 1) {
                        return ResultData.newResultData(ErrorCode.FAILOR, "判断题：\'" + choice.getQuestion() + "\"的答案只能为A或者B!");
                    } else if (!answerCheck.contains(answer)) {
                        return ResultData.newResultData(ErrorCode.FAILOR, "题目：\'" + choice.getQuestion() + "\'的答案不能为其他字符！");
                    }
                } else if (type.equals(QuestionType.MultipleChoice.getCODE())) {
                    String[] split = answer.split(",");
                    if (answer.length() == 1) {
                        return ResultData.newResultData(ErrorCode.FAILOR, "多选题：\'" + choice.getQuestion() + "\'的答案不能只有一个");
                    }
                    for (String s : split) {
                        if (!answerCheck.contains(s)) {
                            return ResultData.newResultData(ErrorCode.FAILOR, "题目：\'" + choice.getQuestion() + "\'的答案不能为其他字符！");
                        }
                    }
                }
                choice.setId(SnowflakeIdWorker.nextId());
                choice.setType(type);
            }
            int count = choiceDao.batchAdd(choiceList);
            return ResultData.newSuccessResultData(count);
        } catch (Exception e) {
            e.printStackTrace();
            return ResultData.newResultData(ErrorCode.ADD_FAILOR_MSG, ErrorCode.ADD_FAILOR_MSG);

        }
    }

    //判断上传的选择题表格的每一列是否为空
    public static ResultData<String> checkNull(Choice choice, String type) {
        if (StringUtils.isEmpty(choice.getQuestion())) {
            return ResultData.newResultData(ErrorCode.FAILOR, "题目不能为空！");
        } else if (StringUtils.isEmpty(choice.getChoiceA())) {
            return ResultData.newResultData(ErrorCode.FAILOR, "题目：\'" + choice.getQuestion() + "\'的选项A不能为空！");
        } else if (StringUtils.isEmpty(choice.getChoiceB())) {
            return ResultData.newResultData(ErrorCode.FAILOR, "题目：\'" + choice.getQuestion() + "\'的选项B不能为空！");
        } else if (!type.equals(QuestionType.JudgeChoice.getCODE())) {
            if (StringUtils.isEmpty(choice.getChoiceC())) {
                return ResultData.newResultData(ErrorCode.FAILOR, "题目：\'" + choice.getQuestion() + "\'的选项C不能为空！");
            } else if (StringUtils.isEmpty(choice.getChoiceD())) {
                return ResultData.newResultData(ErrorCode.FAILOR, "题目：\'" + choice.getQuestion() + "\'的选项D不能为空！");
            }
        } else if (StringUtils.isEmpty(choice.getAnswer())) {
            return ResultData.newResultData(ErrorCode.FAILOR, "题目：\'" + choice.getQuestion() + "\'的答案不能为空！");
        } else if (StringUtils.isEmpty(choice.getCourseName())) {
            return ResultData.newResultData(ErrorCode.FAILOR, "题目：\'" + choice.getQuestion() + "\'的所属课程不能为空！");
        }
        return null;
    }
}
