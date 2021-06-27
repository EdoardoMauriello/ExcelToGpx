package com.exceltogpx;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.apache.poi.EncryptedDocumentException;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;

public class ExcelHandler {
	public static List<Address> l = new ArrayList<>();	
	public static Address last = null;
	public final static int nof = 3;
	public final static int off = 3;
	
	public ExcelHandler() {
		// TODO Auto-generated constructor stu
	}

	public static void read() throws EncryptedDocumentException, IOException {
		FileInputStream ip = new FileInputStream("C:\\Java programs\\excelConvMaven\\prova.xlsx");
		Workbook wb = WorkbookFactory.create(ip);
		Sheet sheet = wb.getSheetAt(0);
		Iterator<Row> rowIterator = sheet.rowIterator();
		DataFormatter d = new DataFormatter();
		while (rowIterator.hasNext()) {
			Row row = rowIterator.next();

			//iterate over the columns of the current row
			Iterator<Cell> cellIterator = row.cellIterator();
			int i = 0;
			Address addr = new Address();
			while (cellIterator.hasNext()) {
				Cell cell = cellIterator.next();
				String cellValue = d.formatCellValue(cell);
				System.out.print(cellValue + "\t");
				switch(i%nof) {
				case 0:
					addr = new Address();
					addr.setCitta(cellValue);
					break;
				case 1:
					addr.setVia(cellValue);
					break;
				case 2:
					addr.setCivico(cellValue);
					log(addr);
					break;
				default:
					break;
				
				}
				i++;
			}
			//append empty line
			System.out.println();
		}

		ip.close();
	}

	public static void log(Address addr) {
		if(last==null || !addr.equals(last)) {
			last = addr;
			l.add(addr);
		}else if(Integer.parseInt(addr.getCivico()) > off + Integer.parseInt(last.getCivico())){
			l.add(addr);
		}
	}

	public static void main(String[] args) {
		try {
			read();
		} catch (EncryptedDocumentException | IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
