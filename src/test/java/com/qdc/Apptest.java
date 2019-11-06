package com.qdc;

import com.qdc.cust.bean.Goods;
import com.qdc.cust.service.GoodsServiceImpl;
import org.apache.poi.hssf.usermodel.HSSFDateUtil;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.*;
import org.junit.Test;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.core.io.FileSystemResource;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.mail.javamail.MimeMessageHelper;

import javax.mail.internet.MimeMessage;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

public class Apptest {
 /*   @Test
    public static void main(String[] args) {
        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("beans-quartz.xml");
    }*/

    @Test
    public void test04() throws  Exception{
        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("beans-email.xml");
        JavaMailSenderImpl bean = context.getBean(JavaMailSenderImpl.class);
        //邮件对象
        MimeMessage mimeMessage = bean.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(mimeMessage,true,"UTF-8");

        helper.setFrom("17805594005@163.com");
        helper.setTo("17805594005@163.com");
        helper.setSubject("这是0708班的邮件测试");
        helper.setText("<html><head></head><body>嘿嘿嘿<img src=cid:identify> <span style='color:red'>嘿嘿嘿嘿</span>，阿哦<h1>哦阿</h1>哦好奥</body></html>",true);
        FileSystemResource file=new FileSystemResource(new File("d:\\Desktop\\1.jpg"));
        helper.addAttachment("photo.jpg",file);
        //作为附件下载
        FileSystemResource file1=new FileSystemResource(new File("d:\\Desktop\\2.jpg"));
        helper.addInline("identify",file1);
        //发送邮件
        bean.send(mimeMessage);

        System.out.println("EMAIL PASS");
    }
    @Test
    public void test03() {
        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("beans.xml");
        GoodsServiceImpl bean = context.getBean(GoodsServiceImpl.class);
        List<Goods> goodsList = bean.getGoodsList();
        System.out.println(goodsList.size());
    }

    @Test
    public void test02() {

        try {
            Workbook wb = WorkbookFactory.create(new File("d:\\Desktop\\customers.xlsx"));
            Sheet sheet = wb.getSheetAt(0);
            int firstRowNum = sheet.getFirstRowNum();
            int lastRowNum = sheet.getLastRowNum();
            System.out.println("第一行"+firstRowNum);
            System.out.println("最后一行"+lastRowNum);

            for (int i = sheet.getFirstRowNum(); i <=sheet.getLastRowNum() ; i++) {
                    Row row=sheet.getRow(i);
                for (int j = row.getFirstCellNum(); j < row.getLastCellNum(); j++) {
                    Cell cell = row.getCell(j);
                    String value= parseExcelValueToString(cell);
                    if(i>0 && j==0){
                        value = value.substring(0, value.indexOf("."));
                    }
                    System.out.print(value+"  ");

                }
                System.out.println();
            }


        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("出异常");


        }
    }

    private String parseExcelValueToString(Cell cell) {
        SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");
         String result="";
        switch (cell.getCellType()){
            case Cell.CELL_TYPE_STRING:
                result=cell.getStringCellValue();
                break;
            case Cell.CELL_TYPE_BOOLEAN:
                result=String.valueOf(cell.getBooleanCellValue());
                break;
            case Cell.CELL_TYPE_FORMULA:
                result=cell.getCellFormula();
                break;
            case Cell.CELL_TYPE_NUMERIC:
                if(HSSFDateUtil.isCellDateFormatted(cell)){
                    Date date = cell.getDateCellValue();
                    result = sdf.format(date);
                }else {
                    result = cell.getNumericCellValue()+"";
                    BigDecimal decimal=new BigDecimal(String.valueOf(cell.getNumericCellValue()));
                    result = decimal.toPlainString();
                }
                break;
                default:
                    result="";

        }
        return result;
    }

    @Test
    public void test01(){

        Workbook wb=new HSSFWorkbook();

        //2.在表格里面创建sheet对象
        Sheet sheet1 = wb.createSheet("sheet one");
        Sheet sheet2 = wb.createSheet("sheet two");
        Sheet sheet3 = wb.createSheet("sheet three");
        Sheet sheet4 = wb.createSheet("sheet four");
        //3.创建第一行
        Row row = sheet1.createRow(0);
        //4.创建第一行的第一列
        Cell cell0 = row.createCell(0);
        Cell cell1 = row.createCell(1);
        Cell cell2 = row.createCell(2);
        //5.给单元格设置值
        cell0.setCellValue("id");
        cell1.setCellValue("序号");
        cell2.setCellValue("联系人");

        FileOutputStream fos=null;
        try {
            fos =new FileOutputStream(new File("d:\\Desktop\\customers.xlsx"));
             wb.write(fos);
            fos.close();
        } catch (IOException e) {
            e.printStackTrace();
        }finally {
            if (fos!=null){
                try {
                    fos.close();
                }catch (Exception ex){
                    ex.printStackTrace();
                }
            }
        }
    }
}
