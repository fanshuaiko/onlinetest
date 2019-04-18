package com.fanshuaiko.backage.utils;

import cn.afterturn.easypoi.excel.ExcelExportUtil;
import cn.afterturn.easypoi.excel.entity.ExportParams;
import com.fanshuaiko.backage.entity.VO.ScoreQueryTerm;
import com.fanshuaiko.backage.entity.VO.ScoreReturnVo;
import org.apache.poi.ss.usermodel.Workbook;

import javax.servlet.http.HttpServletResponse;
import java.io.BufferedOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.List;

/**
 * @ClassName ExportUtil
 * @Description 文件下载工具类
 * @Author fanshuaiko
 * @Date 19-4-3 下午2:48
 * @Version 1.0
 **/
public class ExportUtil {
    public static void exportExcel(HttpServletResponse response, InputStream in, String name) {
        try {
            byte[] buffer = new byte[in.available()];
            in.read(buffer);
            in.close();
            String fileName = URLEncoder.encode(name, "UTF-8");
            response.setCharacterEncoding("UTF-8");
            response.setHeader("Content-disposition", "attachment;filename=" + fileName);
            BufferedOutputStream outputStream = new BufferedOutputStream(response.getOutputStream());
            response.setContentType("application/octet-stream");
            outputStream.write(buffer);
            outputStream.flush();
            outputStream.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * 导出分数表
     * @param response
     * @param scoreReturnVoList
     */
    public static void exportScoreExcel(HttpServletResponse response, List<ScoreReturnVo> scoreReturnVoList) {
        try {
            ExportParams exportParams = new ExportParams("学生成绩表", "学生成绩统计表");
            Workbook workbook = ExcelExportUtil.exportExcel(exportParams, ScoreReturnVo.class, scoreReturnVoList);

            String name = "学生成绩统计表.xls";
            String fileName = URLEncoder.encode(name, "UTF-8");
            response.setCharacterEncoding("UTF-8");
            response.setHeader("Content-disposition", "attachment;filename=" + fileName);
            BufferedOutputStream outputStream = new BufferedOutputStream(response.getOutputStream());
            response.setContentType("application/octet-stream");
            workbook.write(outputStream);
            outputStream.flush();
            outputStream.close();
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
