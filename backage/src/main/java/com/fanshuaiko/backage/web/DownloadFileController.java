package com.fanshuaiko.backage.web;

import com.fanshuaiko.backage.utils.ErrorCode;
import com.fanshuaiko.backage.utils.ExportUtil;
import com.fanshuaiko.backage.utils.ResultData;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;
import java.io.InputStream;

/**
 * @ClassName downloadTemplateController
 * @Description 题目上传模板下载
 * @Author fanshuaiko
 * @Date 19-4-3 下午2:30
 * @Version 1.0
 **/
@RestController
@RequestMapping("/file")
public class DownloadFileController {
    private static Logger log = LoggerFactory.getLogger(ClassController.class);

    @GetMapping("/single")
    public ResultData downloadSingleChoiceTemplate(HttpServletResponse response) {
        try {
            InputStream in = DownloadFileController.class.getClassLoader().getResourceAsStream("importSingleChoice.xls");
            ExportUtil.exportExcel(response, in, "单选题上传模板.xls");
            return ResultData.newSuccessResultData();
        } catch (Exception e) {
            log.info("--------file/downloadSingleChoice:--------");
            e.printStackTrace();
            return ResultData.newResultData(ErrorCode.FAILOR, "下载单选题上传模板失败！");
        }
    }

    @GetMapping("/judge")
    public ResultData downloadJudgeChoiceTemplate(HttpServletResponse response) {
        try {
            InputStream in = DownloadFileController.class.getClassLoader().getResourceAsStream("importJudgeChoice.xls");
            ExportUtil.exportExcel(response, in, "判断题上传模板.xls");
            return ResultData.newSuccessResultData();
        } catch (Exception e) {
            log.info("--------file/downloadJudgeChoiceTemplate:--------");
            e.printStackTrace();
            return ResultData.newResultData(ErrorCode.FAILOR, "下载判断题上传模板失败！");
        }
    }

    @GetMapping("/multiple")
    public ResultData downloadMultipleChoiceTemplate(HttpServletResponse response) {
        try {

            InputStream in = DownloadFileController.class.getClassLoader().getResourceAsStream("importMultipleChoice.xls");
            ExportUtil.exportExcel(response, in, "多选题上传模板.xls");
            return ResultData.newSuccessResultData();
        } catch (Exception e) {
            log.info("--------file/downloadMultipleChoiceTemplate:--------");
            e.printStackTrace();
            return ResultData.newResultData(ErrorCode.FAILOR, "下载多选题上传模板失败！");
        }
    }

    @GetMapping("/subjective")
    public ResultData downloadSubjectiveTemplate(HttpServletResponse response) {
        try {

            InputStream in = DownloadFileController.class.getClassLoader().getResourceAsStream("importSubjective.xls");
            ExportUtil.exportExcel(response, in, "主观题上传模板.xls");
            return ResultData.newSuccessResultData();
        } catch (Exception e) {
            log.info("--------file/downloadSubjectiveTemplate:--------");
            e.printStackTrace();
            return ResultData.newResultData(ErrorCode.FAILOR, "下载主观题上传模板失败！");
        }
    }
}
