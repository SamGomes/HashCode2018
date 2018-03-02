package com.company;

public class Ride {


    private int id;
    private Intersection startIntersection;
    private Intersection finishIntersection;

    private int earliestStart;
    private int latestFinish;
    private int distance;

    public Ride(int id, Intersection startIntersection, Intersection finishIntersection, int earliestStart, int latestFinish) {
        this.id = id;
        this.startIntersection = startIntersection;
        this.finishIntersection = finishIntersection;
        this.earliestStart = earliestStart;
        this.latestFinish = latestFinish;

        this.distance = GlobalData.getDistanceBetween(this.getStartIntersection(),this.getFinishIntersection());
    }

    public int getId() {
        return id;
    }

    public int getDistance() {
        return distance;
    }

    public Intersection getStartIntersection() {
        return startIntersection;
    }

    public Intersection getFinishIntersection() {
        return finishIntersection;
    }

    public int getEarliestStart() {
        return earliestStart;
    }

    public int getLatestFinish() {
        return latestFinish;
    }

}
