package services;

import model.Turtle;

import java.util.ArrayList;

/**
 * Created by yoannlathuiliere on 10/05/2017.
 */
public class TurtleService {
    static TurtleService sharedInstance;

    private TurtleService() {}

    public TurtleService getInstance() {
        if (sharedInstance == null) {
            sharedInstance = new TurtleService();
        }

        return sharedInstance;
    }

    public ArrayList<Turtle> getNeighborhoodOfTurtle(Turtle turtle, ArrayList<Turtle> turtles) {
        for (Turtle t : turtles) {
            if (t==turtle) {break;}

            if()
        }

        return new ArrayList<Turtle>();
    }
}
