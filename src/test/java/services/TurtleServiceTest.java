package services;

import model.Turtle;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;
import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.atLeastOnce;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.anyObject;

import java.util.ArrayList;

import static org.junit.Assert.*;

/**
 * Created by yoannlathuiliere on 17/05/2017.
 */
public class TurtleServiceTest {
    private ArrayList<Turtle> turtles;
    private Turtle turtle;

    @Before
    public void setup()
    {
        turtles = new ArrayList<>();

        turtle = Mockito.mock(Turtle.class);
        Mockito.when(turtle.getX()).thenReturn(10);
        Mockito.when(turtle.getY()).thenReturn(10);

        Turtle turtle2 = Mockito.mock(Turtle.class);
        Mockito.when(turtle.getX()).thenReturn(10);
        Mockito.when(turtle.getY()).thenReturn(11);

        Turtle turtle3 = Mockito.mock(Turtle.class);
        Mockito.when(turtle.getX()).thenReturn(0);
        Mockito.when(turtle.getY()).thenReturn(0);

        Turtle turtle4 = Mockito.mock(Turtle.class);
        Mockito.when(turtle.getX()).thenReturn(11);
        Mockito.when(turtle.getY()).thenReturn(11);

        turtles.add(turtle2);
        turtles.add(turtle3);
        turtles.add(turtle4);
    }

    @Test
    public void getNeighborhoodOfTurtle() throws Exception {
        ArrayList<Turtle> neighborhood = TurtleService.getInstance().getNeighborhoodOfTurtle(turtle, turtles);

        assertThat(neighborhood).isEqualTo(2);
    }

}