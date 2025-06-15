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

public class Bee extends Entity{
    public Bee(PApplet sketch, float x, float y) {
        super(sketch, x, y);
        this.image = sketch.loadImage("images/bee.png");
    }
}
