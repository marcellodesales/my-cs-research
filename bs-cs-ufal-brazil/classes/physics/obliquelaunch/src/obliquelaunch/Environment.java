/*
 * @(#)Space.java        1.0 24/07/2002
 *
 * Copyright (c) 2002 Augusto César, Julian Herrera, Marcello Junior.
 * Universidade Federal de Alagoas - Brazil.
 * All rights reserved.
 */

package obliquelaunch;

import java.lang.Math;
import obliquelaunch.Thrower;
import obliquelaunch.Projectile;

/**
 * Space is responsable for everything concernig
 * the space where the throwing of an object such as a sphere will occour.
 *
 * @version 	1.01 July 09, 2002
 * @author 	Marcello Junior, Augusto César, Julian Herrera
 */

public class Environment {

  /** It's the gravity used in the space. */
  public double gravity;
	
	private double reach;
	
	public void setReach(double reach){
		this.reach = reach;
	}
	
	public double getReach(){
		return reach;
	}
  
  /** Creates new ObliqueThrowing with the default gravity (9.8m/s2) */
  public Environment(){
    this.gravity = 9.81;
    System.out.println("###### Criado objeto ObliqueThrowing #######");
  }

  /** Creates new ObliqueThrowing with gravity */
  public Environment(double gravity){
    this.gravity = gravity;
    System.out.println("###### Criado objeto ObliqueThrowing #######");
  }

  /** Sets the gravity of the environment */
  public void setGravity(double gravity){
	this.gravity = gravity;
  }
  
  /** Gets the gravity in the current environment. */
  public double getGravity(){
	return this.gravity;
  }

  /** Gets the time when the projectile reaches the highest of the projectile in question. */
  public double getMaxHeightTime(Projectile projectile){
    return (projectile.getVyInitialSpeed() / this.gravity);
  }

  /** Gets the highest value of the projectile used by the thrower... */
  public double getMaxHeight(Thrower thrower, Projectile projectile){
    double vY0 = projectile.getVyInitialSpeed();
    double t   = this.getMaxHeightTime(projectile);
    return ((vY0 * t) / 2);
  }
  
  /** situation 2 - Gets the highest value of the projectile used by the thrower... */
  public double getMaxHeight(Projectile projectile){
    double vY0 = projectile.getVyInitialSpeed();
    return (vY0 * vY0) / (2 * this.gravity);
  }

  /** Gets the highest value of the projectile used by the thrower... */
  public double getMaxHeight(Thrower thrower, Projectile projectile, int angle ){
	  double reach = getReach(thrower, projectile);
	  double v0 = projectile.getInitialSpeed(angle, reach, gravity);
    return (Math.pow((v0 * Math.sin(angle)),2) / (2 * gravity));
  }
  
  /** Gets the reach of the projectile when it reaches the ground  */
  public double getReach(Thrower thrower, Projectile projectile){
    double vX0 = projectile.getVxInitialSpeed();
    double t   = this.getMaxHeightTime(projectile);
    return (vX0 * 2 * t);
  }
  
  /** D : Gets the reach time of the projectile when it reaches the ground  */
  public double getReachTime(Thrower thrower, Projectile projectile){
    double tReach  = ( this.getReach(thrower, projectile) * projectile.getVxInitialSpeed() );
    return (tReach);
  }
  
}
