package com.company;

import java.util.ArrayList;

public class GlobalData {
    public static int getDistanceBetween(Intersection start, Intersection finish){
        return Math.abs(finish.getLine()-start.getLine()) + Math.abs(finish.getColumn()-start.getColumn());
    }
    public static Ride getEarliestRide(ArrayList<Ride> rides){ //null if no ride?
        Ride minEarliestStartRide = null;
        for (Ride ride : rides) {

            if (minEarliestStartRide == null) {
                minEarliestStartRide = ride;
                continue;
            }

            if(ride.getEarliestStart() < minEarliestStartRide.getEarliestStart()) {
                minEarliestStartRide = ride;
            }
        }
        return minEarliestStartRide;
    }

    public static Vehicle getClosestCar(ArrayList<Vehicle> vehicles, Ride ride){ //returns null if no available vehicles
        Vehicle closestVehicle = null;
        int currentDelay = Integer.MAX_VALUE;
        for(Vehicle v : vehicles){
            int delay =  v.getDelayTo(ride);
            if(v.getCurrRide() == null && (closestVehicle  == null || delay < currentDelay)){
                closestVehicle = v;
                currentDelay = delay;
            }
        }
        return closestVehicle;
    }

}
