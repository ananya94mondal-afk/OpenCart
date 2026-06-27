package utilities;

import java.io.IOException;

import org.testng.annotations.DataProvider;

public class Dataprovider {

	@DataProvider(name="LoginData")
	public String[][] getLoginData() throws IOException{
		
		String path=System.getProperty("user.dir")+"//testData//OpenCart_LoginData.xlsx";
		
		ExcelUtility excelutility=new ExcelUtility(path);
		int rows=excelutility.getRowCount("Sheet1");
		int cols=excelutility.getCellCount("Sheet1", 1);
		String[][] logindata=new String[rows][cols];
		
		for(int i=1;i<=rows;i++) { //without header start from 1
			for(int j=0;j<cols;j++) {
				logindata[i-1][j]=excelutility.getCellData("Sheet1", i, j);
			}
		}
		return logindata;
	}
}
