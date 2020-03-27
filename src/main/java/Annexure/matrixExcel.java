package Annexure;

import java.io.File;
import java.io.IOException;

import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;

public interface matrixExcel {
	
	public File getExcelFile(String Filename) throws IOException;
	public Workbook getWorkbook(String Filename) throws IOException;
	public Sheet getSheet(String sheetname, String Filename) throws IOException;
	public int getRowCount(String sheetname, String Filename) throws IOException;
	public String readData(String sheetname, String Filename,int row, int colno) throws IOException;
	public void writeData(String sheetname, String Filename,int row, int colno, String value) throws IOException;
}
