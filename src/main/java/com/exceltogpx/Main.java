package com.exceltogpx;

import org.json.JSONObject;

public class Main {
	public static char[] animationChars = new char[]{'|', '/', '-', '\\'};

	public Main() {
	}

	public static void main(String[] args) {
//		Gpx g = new Gpx("output");
//		g.add(48.2081743f, 49.2081743f, 100.2f);
//		g.add(49.2081743f, 49.2081743f, 101.2f);
//		g.add(50.2081743f, 49.2081743f, 102.2f);
//		g.add(51.2081743f, 49.2081743f, 103.2f);
//		System.out.println(g.write());
		
		try{
			long a = System.currentTimeMillis();

//			for(int i = 1; i<100; i++) {
//				json = OSM.readJsonFromUrl("https://nominatim.openstreetmap.org/search?q=The+White+House,+Washington+DC&format=json&addressdetails=1");
//				json = OSM.readJsonFromUrl("https://jsonplaceholder.typicode.com/todos/" + i);
//				System.out.println("Processing: " + i + "% " + animationChars[i % 4] + "\r");
//			}

			ExcelHandler.read();
			for(Address addr : ExcelHandler.l) {
				JSONObject json = OSM.query(addr.toString());
				System.out.println(json.toString());
			}
			long b = System.currentTimeMillis();
			System.out.println(b-a);
		}catch(Exception e) {

		}
	}

//	public void progressbar() {
//
//		for (int i = 0; i <= 100; i++) {
//			System.out.print("Processing: " + i + "% " + animationChars[i % 4] + "\r");
//
//			try {
//				Thread.sleep(100);
//			} catch (InterruptedException e) {
//				e.printStackTrace();
//			}
//		}
//
//		System.out.println("Processing: Done!          ");
//	}
	
	//TODO elaborazione errori scrittura excel e json vuoti
}

