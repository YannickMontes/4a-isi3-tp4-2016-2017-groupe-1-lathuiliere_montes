package services;

import model.Turtle;
import view.DrawingSheet;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by yannick on 17/05/17.
 */
public class FlockingBehavior extends Behavior
{

    @Override
    public void run()
    {
        while(running)
        {
            for(Turtle t : turtles)
            {
                t.flocking(turtles, true);
                t.moove();
            }
            try
            {
                Thread.sleep(50);
            } catch (InterruptedException e)
            {

            }
            drawingSheet.repaint();
        }
        running = true;
    }
}
