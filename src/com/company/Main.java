package com.company;

import java.io.File;
import java.io.PrintWriter;
import java.io.FileNotFoundException;

import java.util.ArrayList;
import java.util.Scanner;

import static sun.misc.Version.println;

public class Main {

    static int numRows;
    static int numColumns;
    static int numVehicles;
    static int numRides;
    static int bonus;
    static int simulationSteps;

    static ArrayList<Ride> rides;
    static ArrayList<Vehicle> vehicles;

    static Road road;

    public static void main(String[] args) {

        rides = new ArrayList<Ride>();
        vehicles = new ArrayList<Vehicle>();

        //read input

        String filename = "";
        if (0 < args.length) {
            filename = args[0];
            try {
                Scanner inputScanner = new Scanner(new File("C:\\Users\\Samuel\\Documents\\HashCodeStuff\\hashCodeProj\\src\\com\\company\\inputs\\"+filename+".in"))
                        .useDelimiter(" |\n");

                numRows = inputScanner.nextInt();
                numColumns = inputScanner.nextInt();
                numVehicles = inputScanner.nextInt();
                numRides = inputScanner.nextInt();
                bonus = inputScanner.nextInt();
                simulationSteps = inputScanner.nextInt();

                //build road
                road = new Road(numRows, numColumns);
                for (int i = 0; i < numRows; i++) {
                    for (int j = 0; j < numColumns; j++) {
                        Intersection newIntersection = new Intersection(i, j);
                        road.addIntersection(i, j, newIntersection);
                    }
                }

                //build vehicles
                for (int i = 0; i < numVehicles; i++) {
                    vehicles.add(new Vehicle(i, road));
                }
                //build rides
                for (int i = 0; i < numRides; i++) {
                    Intersection startIntersection = road.getIntersection(inputScanner.nextInt(), inputScanner.nextInt());
                    Intersection endIntersection = road.getIntersection(inputScanner.nextInt(), inputScanner.nextInt());
                    Ride newRide = new Ride(i, startIntersection, endIntersection, inputScanner.nextInt(), inputScanner.nextInt());
                    rides.add(newRide);
                }

            } catch (FileNotFoundException e) {
                System.out.println("file not found!");
            }
        } else {
            System.out.println("args size must be higher than 0");
        }

        while (simulationSteps > 0 && rides.size() > 0) {
            for (Vehicle vehicle : vehicles) {
                if (rides.size() == 0) {
                    break;
                }
                if (vehicle.getCurrRide() == null) {

                    Ride earliestRide = GlobalData.getEarliestRide(rides);
                    Vehicle closestCar = GlobalData.getClosestCar(vehicles, earliestRide);
                    if (closestCar != null) {
                        closestCar.setCurrRide(earliestRide);
                        rides.remove(earliestRide);
                    }
                }
            }
            //update vehicles
            for (Vehicle v : vehicles) {
                v.move();
            }
            simulationSteps--;
        }

        String lines = "";
        for (Vehicle vehicle : vehicles) {
            lines += "" + vehicle.getDoneRides().size();
            for (Ride ride : vehicle.getDoneRides()) {
                lines += " " + ride.getId();
            }
            lines += "\n";
        }

        System.out.println(lines);

        try (PrintWriter out = new PrintWriter("C:\\Users\\Samuel\\Documents\\HashCodeStuff\\hashCodeProj\\src\\com\\company\\outputs\\"+filename+".out")) {
            out.println(lines);
        }catch (FileNotFoundException e){
            System.out.printf("not ok...");
        }

//	    // print output
//        int slicesSize = slices.size();
//        String lines = "";
//
//        lines = "" + slicesSize;
//        lines += "\n";
//
//        for(int i=0; i < slices.size(); i++){
//            Slice currSlice = slices.get(i);
//            lines += currSlice.getRoot().getLine() +" " + currSlice.getTail().getLine() + " " + currSlice.getRoot().getColumn() +" " + currSlice.getTail().getColumn();
//            lines += "\n";
//        }
//
//        filename = filename.split("\\.|.+?/(?=[^/]+$)")[1];
//
//        try (PrintWriter out = new PrintWriter("./outputs/"+filename+".out")) {
//            out.println(lines);
//        }catch (FileNotFoundException e){
//            System.out.printf("not ok...");
//        }

    }
}
