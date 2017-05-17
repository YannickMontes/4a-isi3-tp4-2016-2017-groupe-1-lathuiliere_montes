package services;

import model.Turtle;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by yannick on 17/05/17.
 */
public class FlockingBehavior extends Thread
{
    private ArrayList<Turtle> turtles;

    public FlockingBehavior(ArrayList<Turtle> turtles)
    {
        this.turtles = turtles;
    }

    public void run()
    {
        System.out.println("Start flocking");
        for(Turtle t : turtles)
        {
            t.flocking(turtles);
        }
    }
}
