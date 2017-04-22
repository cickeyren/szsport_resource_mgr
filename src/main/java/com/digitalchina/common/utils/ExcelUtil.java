package com.digitalchina.common.utils;

import com.digitalchina.sport.mgr.resource.model.StatisticsByPaytype;
import org.apache.poi.hssf.util.HSSFColor;
import org.apache.poi.xssf.usermodel.*;

import javax.servlet.ServletOutputStream;
import java.text.SimpleDateFormat;
import java.util.List;

/**
 * Created by yang_ on 2017/4/21.
 */
public class ExcelUtil {
    /**
     * 导出Excel
     * @param list 查询出来的结果集
     * @param outputStream 输出流
     * @throws Exception
     */
    public static void exportExcelForTradeUnit(String[] titles, List<StatisticsByPaytype> list, ServletOutputStream outputStream) throws Exception {
        // 创建一个workbook 对应一个excel应用文件
        XSSFWorkbook workBook = new XSSFWorkbook();
        // 在workbook中添加一个sheet,对应Excel文件中的sheet
        //Sheet名称，可以自定义中文名称
        XSSFSheet sheet = workBook.createSheet("Sheet1");
        ExportInternalUtil exportUtil = new ExcelUtil.ExportInternalUtil(workBook, sheet);
        XSSFCellStyle headStyle = exportUtil.getHeadStyle();
        XSSFCellStyle bodyStyle = exportUtil.getBodyStyle();
        // 构建表头
        XSSFRow headRow = sheet.createRow(0);
        XSSFCell cell = null;

        // 输出标题
        for (int i = 0; i < titles.length; i++) {
            cell = headRow.createCell(i);
            cell.setCellStyle(headStyle);
            cell.setCellValue(titles[i]);
        }
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        // 构建表体数据
        for (int j = 0; j < list.size(); j++) {
            XSSFRow bodyRow = sheet.createRow(j + 1);
            StatisticsByPaytype tradeUnit = list.get(j);
            cell = bodyRow.createCell(0);
            cell.setCellStyle(bodyStyle);
            sheet.setColumnWidth(0,1500);
            cell.setCellValue(String.valueOf(j+1));

            cell = bodyRow.createCell(1);
            cell.setCellStyle(bodyStyle);
            sheet.setColumnWidth(1,3000);
            cell.setCellValue(tradeUnit.getCreateDate());

            //区属转换
            String registerArea = tradeUnit.getStadiumId();
            cell = bodyRow.createCell(2);
            cell.setCellStyle(bodyStyle);
            sheet.setColumnWidth(2,3000);
            cell.setCellValue(registerArea);

            cell = bodyRow.createCell(3);
            cell.setCellStyle(bodyStyle);
            sheet.setColumnWidth(3,3500);
            cell.setCellValue(tradeUnit.getStadiumId());

            cell = bodyRow.createCell(4);
            cell.setCellStyle(bodyStyle);
            sheet.setColumnWidth(4,5000);
            cell.setCellValue(tradeUnit.getStadiumId());

            cell = bodyRow.createCell(5);
            cell.setCellStyle(bodyStyle);
            sheet.setColumnWidth(5,8000);
            cell.setCellValue(tradeUnit.getStadiumId());

//            DecimalFormat df = new DecimalFormat("#,##0.00");//保留两位小数且不用科学计数法

            cell = bodyRow.createCell(6);
            cell.setCellStyle(bodyStyle);
            sheet.setColumnWidth(6,3000);
            cell.setCellValue(tradeUnit.getStadiumId());

            cell = bodyRow.createCell(7);
            cell.setCellStyle(bodyStyle);
            sheet.setColumnWidth(7,5000);
            cell.setCellValue(formatter.format(tradeUnit.getStadiumId()));
        }
        workBook.write(outputStream);
        outputStream.flush();
        outputStream.close();
    }

    private static class ExportInternalUtil {

        private XSSFWorkbook wb = null;
        private XSSFSheet sheet = null;

        /**
         * @param wb
         * @param sheet
         */
        public ExportInternalUtil(XSSFWorkbook wb, XSSFSheet sheet) {
            this.wb = wb;
            this.sheet = sheet;
        }

        /**
         * 设置表头的单元格样式
         *
         * @return
         */
        public XSSFCellStyle getHeadStyle() {
            // 创建单元格样式
            XSSFCellStyle cellStyle = wb.createCellStyle();
            // 设置单元格的背景颜色为淡蓝色
            cellStyle.setFillForegroundColor(HSSFColor.PALE_BLUE.index);
            cellStyle.setFillPattern(XSSFCellStyle.SOLID_FOREGROUND);
            // 设置单元格居中对齐
            cellStyle.setAlignment(XSSFCellStyle.ALIGN_CENTER);
            // 设置单元格垂直居中对齐
            cellStyle.setVerticalAlignment(XSSFCellStyle.VERTICAL_CENTER);
            // 创建单元格内容显示不下时自动换行
            cellStyle.setWrapText(true);
            // 设置单元格字体样式
            XSSFFont font = wb.createFont();
            // 设置字体加粗
            font.setBoldweight(XSSFFont.BOLDWEIGHT_BOLD);
            font.setFontName("宋体");
            font.setFontHeight((short) 200);
            cellStyle.setFont(font);
            // 设置单元格边框为细线条
            cellStyle.setBorderLeft(XSSFCellStyle.BORDER_THIN);
            cellStyle.setBorderBottom(XSSFCellStyle.BORDER_THIN);
            cellStyle.setBorderRight(XSSFCellStyle.BORDER_THIN);
            cellStyle.setBorderTop(XSSFCellStyle.BORDER_THIN);
            return cellStyle;
        }

        /**
         * 设置表体的单元格样式
         *
         * @return
         */
        public XSSFCellStyle getBodyStyle() {
            // 创建单元格样式
            XSSFCellStyle cellStyle = wb.createCellStyle();
            // 设置单元格居中对齐
            cellStyle.setAlignment(XSSFCellStyle.ALIGN_CENTER);
            // 设置单元格垂直居中对齐
            cellStyle.setVerticalAlignment(XSSFCellStyle.VERTICAL_CENTER);
            // 创建单元格内容显示不下时自动换行
            cellStyle.setWrapText(true);
            // 设置单元格字体样式
            XSSFFont font = wb.createFont();
            // 设置字体加粗
//            font.setBoldweight(XSSFFont.BOLDWEIGHT_BOLD);
            font.setFontName("宋体");
            font.setFontHeight((short) 200);
            cellStyle.setFont(font);
            // 设置单元格边框为细线条
            cellStyle.setBorderLeft(XSSFCellStyle.BORDER_THIN);
            cellStyle.setBorderBottom(XSSFCellStyle.BORDER_THIN);
            cellStyle.setBorderRight(XSSFCellStyle.BORDER_THIN);
            cellStyle.setBorderTop(XSSFCellStyle.BORDER_THIN);
            //set date format
            //XSSFDataFormat format = wb.createDataFormat();
            //cellStyle.setDataFormat(format.getFormat("yyyy-MM-dd"));
            return cellStyle;
        }
    }
}
