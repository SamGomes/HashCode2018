package com.company;

import java.util.ArrayList;

public class Road {

    private int numRows;
    private int numColumns;

    private ArrayList<Intersection> intersections;

    public Road(int numRows, int numColumns){
        this.numRows = numRows;
        this.numColumns = numColumns;
        intersections = new ArrayList<Intersection>();
    }

    public void addIntersection(int line, int column, Intersection newCell){
        intersections.add(line*numColumns + column, newCell);
    }

    public Intersection getIntersection(int line, int column){
        return intersections.get(line*numColumns + column);
    }
}
