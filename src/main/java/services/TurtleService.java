package services;

import model.Turtle;
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

    public ArrayList<Turtle> getNeighborhoodOfTurtle(Turtle turtle, ArrayList<Turtle> turtles) {
        int endingAngle = turtle.getFieldOfViewAngle() / 2;
        int startingAngle = - turtle.getFieldOfViewAngle() / 2;
        int radius = turtle.getFieldOfViewDistance();
        ArrayList<Turtle> neighborhood = new ArrayList<>();

        for (Turtle t : turtles) {
            if (t==turtle) {continue;} //TODO: Filter

            double turtleAngle = Math.atan2((double) t.getX(), (double) t.getY());
            double turtleRadius = Math.sqrt((double) t.getX() * (double) t.getX() + (double) t.getY() * (double) t.getY());

            if(turtleAngle > startingAngle && turtleAngle < endingAngle && turtleRadius > 0 && turtleRadius < radius) {
                neighborhood.add(t);
            }
        }

        return neighborhood;
    }
}
