package no.hvl.dat100ptc.oppgave1;

import no.hvl.dat100ptc.TODO;

public class GPSPoint {
	
	// Tid i sekunder
	private int time;
	
	// Breddegrad
	private double latitude;
	
	// Lengdegrad
	private double longitude;
	
	// Høyde i meter
	private double elevation;
	
	public GPSPoint(int time, double latitude, double longitude, double elevation) {
		this.time = time;
		this.latitude = latitude;
		this.longitude = longitude;
		this.elevation = elevation;
	}

	// TODO - get/set metoder
	public int getTime() {
		
		return time;
		
	}

	public void setTime(int time) {
				
		this.time = time;
		
	}

	public double getLatitude() {
		
		throw new UnsupportedOperationException(TODO.method());
		
		
	}

	public void setLatitude(double latitude) {
		
		throw new UnsupportedOperationException(TODO.method());
		
	}

	public double getLongitude() {
		
		throw new UnsupportedOperationException(TODO.method());
		
	}

	public void setLongitude(double longitude) {
		
		throw new UnsupportedOperationException(TODO.method());
		
	}

	public double getElevation() {
		
		throw new UnsupportedOperationException(TODO.method());
		
	}

	public void setElevation(double elevation) {
		
		throw new UnsupportedOperationException(TODO.method());
		
	}
	
	public String toString() {
		
		String str;
		
		throw new UnsupportedOperationException(TODO.method());

		// TODO
		
	}
}
