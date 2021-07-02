package com.exceltogpx;

import java.io.File;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.List;

class Gpx {

	private List<Waypoint> l = new ArrayList<Waypoint>();
	private File f;
	public static final String datpath = System.getProperty("user.dir");
	public final String fileName;
	public static final String ext = ".gpx"; 
	public static final String header = "<?xml version=\"1.0\" encoding=\"UTF-8\"?>\r\n"
			+ "<gpx version=\"1.1\" creator=\"Mauriello Edoardo\" "
			+ "xmlns=\"http://www.topografix.com/GPX/1/1\">\r\n";
	public static final String footer = "</gpx>\n";
	
	public Gpx(String name) {
		fileName = name;
		f = new File(datpath + File.separator + fileName + ext);
	}
	
	/**
	 * Adds Waypoint w to the list
	 * @param w
	 */
	public void add(Waypoint w) {
		l.add(w);
	}
	
	/**
	 * Creates and adds a waypoint to the list using parameters and 100 as elevation (standard value
	 * @param lon
	 * @param lat
	 */
	public void add(float lat, float lon) {
		l.add(new Waypoint(lat, lon));
	}
	
	protected String getFileName() {
		return fileName + ext;
	}

	public boolean exists() {
		return f.exists();
	}
	
	/**
	 * 
	 * @return 0 file created correctly
	 * @return -1 file already existent, please delete the other file
	 * @return -2 generic error
	 */
	public int write() {
		try{
			if(f.exists())
				return -1;
			FileWriter fw = new FileWriter(f, true);
			fw.append(header);
			for(Waypoint w : l) {
				fw.append((String.format("<wpt lat=\"%2.7f\" lon=\"%2.7f\">\n", 
						w.getLat(), w.getLon())).replace(',', '.'));
				fw.append("</wpt>\n");
			}
			fw.append(footer);
			fw.flush();
			fw.close();
			System.out.println("Scritto file\n" + f.length() + " caratteri\n"
			+ "In posizione " + datpath);
		}catch(Exception e){
			return -2;			
		}
		return 0;
	}
	
	public static void main(String[] args) {
//		Gpx g = new Gpx("output");
//		g.add(48.2081743f, 49.2081743f, 100.2f);
//		g.add(49.2081743f, 49.2081743f, 101.2f);
//		g.add(50.2081743f, 49.2081743f, 102.2f);
//		g.add(51.2081743f, 49.2081743f, 103.2f);
//		System.out.println(g.write());
	}
}
