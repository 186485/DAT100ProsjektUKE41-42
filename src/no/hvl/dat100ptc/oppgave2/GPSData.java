package no.hvl.dat100ptc.oppgave2;

import no.hvl.dat100ptc.TODO;
import no.hvl.dat100ptc.oppgave1.GPSPoint;

public class GPSData {

	private GPSPoint[] gpspoints;
	protected int antall = 0;

	public GPSData(int antall) {
		GPSPoint[] tabell = new GPSPoint[antall];
		gpspoints=tabell;
		antall=0;
	}

	public GPSPoint[] getGPSPoints() {
		return this.gpspoints;
	}
	
	protected boolean insertGPS(GPSPoint gpspoint) {
		if(antall<gpspoints.length) {
			gpspoints[antall]=gpspoint;
			antall++;
			return true;
		}
		return false;
	}

	public boolean insert(String time, String latitude, String longitude, String elevation) {
		GPSPoint gpspoint;
		gpspoint=GPSDataConverter.convert(time, latitude, longitude, elevation);
		if(gpspoint==null) {
			return false;
		}
		insertGPS(gpspoint);
		return true;
	}

	public void print() {
		System.out.println("====== GPS Data - START ======");
		for(int i = 0; i < gpspoints.length; i++) {
			GPSPoint p = gpspoints[i];
			System.out.println(p.toString());
		}
		System.out.println("====== GPS Data - SLUTT ======");
		
	}
}
