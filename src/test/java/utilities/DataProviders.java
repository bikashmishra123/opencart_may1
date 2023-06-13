package utilities;

import java.io.IOException;

import org.testng.annotations.DataProvider;

public class DataProviders {

	//DataProvider 1 
	
	@DataProvider(name="LoginData")
	public String[][] getData() throws IOException{
		
		String path = ".\\testData\\Opencart_LoginData.xlsx" ; //taking xl from testData
		
 
		ExcelUtility xlutil = new ExcelUtility(path); //creating object for Xlutility
		
		int totalrows = xlutil.getRowCount("Sheet1");
		int totalcols = xlutil.getCellCount("Sheet1",1);
		
		String logindata[][] = new String[totalrows][totalcols] ;//created for two dimanesional array which can 
		
		for(int i = 1 ; i<totalrows  ; i ++) { //read the data from xl and store in 2dimanesinal array 
			
			for (int j = 0 ; j<totalcols ; j++) {
				
				logindata[i-1][j] = xlutil.getCellData("Sheet1", i, j) ;
				
			}
		}
		
		return logindata ; //returning two dimensional array 
	}
	
	
	//DataProvider 2
	
	//DataProvider 3
}
