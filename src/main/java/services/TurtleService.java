package services;

import model.Turtle;

import java.lang.reflect.Array;
import java.math.*;
import java.util.ArrayList;

/**
 * Created by yoannlathuiliere on 10/05/2017.
 */
public class TurtleService {
    private static TurtleService sharedInstance;

    private TurtleService() {}

    public static TurtleService getInstance() {
        if (sharedInstance == null) {
            sharedInstance = new TurtleService();
        }

        return sharedInstance;
    }

    public ArrayList<Turtle> getNeighborhoodOfTurtle(Turtle turtle, ArrayList<Turtle> turtles, boolean color) {
        int startingAngle = turtle.getDirection() - (turtle.getFieldOfViewAngle() / 2);
        int endingAngle = turtle.getDirection() + (turtle.getFieldOfViewAngle() / 2);
        int radius = turtle.getFieldOfViewDistance();
        ArrayList<Turtle> neighborhood = new ArrayList();

        for (Turtle t : turtles) {
            if (t==turtle) {continue;} //TODO: Filter

            int a = t.getX();
            int b = t.getY();

            int tX = t.getX() - turtle.getX();
            int tY = t.getY() - turtle.getY();

            double turtleAngle = Math.toDegrees(Math.atan2(tY, tX));
            double turtleRadius = Math.sqrt(tX * tX + tY * tY);

            if(tX == 0 && tY == 0 && (color && t.getColor() == turtle.getColor())) {
                neighborhood.add(t);
            } else if (turtleAngle >= startingAngle && turtleAngle <= endingAngle
                    && turtleRadius >= 0 && turtleRadius <= radius
                    && (color && t.getColor() == turtle.getColor())) {
                neighborhood.add(t);
            }
        }

        return neighborhood;
    }

    public ArrayList<Turtle> get360Neighborhood(Turtle turtle, ArrayList<Turtle> turtles, int distance, boolean color)
    {
        ArrayList<Turtle> neighborhood = new ArrayList();

        for (Turtle t : turtles) {
            if (t==turtle) {continue;} //TODO: Filter

            if(TurtleService.getInstance().getEuclidianDistance(turtle.getX(), t.getX(), turtle.getY(), t.getY()) <= distance
                    && (color && t.getColor() == turtle.getColor()))
                neighborhood.add(t);
        }

        return neighborhood;
    }

    public double getEuclidianDistance(int x1, int x2, int y1, int y2)
    {
        return Math.sqrt(Math.pow(x1-x2, 2) + Math.pow(y1-y2, 2));
    }

    public float getAverageDirection(ArrayList<Turtle> turtles)
    {
        float mean = 0.0f;
        for(Turtle t: turtles)
        {
            mean += t.getDirection();
        }
        mean = mean/turtles.size();
        return mean;
    }

    public float getAverageSpeed(ArrayList<Turtle> turtles)
    {
        float mean = 0.0f;
        for(Turtle t: turtles)
        {
            mean += t.getSpeed();
        }
        mean = mean/turtles.size();
        return mean;
    }
}
