/*
 * @(#)Thrower.java        1.0 Created on 23 de Julho de 2002, 22:40
 *
 * Copyright (c) 2001 Augusto Cesar, Julian Herrera, Marcello Junior.
 * Universidade Federal de Alagoas - Brazil.
 * All rights reserved.
 */

package obliquelaunch;

import obliquelaunch.Projectile;

/**
 * The thrower will be something that throws up anything. It can be
 * a cannon that throws a projectile, or it can be a player that can
 * kick a ball.
 *
 * @author Marcello Junior
 * @version 1.2
 */
public class Thrower{

  /** It's the initial speed that will be passed to the projectile; */
  private double initialSpeed;
  
  /** It's the angle formed with the horizontal axis. */
  private double angle;
  
  /** It's the gravity passed by the obliqueThrowing. */
  private double gravity;
	
  /** It's the reach */
  private double reach;

  /** Creates new Thrower */
  public Thrower() {
    System.out.println("######### Criado objeto Thrower ########");
  }

  /** Sets the initial speed of a given projectile. */
  public void setInitialSpeed(double speed, Projectile projectile){
    projectile.setInitialSpeed(speed);
    this.initialSpeed = speed;
  }

  /** Sets the angle of the throwing of a given projectile. */
  public void setAngle(int angle, Projectile projectile){
    projectile.setAngle(angle);
    this.angle = angle;
  }
  
  /** Sets the gravity of the throwing of a given projectile. */
  public void setGravity(double gravity, Projectile projectile){
    projectile.setGravity(gravity);
    this.gravity = gravity;
  }
  
  /** Sets the reach of the throwing of a given projectile. */
  public void setReach(double reach, Projectile projectile){
//	projectile.setReach(reach);
	this.reach = reach;
  }
  
  /** Gets the gravity where the thrower will throw up any projectile. */
  public double getGravity(){
    return this.gravity;
  }
  
  /** Gets the angle formed with the horizontal axis to throw up any projectile. */
  public double getAngle(){
    return this.angle;
  }
  
  /** Gets the initial speed of the throwing of any projectile. */
  public double getInitialSpeed(){
    return this.initialSpeed;
  }

}
