/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package newgame.Entity;

/**
 *
 * @author vivianwu
 */

import java.awt.image.BufferedImage;
import processing.core.PApplet;
import processing.core.PImage;

public class Entity {
    public float x, y;
    public PImage image;
    public int speed;
    public String direction;
    
    public boolean isCollidingWith(Ping other) {
        int centerX = (int)(x + (image.pixelWidth/2));
        int centerY = (int)(y + (image.pixelHeight/2));
        int otherCenterX = (int)(other.x+(other.image.pixelWidth/2));
        int otherCenterY = (int)(other.y+(other.image.pixelHeight/2));
        
        float distance = PApplet.dist(otherCenterX, otherCenterY, centerX, centerY);
        return distance <32;
    } 
}
