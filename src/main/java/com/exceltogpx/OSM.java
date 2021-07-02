package com.exceltogpx;


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.net.URL;
import java.nio.charset.Charset;

import org.json.JSONException;
import org.json.JSONObject;

public class OSM {

	public final static String aq = "https://nominatim.openstreetmap.org/search?q=";
	public final static String bq = "&format=json&addressdetails=1";
	
	private static String readAll(Reader rd) throws IOException {
		StringBuilder sb = new StringBuilder();
		int cp;
		while ((cp = rd.read()) != -1) {
			sb.append((char) cp);
		}
		return sb.toString();
	}

	public static JSONObject readJsonFromUrl(String url) throws IOException, JSONException {
		try (InputStream is = new URL(url).openStream()){
			BufferedReader rd = new BufferedReader(new InputStreamReader(is, Charset.forName("UTF-8")));
			String jsonText = brRemover(readAll(rd));
			JSONObject json = new JSONObject(jsonText);
			return json;
		}
}

	private static String brRemover(String s) {
		if(s.charAt(0)=='[')
			return s.substring(1, s.length()-1);
		return s;
	}

	public static JSONObject query(String q) {
		try{
			return readJsonFromUrl(aq + edit(q) + bq);
		}catch(Exception e){
			return null;
		}finally {
//			System.out.println("done");
		}
	}
	
	public static String edit(String q) {
		String s = q.toLowerCase();
		s = s.replace("circonv.", "circonvallazione ");
		s = s.replace("\' ", "\'");
		s = s.replace(' ', '+');
		return s;
	}
	
	public static void printquery(String s) {
		System.out.println(aq + edit(s) + bq);
	}
	
	public static void main(String[] args) throws IOException, JSONException {
//		JSONObject json = readJsonFromUrl("https://nominatim.openstreetmap.org/search?q=The+White+House,+Washington+DC&format=json&addressdetails=1");
//		JSONObject json = query("Via Pasubio 19 Varese");
//		System.out.println(json.toString());
//		String s = "3.0";
//		Float.parseFloat(s);
		
//		System.out.println(json.get("id"));
	}
}