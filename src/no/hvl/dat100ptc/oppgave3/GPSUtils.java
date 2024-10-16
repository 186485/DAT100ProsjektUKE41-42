package no.hvl.dat100ptc.oppgave3;

import static java.lang.Math.*;

import no.hvl.dat100ptc.oppgave1.GPSPoint;
import no.hvl.dat100ptc.TODO;

public class GPSUtils {

	public static double findMax(double[] da) {

		double max;

		max = da[0];

		for (double d : da) {
			if (d > max) {
				max = d;
			}
		}

		return max;
	}

	public static double findMin(double[] da) {

		double min;

		min = da[0];

		for (double d : da) {
			if (d < min) {
				min = d;
			}
		}

		return min;

	}

	public static double[] getLatitudes(GPSPoint[] gpspoints) {

		double[] tabell = new double[gpspoints.length];
		for (int i = 0; i < gpspoints.length; i++) {
			tabell[i] = gpspoints[i].getLatitude();
		}

		return tabell;
	}

	public static double[] getLongitudes(GPSPoint[] gpspoints) {

		double[] tabell = new double[gpspoints.length];
		for (int i = 0; i < gpspoints.length; i++) {
			tabell[i] = gpspoints[i].getLongitude();
		}

		return tabell;

	}

	private static final int R = 6371000; // jordens radius

	public static double distance(GPSPoint gpspoint1, GPSPoint gpspoint2) {

		double d;
		double latitude1, longitude1, latitude2, longitude2;

		latitude1 = gpspoint1.getLatitude();
		latitude2 = gpspoint2.getLatitude();

		longitude1 = gpspoint1.getLongitude();
		longitude2 = gpspoint2.getLongitude();

		double phi1 = Math.toRadians(latitude1);
		double phi2 = Math.toRadians(latitude2);

		double deltaPhi = phi2 - phi1;
		double deltaLambda = Math.toRadians(longitude2 - longitude1);

		double a = Math.pow(Math.sin(deltaPhi / 2), 2)
				+ Math.cos(phi1) * Math.cos(phi2) * Math.pow(Math.sin(deltaLambda / 2), 2);

		double c = 2.0 * Math.atan2(Math.sqrt(a), Math.sqrt(1.0 - a));

		d = R * c;
		return d;
	}

	private static double compute_a(double phi1, double phi2, double deltaphi, double deltadelta) {

		double a = Math.pow(Math.sin(deltaphi / 2), 2)
				+ Math.cos(phi1) * Math.cos(phi2) * Math.pow(Math.sin(deltadelta / 2), 2);
		return a;
	}

	private static double compute_c(double a) {

		double c = 2.0 * Math.atan2(Math.sqrt(a), Math.sqrt(1.0 - a));
		return c;

	}

	public static double speed(GPSPoint gpspoint1, GPSPoint gpspoint2) {

		int secs;
		double speed;

		secs = gpspoint2.getTime() - gpspoint1.getTime();
		speed = distance(gpspoint1, gpspoint2) / secs;

		return speed;
	}

	public static String formatTime(int secs) {

		String timestr = "  ";
		String TIMESEP = ":";
		int timer = secs / 3600;
		int minutter = (int) (secs / 60) % 60;
		int sekunder = (int) (secs % 60);

		if (timer < 10) {
			timestr += "0" + timer;
		} else {
			timestr += timer;
		}
		
		timestr += ":";

		if (minutter < 10) {
			timestr += "0" + minutter;
		} else {
			timestr += minutter;
		}
		
		timestr += ":";
		
		if (sekunder < 10) {
			timestr += "0" + sekunder;
		} else {
			timestr += sekunder;
		}

		return timestr;

	}

	private static int TEXTWIDTH = 10;
	
	public static String formatDouble(double d) {
		double avrundet = (double) Math.round(d * 100) / 100;
		
		String str = Double.toString(avrundet);
		String mellomrom = " ";
		
		String resultat = mellomrom.repeat(TEXTWIDTH - str.length()) + str;
		return resultat;
	}
}
