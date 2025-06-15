/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package newgame.Entity;

/**
 *
 * @author vivianwu
 */

import processing.core.PApplet;
import processing.core.PImage;

public class Entity {
    public float x, y;
    public PImage image;
    public PApplet sketch;
    public String direction;
    public int speed;
        
    public Entity(PApplet sketch) {
        this.sketch = sketch;
    }
    
    public Entity(PApplet sketch, float x, float y) {
        this(sketch);
        this.x = x;
        this.y = y;
    }
    
    public boolean isCollidingWith(Ping other) {
        if (this.image == null || other.getCurrentImage() == null) {
        return false;
    }
        int centerX = (int)(x + (image.pixelWidth/2));
        int centerY = (int)(y + (image.pixelHeight/2));
        int otherCenterX = (int)(other.x+(other.image.pixelWidth/2));
        int otherCenterY = (int)(other.y+(other.image.pixelHeight/2));
        
        float distance = PApplet.dist(otherCenterX, otherCenterY, centerX, centerY);
        return distance <32;
    } 
}
