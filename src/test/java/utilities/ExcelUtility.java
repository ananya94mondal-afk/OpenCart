package utilities;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class ExcelUtility {

	public FileInputStream fi;
	public FileOutputStream fo;
	
	public XSSFWorkbook workbook;
	public XSSFSheet sheet;
	
	public XSSFRow row;
	public XSSFCell cell;
	
	public CellStyle style;
	
	String path;
	
	public ExcelUtility(String path) {
		this.path=path;
	}
	
	public int getRowCount(String sheetname) throws IOException {
		fi=new FileInputStream(path);
		workbook=new XSSFWorkbook(fi);
		sheet=workbook.getSheet(sheetname);
		int rowcount=sheet.getLastRowNum();
		workbook.close();
		fi.close();
		return rowcount;
		
	}
	
	public int getCellCount(String sheetname,int rownum) throws FileNotFoundException,IOException {
		fi=new FileInputStream(path);
		workbook=new XSSFWorkbook(fi);
		sheet=workbook.getSheet(sheetname);
		int cellcount=sheet.getRow(rownum).getLastCellNum();
		workbook.close();
		fi.close();
		return cellcount;
	}
	
	public String getCellData(String sheetname,int rownum, int cellnum) throws IOException {
		
		fi=new FileInputStream(path);
		workbook=new XSSFWorkbook(fi);
		sheet=workbook.getSheet(sheetname);
		row=sheet.getRow(rownum);
		cell=row.getCell(cellnum);
		DataFormatter formatter=new DataFormatter();
		String data;
		try {
			data= formatter.formatCellValue(cell);
		
		}catch(Exception e) {
			data="";
		}
		
		workbook.close();
		fi.close();
		return data;
	}
	
	public void setCellData(String sheetname,int rownum, int cellnum,String data) throws IOException {
		
		File file=new File(path);
		
		if(!file.exists()) { //if file not exists
			
			workbook=new XSSFWorkbook();
			fo=new FileOutputStream(path);
			workbook.write(fo);
			
		}
		
		fi=new FileInputStream(path);
		workbook=new XSSFWorkbook(fi);
		
		if(workbook.getSheetIndex(sheetname)==-1)//if sheet is not exists
			workbook.createSheet(sheetname);
		
		sheet=workbook.getSheet(sheetname);
		
		if(sheet.getRow(rownum)==null)
			sheet.createRow(rownum);
		
		row=sheet.getRow(rownum);
		
		cell=row.createCell(cellnum);
		cell.setCellValue(data);
		
		fo=new FileOutputStream(path);
		
		workbook.write(fo);
		
		workbook.close();
		fi.close();
		fo.close();
	}
}
