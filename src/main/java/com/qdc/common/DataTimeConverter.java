package com.qdc.common;


import org.springframework.core.convert.converter.Converter;
import java.text.SimpleDateFormat;
import java.util.Date;

public class DataTimeConverter implements Converter<String, Date> {

    public Date convert(String source) {

        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        try{
            Date date = sdf.parse(source);
            return date;
        }catch (Exception ex){
            System.out.println(ex.getMessage());
            return null;
        }
    }
}
