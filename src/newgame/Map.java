/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package newgame;

/**
 *
 * @author vivianwu
 */

import processing.core.PApplet;
import processing.core.PImage;

public class Map {
    private PApplet app; // canvas used to display graphical elements
    private PImage image; //image of the map
    private int x, y;
    
    public void draw() {
        app.image(image, x, y);
    }
}
