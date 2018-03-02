package com.company;

import java.util.ArrayList;

public class Vehicle {

    private int id;

    private ArrayList<Ride> doneRides = new ArrayList<>();
    private Intersection currIntersection;
    private Ride currRide;
    private Ride lastRide;
    private int stepsLeft;

    public Vehicle(int id, Road road) {
        this.id = id;
        this.currIntersection = road.getIntersection(0, 0);
    }

    public ArrayList<Ride> getDoneRides() {
        return doneRides;
    }

    public int getId() {
        return id;
    }

    public Intersection getCurrIntersection() {
        return currIntersection;
    }

    public Ride getCurrRide() {
        return currRide;
    }

    public void setCurrIntersection(Intersection currIntersection) {
        this.currIntersection = currIntersection;
    }

    public void setCurrRide(Ride currRide) {
        this.stepsLeft =
                GlobalData.getDistanceBetween(currIntersection, currRide.getFinishIntersection());
        this.currRide = currRide;
        this.stepsLeft = currRide.getDistance();
        this.doneRides.add(currRide);
    }

    public int getDelayTo(Ride ride) {
        return GlobalData.getDistanceBetween(currIntersection, ride.getStartIntersection());
    }

    public void move() {
        stepsLeft--;
        if (stepsLeft == 0) {
            lastRide = currRide;
            currIntersection = lastRide.getFinishIntersection();
            currRide = null;
        }
    }

    @Override
    public String toString() {
        return "Vehicle{" +
                "id=" + id +
                ", doneRides=" + doneRides +
                ", currIntersection=" + currIntersection +
                ", currRide=" + currRide +
                ", lastRide=" + lastRide +
                ", stepsLeft=" + stepsLeft +
                '}';
    }
}
