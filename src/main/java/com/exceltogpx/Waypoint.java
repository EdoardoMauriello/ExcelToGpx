package com.exceltogpx;

public class Waypoint {

	private float lon;
	private float lat;
	private float ele;
	
	/**
	 * @param lat latitude
	 * @param lon longitude
	 * @param ele elevation
	 */
	public Waypoint(float lat, float lon, float ele) {
		this.lat = lat;
		this.lon = lon;
		this.ele = ele;
	}

	protected float getLat() {
		return lat;
	}

	protected float getLon() {
		return lon;
	}

	protected float getEle() {
		return ele;
	}

	@Override
	public String toString() {
		return "Waypoint [lat=" + lat + ", lon=" + lon + ", ele=" + ele + "]";
	}

}

