/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */


/**
 *
 * @author vivianwu
 */


package newgame;

import newgame.Entity.Ping;
import processing.core.PApplet;

public class NewSketch extends PApplet{
    private Map map; // declare map object
    private Ping mainCharacter;
    
    public void settings() {
        size (800, 800);
    }
    
    public void setup() {
        background (100, 100, 100);
        mainCharacter = new Ping(this, 3, 4, "Ping",20, 20, "images/ping.png");
        //map = new Map(this, 0, 0, "Map", 0, "images/map.jpg");
    }
    
    public void draw() {
        background(255);
        mainCharacter.draw();
        map.draw();
    }
       
  }


    
    

