package com.exceltogpx;

import java.io.IOException;

import org.json.JSONObject;

public class Main {
	public static char[] animationChars = new char[]{'|', '/', '-', '\\'};

	public Main() {
	}

	public static void main(String[] args) {
		float a = System.currentTimeMillis();
		float b;
		try{
			System.out.println("Esecuzione con\nInput: " + args[0]
					 + "\noutput: " + args[1] + Gpx.ext);
			ExcelHandler eh = new ExcelHandler(args[0]);
			Gpx g = new Gpx(args[1]);
			
			if(g.exists()) {
				System.out.println("ERROR: delete file " + g.getFileName());
			}else if(!eh.exists()) {
				System.out.println("ERROR: file " + eh.getFileName() + " not found");
			}else{
				eh.read();
				JSONObject last = null;
				int err = 0;
				for(int i = 0; i<eh.l.size(); i++) {
					System.out.print("Processing: " + i*100/eh.l.size() + "% " + animationChars[i % 4] + "\r");
					Address addr = eh.l.get(i);
					JSONObject json = OSM.query(addr.toString(false));
					if(json!=null && !json.equals(last)) {
						g.add(new Waypoint(Float.parseFloat((String)json.get("lat")),
							Float.parseFloat((String)json.get("lon"))));
					}else if(json == null) {
						json = OSM.query(addr.toString(true));
						if(json == null) {
							System.out.println("ERROR on query " + addr.getVia());
							err++;
						}
					}
				}
				g.write();
				b = System.currentTimeMillis();
				System.out.println(err + "errori su " + eh.l.size() + " richieste\nPercentuale: " + err*100/eh.l.size() + "%\n");
				System.out.println("Tempo di esecuzione: " + (b-a) + "ms");
			}
		}catch(IOException e) {
			e.printStackTrace();
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

