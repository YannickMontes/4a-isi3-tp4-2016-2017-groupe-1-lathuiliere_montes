package model;

import java.awt.*;
import java.util.Observable;

public class Turtle extends Observable
{
	protected int x;
	protected int y;
	protected int direction;
	protected int coul;

	public Turtle()
    {
		reset();
	}

    public void reset()
    {
        x = 0;
        y = 0;
        direction = -90;
        coul = 0;
    }

    public void setColor(int n)
    {
        coul = n;
    }

    public int getColor()
    {
        return coul;
    }

    public int getDirection()
    {
        return direction;
    }

    public void setDirection(int direction)
    {
        this.direction = direction;
        notifyObservers();
    }

    public int getX()
	{
		return x;
	}

	public void setX(int x)
	{
		this.x = x;
	}

	public int getY()
	{
		return y;
	}

	public void setY(int y)
	{
		this.y = y;
	}

	public void setPosition(int newX, int newY) {
		x = newX;
		y = newY;
	}

	protected Color decodeColor(int c) {
		switch(c) {
			case 0: return(Color.black);
			case 1: return(Color.blue);
			case 2: return(Color.cyan);
			case 3: return(Color.darkGray);
			case 4: return(Color.red);
			case 5: return(Color.green);
			case 6: return(Color.lightGray);
			case 7: return(Color.magenta);
			case 8: return(Color.orange);
			case 9: return(Color.gray);
			case 10: return(Color.pink);
			case 11: return(Color.yellow);
			default : return(Color.black);
		}
	}

	public void avancer(int dist)
    {
		int newX = (int) Math.round(x+dist*Math.cos(Math.toRadians(direction)));
		int newY = (int) Math.round(y+dist*Math.sin(Math.toRadians(direction)));

		x = newX;
		y = newY;

        notifyObservers();
	}

	public void droite(int ang)
    {
		direction = (direction + ang) % 360;
        notifyObservers();
	}

	public void gauche(int ang)
    {
		direction = (direction - ang) % 360;
        notifyObservers();
	}

	public void couleur(int n) {
		coul = n % 12;
	}

	public void couleurSuivante() {
	 	couleur(coul+1);
	}

	/** quelques classiques */

	public void carre() {
		for (int i=0;i<4;i++) {
			avancer(100);
			droite(90);
		}
		notifyObservers();
	}

	public void poly(int n, int a) {
		for (int j=0;j<a;j++) {
			avancer(n);
			droite(360/a);
		}
        notifyObservers();
	}

	public void spiral(int n, int k, int a) {
		for (int i = 0; i < k; i++) {
			couleur(coul+1);
			avancer(n);
			droite(360/a);
			n = n+1;
		}
        notifyObservers();
	}
}