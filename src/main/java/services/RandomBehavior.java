package services;

import model.Turtle;
import view.DrawingSheet;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by yannick on 17/05/17.
 */
public class RandomBehavior extends Thread
{
    private ArrayList<Turtle> turtles;
    private DrawingSheet drawingSheet;

    public RandomBehavior(ArrayList<Turtle> turtles, DrawingSheet drawingSheet)
    {
        this.drawingSheet = drawingSheet;
        this.turtles = turtles;
    }

    @Override
    public void run()
    {
        while(true)
        {
            for(Turtle t : turtles)
            {
                t.randomMoove();
                t.moove();
            }
            try
            {
                Thread.sleep(50);
            } catch (InterruptedException e)
            {
                e.printStackTrace();
            }
            drawingSheet.repaint();
        }
    }
}
