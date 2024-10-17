package no.hvl.dat100ptc.oppgave4;

import no.hvl.dat100ptc.oppgave1.GPSPoint;
import no.hvl.dat100ptc.oppgave2.GPSData;
import no.hvl.dat100ptc.oppgave2.GPSDataConverter;
import no.hvl.dat100ptc.oppgave2.GPSDataFileReader;
import no.hvl.dat100ptc.oppgave3.GPSUtils;

import java.util.Locale;

import no.hvl.dat100ptc.TODO;

public class GPSComputer {

	private GPSPoint[] gpspoints;

	public GPSComputer(String filename) {

		GPSData gpsdata = GPSDataFileReader.readGPSFile(filename);
		gpspoints = gpsdata.getGPSPoints();

	}

	public GPSComputer(GPSPoint[] gpspoints) {
		this.gpspoints = gpspoints;
	}

	public GPSPoint[] getGPSPoints() {
		return this.gpspoints;
	}

	public double totalDistance() {
		double avstand = 0;
		for (int i = 0; i < gpspoints.length; i++) {
			if (i < gpspoints.length - 1)
				avstand += GPSUtils.distance(gpspoints[i + 1], gpspoints[i]);
		}

		return avstand;
	}

	public double totalElevation() {

		double elevation = 0;

		for (int i = 0; i < gpspoints.length; i++) {
			if (i < gpspoints.length - 1) {
				double elevationChange = gpspoints[i + 1].getElevation() - gpspoints[i].getElevation();

				if (elevationChange > 0) {
					elevation += elevationChange;
				}
			}
		}

		return elevation;
	}

	public int totalTime() {
		int tid = 0;

		for (int i = 0; i < gpspoints.length; i++) {
			if (i < gpspoints.length - 1)
				tid += gpspoints[i + 1].getTime() - gpspoints[i].getTime();

		}
		return tid;
	}

	public double[] speeds() {

		double[] speeds = new double[gpspoints.length - 1];

		for (int i = 0; i < gpspoints.length; i++) {
			if (i < gpspoints.length - 1) {
				int tid = gpspoints[i + 1].getTime() - gpspoints[i].getTime();
				double avstand = GPSUtils.distance(gpspoints[i + 1], gpspoints[i]);

				speeds[i] = avstand / tid;
			}
		}
		return speeds;
	}

	public double maxSpeed() {

		double maxspeed = GPSUtils.findMax(speeds());

		return maxspeed;

	}

	public double averageSpeed() {
	    double totalDistance = totalDistance(); 
	    int totalTime = totalTime(); 

	    return totalDistance / totalTime; 
	}

	// conversion factor m/s to miles per hour (mps)
	public static final double MS = 2.23;

	public double kcal(double weight, int secs, double speed) {
		double kcal = 0;
		double time = (double)secs / 3600.0;

		double met;
		double speedmph = speed * MS;

		if (speedmph < 10) {
			met = 4.0;
			kcal = met * weight * time;
		} else if (speedmph >= 10 && speedmph < 12) {
			met = 6;
			kcal = met * weight * time;
		} else if (speedmph >= 12 && speedmph < 14) {
			met = 8;
			kcal = met * weight * time;
		} else if (speedmph >= 14 && speedmph < 16) {
			met = 10;
			kcal = met * weight * time;
		} else if (speedmph >= 16 && speedmph < 20) {
			met = 12;
			kcal = met * weight * time;
		} else if (speedmph >= 20) {
			met = 16;
			kcal = met * weight * time;
		}

		return kcal;
	}

	public double totalKcal(double weight) {

		double totalkcal = 0;

		double[] kcals = new double[gpspoints.length-1];

		double[] segmentSpeeds = speeds();
		for (int i = 0; i < gpspoints.length-1; i++) {
				int tid = gpspoints[i + 1].getTime() - gpspoints[i].getTime();
				totalkcal +=kcal(weight, tid, segmentSpeeds[i]);
			
			
		}

		return totalkcal;
	}

	private static double WEIGHT = 80.0;

	public void displayStatistics() {
		
		System.out.println("==============================================");
		System.out.println("Total time     :   "+GPSUtils.formatTime(totalTime()) );
		System.out.println("Total distance :      "+String.format(Locale.US, "%.2f",totalDistance() / 1000.0) +" km");
		System.out.println("Total elevation:     "+String.format(Locale.US, "%.2f",totalElevation())+" m");
		System.out.println("Max speed      :      "+String.format(Locale.US,"%.2f",maxSpeed()*3.6)+" km/t");
		System.out.println("Average speed  :      "+String.format(Locale.US,"%.2f",averageSpeed()*3.6)+" km/t");
		System.out.println("Energy         :     "+String.format(Locale.US,"%.2f",totalKcal(WEIGHT))+" kcal");
		System.out.println("==============================================");


	}

}
