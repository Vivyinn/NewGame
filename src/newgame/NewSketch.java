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
import processing.core.PImage;

public class NewSketch extends PApplet{
    private PImage gameBackground;
    private PImage startScreen;
    private Ping ping;   
    private boolean gameStarted = false;
    
    public void settings() {
        size (1200, 1000);
    }
    
    public void setup() {
        gameBackground = loadImage("images/EMPTY POT.png");
        startScreen = loadImage("images/EMPTY POT.png");
        
        ping = new Ping(this, width/2, height/2, 5);
        
    }
    
    public void draw() {
        if (!gameStarted) {
            // Show start screen
            image(startScreen, 0, 0, width, height);
        } else {
            // Show game
            image(gameBackground, 0, 0, width, height);
            ping.update();
            ping.draw();
        }
    }
    
    public void keyPressed() {
        if (!gameStarted && keyCode == ENTER) {
            gameStarted = true;
            ping.enableMovement(true); // Only allow movement after ENTER
        } 
    }
       
  }


    
    

