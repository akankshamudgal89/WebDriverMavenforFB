package FBDataProvider;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class ExcelReaderCLass {

	public static String[][] GetData;
	
	public static Object[][] ReadExcelData(String excellocation, String Sheetname) throws IOException{
		Object [][] Getdata= null;
		FileInputStream fis= new FileInputStream(excellocation);
		XSSFWorkbook wb =new XSSFWorkbook(fis);
		XSSFSheet sh= wb.getSheet(Sheetname);
		int rowcount= sh.getLastRowNum();
		int colcount=sh.getRow(0).getLastCellNum();
		GetData= new String[rowcount][colcount];
		for(int i=0;i<=rowcount;i++) {
			XSSFRow row=sh.getRow(i);
			for(int j=0;j<colcount;j++) {
				String cell= row.getCell(j).getStringCellValue();
				GetData[i-1][j]= cell;
			}
			
		}
		wb.close();
		
		
		return GetData;
		
		
		
		
		
		
	}

}
