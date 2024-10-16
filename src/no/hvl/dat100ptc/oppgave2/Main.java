package no.hvl.dat100ptc.oppgave2;

import no.hvl.dat100ptc.oppgave1.GPSPoint;

public class Main {

	public static void main(String[] args) {
		GPSPoint p1 = new GPSPoint(60, 127.0, 56.0, 32.0);
		GPSPoint p2 = new GPSPoint(124, 89.0, 23.0, 12.0);

		GPSData data = new GPSData(2);
		data.insertGPS(p1);

		data.insertGPS(p2);

		data.print();
	}

}
