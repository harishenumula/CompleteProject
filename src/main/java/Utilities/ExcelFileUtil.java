package Utilities;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

import org.apache.poi.EncryptedDocumentException;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.format.CellFormatType;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.Font;
import org.apache.poi.ss.usermodel.IndexedColors;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;

public class ExcelFileUtil {

	Workbook book;

	public ExcelFileUtil() throws Throwable, Throwable, IOException {
		System.out.println(System.getProperty("user.dir"));
		FileInputStream fi = new FileInputStream(System.getProperty("user.dir") + "\\TestInput\\Inputs.xlsx");
		System.out.println(System.getProperty("user.dir") + "\\TestInput\\Inputs.xlsx");
		book = WorkbookFactory.create(fi);
	}

	public int getrowCount(String Sheetname) {
		return book.getSheet(Sheetname).getLastRowNum();
	}

	public int colcount(String sheetname, int row) {
		return book.getSheet(sheetname).getRow(row).getLastCellNum();
	}

	public String getData(String sheetname, int row, int col) {
		String data = "";
		if (book.getSheet(sheetname).getRow(row).getCell(col).getCellType() == Cell.CELL_TYPE_NUMERIC) {
			data = String.valueOf(book.getSheet(sheetname).getRow(row).getCell(col).getNumericCellValue());

		} else {
			data = book.getSheet(sheetname).getRow(row).getCell(col).getStringCellValue();
		}
		return data;
	}

	public void setData(String sheetname, int row, int col, String status) throws IOException {
		Row row1 = book.getSheet(sheetname).getRow(row);
		Cell cell = row1.createCell(col);
		cell.setCellValue(status);
		if (status.equalsIgnoreCase("pass")) {
			CellStyle style = book.createCellStyle();
			Font font = book.createFont();
			font.setColor(IndexedColors.GREEN.getIndex());
			font.setBoldweight(font.BOLDWEIGHT_BOLD);
			style.setFont(font);
			row1.getCell(col).setCellStyle(style);
		}
		if (status.equalsIgnoreCase("Fail")) {
			CellStyle style = book.createCellStyle();
			Font font = book.createFont();
			font.setColor(IndexedColors.RED.getIndex());
			font.setBoldweight(font.BOLDWEIGHT_BOLD);
			style.setFont(font);
			row1.getCell(col).setCellStyle(style);
		}
		if (status.equalsIgnoreCase("Not Executed")) {
			CellStyle style = book.createCellStyle();
			Font font = book.createFont();
			font.setColor(IndexedColors.GREY_80_PERCENT.getIndex());
			font.setBoldweight(font.BOLDWEIGHT_BOLD);
			style.setFont(font);
			row1.getCell(col).setCellStyle(style);
		}
		FileOutputStream fos = new FileOutputStream(System.getProperty("user.dir") + "\\TestOutput\\Inputs.xlsx");
		book.write(fos);
		fos.close();
	}
}
