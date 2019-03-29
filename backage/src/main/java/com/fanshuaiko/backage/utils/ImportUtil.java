package com.fanshuaiko.backage.utils;

import cn.afterturn.easypoi.excel.ExcelImportUtil;
import cn.afterturn.easypoi.excel.entity.ImportParams;
import com.fanshuaiko.backage.dao.CourseDao;
import com.fanshuaiko.backage.dict.QuestionType;
import com.fanshuaiko.backage.entity.Choice;
import com.fanshuaiko.backage.entity.Subjective;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import java.lang.reflect.Field;
import java.util.Arrays;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

/**
 * @ClassName ImportUtil
 * @Description 上传文件解析类
 * @Author fanshuaiko
 * @Date 19-3-27 下午4:05
 * @Version 1.0
 * <p>
 * * 为什么这样写：
 * *      因为在ImportUtil中注入了CourseDao并使用其方法来对上传文件中的所属课程与数据对比看是否正确
 * *      所以在使用ImportUtil的时候就不能使用new方法或者使用将方法static提供给其他类使用
 * *      不然会导致CourseDao注入失败
 * 所以目前我的解决办法是继承interface，在需要用的地方注入ImportUtilService来调用相关的方法
 **/
@Service
public class ImportUtil implements ImportUtilService {

    @Autowired
    private CourseDao courseDao;

    //选择题上传文件解析
    public ResultData checkImportChoice(MultipartFile file, String type) {
        ImportParams importParams = new ImportParams();
        importParams.setTitleRows(1);
        importParams.setHeadRows(1);
        try {
            List<Choice> choiceList = ExcelImportUtil.importExcel(file.getInputStream(), Choice.class, importParams);
            //去除空元素
            removeNullObj(choiceList);
            //判断上传文件是否为空
            if (choiceList.isEmpty()) {
                return ResultData.newResultData(ErrorCode.FAILOR, "导入数据失败，数据不能为空，请检查后重新导入");
            }
            //校验
            List<String> answerCheck = Arrays.asList("A", "B", "C", "D");
            for (Choice choice : choiceList) {
                //判断表格没列的值是否为空
                ResultData<String> data = checkChoiceNull(choice, type);
                if (data != null) {
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
            return ResultData.newSuccessResultData(choiceList);
        } catch (Exception e) {
            e.printStackTrace();
            return ResultData.newResultData(ErrorCode.ADD_FAILOR_MSG, "上传失败");

        }
    }

    //判断上传的选择题表格的每一列是否为空
    public ResultData<String> checkChoiceNull(Choice choice, String type) {
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

    //主观题上传文件解析
    public ResultData checkImportSubjective(MultipartFile file, String type) {
        ImportParams importParams = new ImportParams();
        importParams.setTitleRows(1);
        importParams.setHeadRows(1);
        try {
            List<Subjective> subjectiveList = ExcelImportUtil.importExcel(file.getInputStream(), Subjective.class, importParams);
            //去除空元素
            removeNullObj(subjectiveList);
            //判断上传文件是否为空
            if (subjectiveList.isEmpty()) {
                return ResultData.newResultData(ErrorCode.FAILOR, "导入数据失败，数据不能为空，请检查后重新导入");
            }
            for (Subjective subjective : subjectiveList) {
                ResultData<String> data = checkSubjectiveNull(subjective, type);
                if (data != null) {
                    return data;
                }
                subjective.setId(SnowflakeIdWorker.nextId());
                subjective.setType(type);
            }
            return ResultData.newSuccessResultData(subjectiveList);
        } catch (Exception e) {
            e.printStackTrace();
            return ResultData.newResultData(ErrorCode.ADD_FAILOR_MSG, "上传失败！");
        }

    }

    //判断上传的选择题表格的每一列是否为空
    public ResultData<String> checkSubjectiveNull(Subjective subjective, String type) {
        if (StringUtils.isEmpty(subjective.getQuestion())) {
            return ResultData.newResultData(ErrorCode.FAILOR, "题目不能为空！");
        } else if (StringUtils.isEmpty(subjective.getAnswer())) {
            return ResultData.newResultData(ErrorCode.FAILOR, "题目：\'" + subjective.getQuestion() + "\'的答案不能为空！");
        } else if (StringUtils.isEmpty(subjective.getCourseName())) {
            return ResultData.newResultData(ErrorCode.FAILOR, "题目：\'" + subjective.getQuestion() + "\'的所属课程不能为空！");
        } else if (courseDao.selectByName(subjective.getCourseName()) == null) {
            return ResultData.newResultData(ErrorCode.FAILOR, "题目：\'" + subjective.getQuestion() + "\'所属课程\'" + subjective.getCourseName() + "\'不存在");
        }
        return null;
    }

    //去除集合中的空对象
    public static void removeNullObj(List list) {
        try {
            LinkedList<Object> objects = new LinkedList<>();
            for (Object obj : list) {
                int count =0;
                Field[] declaredFields = obj.getClass().getDeclaredFields();
                int length = declaredFields.length;
                for (Field field : declaredFields) {
                    field.setAccessible(true);
                    if (field.get(obj) == null) {
                        count+=1;
                    }
                }
                if(count==length){
                    objects.add(obj);
                }
            }
            list.removeAll(objects);
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

}
