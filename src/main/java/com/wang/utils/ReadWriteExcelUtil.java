package com.wang.utils;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;



/*
 * Excel导入和导出的工具类
 *
 * */
public class ReadWriteExcelUtil {


    //main方法测试
    public static void main(String[] args) throws Exception {

       // readExcle07();
        //readExcel03and07();
        writeExcel07();
        //readExcle03();
        //writeExcel03();
    }


    /**
     * 读取excel的方法， 07之前的版本，文件类型为.xls
     *   07及07之后的版本，文件类型为.xlsx
     * @throws Exception
     */
    public static void writeExcel03() throws Exception{
        /*
         *
         * 这是最基本的创建excel的方法，所有的值都给的固定值。
         *
         */
        //创建新excel文档，07版本之前均可以这么写
        HSSFWorkbook workbook = new HSSFWorkbook();
        //新建工作表
        HSSFSheet sheet = workbook.createSheet("test");
        //新建行
        HSSFRow row = sheet.createRow(0);
        //取第一行第一列
        HSSFCell cell = row.createCell(0);
        //给第一行第一列赋值
        cell.setCellValue("我是谁？");
        //将新建的工作表保存到硬盘中
        FileOutputStream fos = new FileOutputStream(new File("C:\\Users\\admin\\Desktop\\aa.xls"));
        workbook.write(fos);
        fos.close();


    }


    public static void readExcle03() throws Exception{

        /*
         * 最简单的读取excel的方法，给的值是固定的，可以取出对应表格中任何一个位置的值
         * 07之前的版本都可以这么写
         *
         * */

        //创建输入流
        FileInputStream fis = new FileInputStream(new File("C:\\Users\\admin\\Desktop\\03.xls"));
        //通过构造函数传参
        HSSFWorkbook workbook = new HSSFWorkbook(fis);
        //获取工作表
        HSSFSheet sheet = workbook.getSheetAt(0);
        //获取行，方法为getRow()
        HSSFRow row = sheet.getRow(0);
        //获取单元格，方法为row.getCell()
        HSSFCell cell = row.getCell(0);
        //获取单元格中的值
        String cellvalue = cell.getStringCellValue();
        System.out.println(cellvalue);
        fis.close();
    }
    /*
     * 在实际应用中，在读取文件的时候，我们不一定确定读取的excel的具体版本，所以，在输入和输出的语句需要添加判断来让其适应不同的版本
     *
     * */
    public static void readExcel03and07() throws Exception{
        //读取文件的路径
        String filepath = "C:\\Users\\admin\\Desktop\\07.xlsx";

        //判断文件的格式，
        if(filepath.matches("^.+\\.(?i)((xls)|(xlsx))$")){
            FileInputStream fis = new FileInputStream(filepath);
            boolean is03Excel = filepath.matches("^.+\\.(?i)((xls))$")?true:false; //判断是2003还是2007
            Workbook workbook = is03Excel ? new HSSFWorkbook(fis):new XSSFWorkbook(fis);
           // Workbook workbook = new XSSFWorkbook(fis);
            Sheet sheet = workbook.getSheetAt(0);
            Row row = sheet.getRow(0);
            Cell cell = row.getCell(0);
            System.out.println("第一行第一列的数据是:"+cell.getStringCellValue());
        }
    }


    /*
     * 创建07及以后excel的写法
     *
     * */
    public static void writeExcel07() throws Exception{

        /*
         * 创建07之后的版本使用的工厂类为XSSFWorkbook
         *
         * */
        //创建新表格文件
        XSSFWorkbook workbook = new XSSFWorkbook();
        //创建新表单
        XSSFSheet sheet = workbook.createSheet("test07");
      //  workbook.setSheetName(1,"wang");
        //创建sheet表单中的行
        XSSFRow row = sheet.createRow(0);
        //选取行中对应的列
        XSSFCell cell = row.createCell(0);
        //给对应的列中赋值
        cell.setCellValue("testss");
        //输出创建的文件，存储到硬盘中
        FileOutputStream fos = new FileOutputStream(new File("C:\\Users\\admin\\Desktop\\test.xlsx"));
        //workbook的write方法调用，不能忘啊
        workbook.write(fos);
        fos.close();
    }
}