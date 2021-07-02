package com.exceltogpx;

import java.io.File;
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
	public List<Address> l = new ArrayList<>();	
	public static Address last = null;
	public static File f;
	public final static String datpath = System.getProperty("user.dir") + File.separatorChar;
	public final String fileName;
	public final static int nof = 3;
	public final static int off = 20;
	public final static int indcitta = 0;
	public final static int indvia = 1;
	public final static int indcivico = 2;
	
	public ExcelHandler(String s) {
		fileName = s;
		f = new File(s);
	}

	/**
	 * reads the files from excel and puts them into the static list l
	 * @param s excel name
	 * @throws EncryptedDocumentException
	 * @throws IOException
	 */
	public void read() throws EncryptedDocumentException, IOException {
		FileInputStream ip = new FileInputStream(datpath + fileName);
		Workbook wb = WorkbookFactory.create(ip);
		Sheet sheet = wb.getSheetAt(0);
		Iterator<Row> rowIterator = sheet.rowIterator();
		DataFormatter d = new DataFormatter();
		int i = 0;
		while (rowIterator.hasNext()) {
			Row row = rowIterator.next();

			//iterate over the columns of the current row
			Iterator<Cell> cellIterator = row.cellIterator();
			Address addr = new Address();
			while (cellIterator.hasNext()) {
				Cell cell = cellIterator.next();
				String cellValue = d.formatCellValue(cell);
//				System.out.print(cellValue + "\n");
				switch(i%nof) {
				case indcitta:
					addr = new Address();
					addr.setCitta(cellValue.trim());
					break;
				case indvia:
					addr.setVia(cellValue.trim());
					break;
				case indcivico:
					if(!cellValue.equals("0"))
						addr.setCivico(cellValue.trim());
					if(i>nof)log(addr);
					break;
				default:
					break;
				}
				i++;
			}
		}

		ip.close();
	}

	public boolean exists() {
		return f.exists();
	}
	public void log(Address addr) {
		l.add(addr);
	}
	
	public String getFileName() {
		return fileName;
	}

	public static void main(String[] args) {
//		try {
//			read();
//			System.out.println(l.get(0).getCitta());
//			for(Address addr : l) {
//				System.out.println(addr.toString());
//				JSONObject json = OSM.query(addr.toString());
//				System.out.println(json == null ? "null":json.toString());
//			}
//		} catch (EncryptedDocumentException | IOException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
		ExcelHandler eh = new ExcelHandler("segrate.xls");
		try {
			eh.read();
		} catch (EncryptedDocumentException | IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println(eh.l.get(0).toString() + "\n" + eh.l.size());
		
	}
}
