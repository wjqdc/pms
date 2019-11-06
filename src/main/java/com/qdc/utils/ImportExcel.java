package com.qdc.utils;

import com.fasterxml.jackson.databind.exc.InvalidFormatException;
import com.qdc.cust.bean.Customer;
import org.apache.poi.ss.usermodel.*;


import java.io.IOException;
import java.io.InputStream;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

import static com.qdc.utils.ExcelUtils.parseExcelValueToString;

public class ImportExcel {

    //上传Excel文件，返回List<Customer>集合
    public static List<Customer> excelToList(InputStream file) throws Exception, IOException, InvalidFormatException {
        DateUtils dateUtils = new DateUtils();
        Workbook workbook = null;
        workbook = WorkbookFactory.create(file);
        List<Customer> list= new ArrayList<>();
        for (int k = 0; k < workbook.getNumberOfSheets(); k++) {
            Sheet sheet = workbook.getSheetAt(k);
            for (int i = sheet.getFirstRowNum()+1; i <= sheet.getLastRowNum(); i++) {
                Row row = sheet.getRow(i);
                Customer customer = new Customer();
                for (int j = row.getFirstCellNum(); j < row.getLastCellNum(); j++) {
                    Cell cell = row.getCell(j);
                    String value = parseExcelValueToString (cell);
                    if (j==0){
                        value = value.substring(0,value.indexOf("."));
                        customer.setId(Integer.parseInt(value));
                    } else if (j==1){
                        customer.setCompanyperson(value);
                    } else if (j==2){
                        customer.setComname(value);
                    } else if (j==3){
                        customer.setAddtime(dateUtils.StringToDate(value));
                    } else if (j==4){
                        customer.setComphone(value);
                    } else if (j==5){
                        customer.setComaddress(value);
                    } else if (j==6){
                        customer.setCamera(value);
                    } else if (j==7){
                        customer.setPresent(value);
                    } else if (j==8){
                        customer.setRemark(value);

                    }
                }
                list.add(customer);
            }
        }
        return  list;
    }
    
}
