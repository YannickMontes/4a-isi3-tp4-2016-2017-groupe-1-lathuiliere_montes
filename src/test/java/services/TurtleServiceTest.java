package services;

import model.Turtle;
import org.junit.Before;
import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.*;
import static org.mockito.Mockito.verify;

import java.util.ArrayList;

/**
 * Created by yoannlathuiliere on 17/05/2017.
 */
public class TurtleServiceTest {
    private ArrayList<Turtle> turtles;
    private Turtle turtle;
    private Turtle garry;
    private Turtle jose;
    private Turtle maria;
    private Turtle angelo;
    private Turtle leo;

    @Before
    public void setup()
    {
        turtles = new ArrayList();

        turtle = mock(Turtle.class);
        when(turtle.getX()).thenReturn(50);
        when(turtle.getY()).thenReturn(50);
        when(turtle.getFieldOfViewDistance()).thenReturn(1000);

        garry = mock(Turtle.class);
        when(garry.getX()).thenReturn(50);
        when(garry.getY()).thenReturn(25);

        jose = mock(Turtle.class);
        when(jose.getX()).thenReturn(80);
        when(jose.getY()).thenReturn(60);

        maria = mock(Turtle.class);
        when(maria.getX()).thenReturn(30);
        when(maria.getY()).thenReturn(30);

        angelo = mock(Turtle.class);
        when(angelo.getX()).thenReturn(50);
        when(angelo.getY()).thenReturn(50);

        leo = mock(Turtle.class);
        when(leo.getX()).thenReturn(80);
        when(leo.getY()).thenReturn(40);

        turtles.add(garry);
        turtles.add(jose);
        turtles.add(maria);
        turtles.add(angelo);
        turtles.add(leo);
    }

    @Test
    public void should_return_garry_angelo() throws Exception {
        when(turtle.getFieldOfViewAngle()).thenReturn(10);
        when(turtle.getDirection()).thenReturn(-90);

        ArrayList<Turtle> neighborhood = TurtleService.getInstance().getNeighborhoodOfTurtle(turtle, turtles);
        assertThat(neighborhood).containsExactlyInAnyOrder(garry, angelo);
    }

    @Test
    public void should_return_garry_angelo_leo_maria() throws Exception {
        when(turtle.getFieldOfViewAngle()).thenReturn(180);
        when(turtle.getDirection()).thenReturn(-90);

        ArrayList<Turtle> neighborhood = TurtleService.getInstance().getNeighborhoodOfTurtle(turtle, turtles);
        assertThat(neighborhood).containsExactlyInAnyOrder(garry, angelo, leo, maria);
    }

    @Test
    public void should_return_jose_angelo() throws Exception {
        when(turtle.getFieldOfViewAngle()).thenReturn(180);
        when(turtle.getDirection()).thenReturn(90);

        ArrayList<Turtle> neighborhood = TurtleService.getInstance().getNeighborhoodOfTurtle(turtle, turtles);
        assertThat(neighborhood).containsExactlyInAnyOrder(angelo, jose);
    }

    @Test
    public void should_contains_angelo_maria() throws Exception {
        when(turtle.getFieldOfViewAngle()).thenReturn(90);
        when(turtle.getDirection()).thenReturn(-180);

        ArrayList<Turtle> neighborhood = TurtleService.getInstance().getNeighborhoodOfTurtle(turtle, turtles);
        assertThat(neighborhood).containsExactlyInAnyOrder(angelo, maria);
    }

}