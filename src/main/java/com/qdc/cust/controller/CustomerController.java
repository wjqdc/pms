package com.qdc.cust.controller;


import com.qdc.cust.bean.Customer;
import com.qdc.cust.service.CustomerService;
import com.qdc.utils.ExcelUtils;
import com.qdc.utils.ImportExcel;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import java.io.*;
import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.*;

@Controller
@RequestMapping("/cust")
public class CustomerController {
     @Autowired
     private CustomerService customerService;


     //导入Excel
     @RequestMapping(value = "/importExcel",method = RequestMethod.POST)
     @ResponseBody
     public Map<String,Object> importExcel(MultipartFile excel) throws Exception {
      ImportExcel importExcel=new ImportExcel();
         Map<String,Object> map=new HashMap<>();
         InputStream inputStream=excel.getInputStream();
         List<Customer> customers = importExcel.excelToList(excel.getInputStream());
         if(customers!=null||customers.size()>0){
             map.put("message","上传成功");
             //customerService.importExcel(customers);
         }else {
             map.put("message","上传失败");
         }

/*
         List<Customer> list=new ArrayList<>();
         SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");
         try {
             Workbook wb = WorkbookFactory.create(excel.getInputStream());

             //解析有多少sheet
             for (int k = 0; k <wb.getNumberOfSheets() ; k++) {
                 Sheet sheet = wb.getSheetAt(k);
                 if (sheet==null){
                     break;
                 }
                 int firstRowNum = sheet.getFirstRowNum();
                 int lastRowNum = sheet.getLastRowNum();
                 //获取每一行
                 for (int i = sheet.getFirstRowNum()+1; i <=sheet.getLastRowNum() ; i++) {
                     Row row=sheet.getRow(i);
                     Customer customer=new Customer();
                     if(row!=null){
                         String linkMan = row.getCell(1).getStringCellValue();
                          customer.setCompanyperson(linkMan);
                         String companyName = row.getCell(2).getStringCellValue();
                          customer.setComname(companyName);
                         Date time = row.getCell(3).getDateCellValue();
                         String format = sdf.format(time);
                         Date addTime = sdf.parse(format);
                         customer.setAddtime(addTime);
                         double numericCellValue = row.getCell(4).getNumericCellValue();
                         BigDecimal decimal=new BigDecimal(String.valueOf(numericCellValue));
                         customer.setComphone(decimal.toPlainString());
                         //获取每行里的每一列
                        *//* for (int j = row.getFirstCellNum(); j < row.getLastCellNum(); j++) {
                             Cell cell = row.getCell(j);
                             String value= ExcelUtils.parseExcelValueToString(cell);
                             if(i>0 && j==0){
                                 value = value.substring(0, value.indexOf("."));
                             }
                             System.out.print(value+"  ");
                         }*//*
                         System.out.println();
                     }
                 }
             }
             customerService.importExcel(excel);
             map.put("statusCode",200);
             map.put("message","上传成功");

         } catch (Exception e) {
             e.printStackTrace();
             System.out.println("出异常");
             map.put("statusCode",200);
             map.put("message","上传成功");
         }*/
      return  map;
     }

     //导出Excel
     @RequestMapping(value = "/exportExcel",method = RequestMethod.GET)
     @ResponseBody
     public Map<String,Object> exportExcel() throws FileNotFoundException {
         //1.创建Excel表格
         Workbook wb=new HSSFWorkbook();
         //2.在表格里面创建sheet对象
         Sheet sheet=wb.createSheet("customer");
         //3.创建第一行
         Row row=sheet.createRow(0);
         //4.创建第一行的第一列
         Cell[] cell=new HSSFCell[5];
         for (int i = 0; i < 5; i++) {
             cell[i]=row.createCell(i);
         }
         cell[0].setCellValue("ID");
         cell[1].setCellValue("联系人");
         cell[2].setCellValue("公司名称");
         cell[3].setCellValue("添加时间");
         cell[4].setCellValue("联系电话");

         SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");

         List<Customer> custmerList = customerService.getCustmerList();
         for (int i = 0; i < custmerList.size(); i++) {
           Customer customer=custmerList.get(i);
           Row row1=sheet.createRow(i+1);
           Cell[] cells =new HSSFCell[5];
             for (int j = 0; j <5 ; j++) {
                 cells[j]=row1.createCell(j);
             }
             cells[0].setCellValue(customer.getId());
             cells[1].setCellValue(customer.getCompanyperson());
             cells[2].setCellValue(customer.getComname());
             Date addtime = customer.getAddtime();
             String format = sdf.format(addtime);
             cells[3].setCellValue(format);
             cells[4].setCellValue(customer.getComphone());
             FileOutputStream fos=null;
             try {
                 fos =new FileOutputStream(new File("d:\\Desktop\\customers.xls"));
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
         Map<String,Object> map=new HashMap<String, Object>();
         map.put("code",200);
         map.put("message","下载成功");
         return  map;
     }

    @RequestMapping(value = "/info/{id}",method=RequestMethod.GET)
    @ResponseBody
    public Customer info(@PathVariable("id") Integer cid){
        Customer custormer= customerService.getDetailById(cid);
        return custormer;
    }


    @RequestMapping(value = "/jsonList",method = RequestMethod.GET)
    @ResponseBody
    public List<Customer> getCustomerJsonList(){
        return customerService.getCustmerList();
    }

    @RequestMapping(value = "/search",method = RequestMethod.GET)
    public ModelAndView seaech(Integer cid,String keyword,Integer orderby){
        List<Customer> list= customerService.serach(cid,keyword,orderby);
        ModelAndView mv=new ModelAndView("customer");
        mv.addObject("list",list);
        return mv;
    }

    @RequestMapping(value = "/del",method = RequestMethod.DELETE)
    @ResponseBody
    public Map<String,Object> deletecustormer(@RequestParam("ids[]") Integer[] ids){
        boolean result=customerService.deletecustormer(ids);
        Map<String,Object> map=new HashMap<String, Object>();
        if(result){
            map.put("statusCode",200);
            map.put("message","删除成功");
        }else{
            map.put("statusCode",500);
            map.put("message","删除失败");
        }
        return map;
    }

    @RequestMapping(value = "/update",method = RequestMethod.PUT)
    public  String updateCustomerById(Customer customer){
        customerService.updateCustomerById(customer);
        return  "redirect:/cust/list";
    }

    @RequestMapping(value = "/edit/{id}",method = RequestMethod.GET)
    public String edit(@PathVariable("id") Integer cid, Map<String,Object>  map){
        Customer custormer= customerService.getDetailById(cid);
        map.put("customer",custormer);
        return "customer-edit";
    }

    @RequestMapping(value = "/detail/{id}",method = RequestMethod.GET)
    public String getDetailById(@PathVariable("id") Integer cid, Map<String,Object>  map){
           Customer custormer= customerService.getDetailById(cid);
           map.put("customer",custormer) ;
           return "customer-look";
    }



     @RequestMapping(value = "/list",method = RequestMethod.GET)
     public ModelAndView getCustmerList(){
         List<Customer> list=customerService.getCustmerList();
         ModelAndView mv=new ModelAndView("customer");
         mv.addObject("list",list);
         return mv;
     }

    @RequestMapping(value = "/savaInfo",method = RequestMethod.POST)
    public  String savaInfo(Customer customer){
        customerService.savaInfo(customer);
     return  "redirect:/cust/list";
    }
}
