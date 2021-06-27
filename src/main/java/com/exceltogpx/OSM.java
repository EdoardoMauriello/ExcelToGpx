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
			return readJsonFromUrl("https://nominatim.openstreetmap.org/search?q=" + q.replace(' ', '+') + "&format=json&addressdetails=1");
		}catch(Exception e){
			return null;
		}
	}
	
	public static void main(String[] args) throws IOException, JSONException {
//		JSONObject json = readJsonFromUrl("https://nominatim.openstreetmap.org/search?q=The+White+House,+Washington+DC&format=json&addressdetails=1");
		JSONObject json = query("Via Pasubio 19 Varese");
		System.out.println(json.toString());
//		System.out.println(json.get("id"));
	}
}