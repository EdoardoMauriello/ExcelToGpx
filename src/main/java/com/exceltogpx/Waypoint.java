package com.exceltogpx;

public class Waypoint {

	private float lon;
	private float lat;
	
	/**
	 * @param lat latitude
	 * @param lon longitude
	 */
	public Waypoint(float lat, float lon) {
		this.lat = lat;
		this.lon = lon;
	}

	protected float getLat() {
		return lat;
	}

	protected float getLon() {
		return lon;
	}

	@Override
	public String toString() {
		return "Waypoint [lat=" + lat + ", lon=" + lon + "]";
	}

}

