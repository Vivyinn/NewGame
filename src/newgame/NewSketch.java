/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */


/**
 *
 * @author vivianwu
 */


package newgame;

import newgame.Entity.Entity;
import newgame.Entity.Ping;
import processing.core.PApplet;
import processing.core.PImage;

public class NewSketch extends PApplet{
    private PImage gameBackground;
    private PImage startScreen;
    private PImage obstacleBgImg;
    private PImage instructionImg;
    private PImage emperorImg;
    private Ping ping;   
    
    // Enter to start game
    private boolean gameStarted = false;
    
    // Instructions screen
    private boolean showInstructions = false;
    
    // Obstacle Screen
    private boolean showObstaclePhase = false;
    
    // Third Scene - Collision/Instructions 
    private Entity emperor;
    private boolean emperorActive = true;
    
    // Second Scene
    private float emperorX, emperorY;
    private final int PING_SIZE = 50;
    private final int EMPEROR_SIZE = 150;
    private final int EMPEROR_RADIUS = 50;
    
    public void settings() {
        size (1200, 1000);
    }
    
    public void setup() {
        gameBackground = loadImage("images/gameBackground.png");
        startScreen = loadImage("images/EMPTY POT.png");
        emperorImg = loadImage("images/emperor.png");
        obstacleBgImg = loadImage("images/obstacle_bg.png");
        instructionImg = loadImage("images/instructions.png");
        
        emperorX = width - EMPEROR_SIZE - 180;
        emperorY = 100;  
        
        ping = new Ping(this, 25, height - PING_SIZE - 50, 5);
        
        // emperor entity
        emperor = new Entity();
        emperor.x = width - EMPEROR_SIZE - 20;
        emperor.y = 20;
        emperor.image = emperorImg;
    }
    
    public void draw() {
        if (!gameStarted) {
            image(startScreen, 0, 0);
            return;
        }
        
        // Main game background
        image(gameBackground, 0, 0);
        
        // Draw emperor
        image(emperorImg, emperorX, emperorY, EMPEROR_SIZE, EMPEROR_SIZE);
                
        
        
        // check collision using circular detection
        if (emperorActive) {
            image(emperorImg, emperor.x, emperor.y);
            
            if (emperor.isCollidingWith(ping) && !showInstructions) {
            showInstructions = true;
            ping.enableMovement(false);
            }
        }
        
        if (showInstructions) {
            image(instructionImg, width/2 - 200, height/2 - 150, 400, 300);
        } else if (showObstaclePhase) {
            image(obstacleBgImg, 0, 0);
        }
        
        // Always draw Ping
        ping.update();
        ping.draw();
        
    }
    
    public void keyPressed() {
        if (!gameStarted && keyCode == ENTER) {
            gameStarted = true;
            ping.enableMovement(true); // Only allow movement after ENTER
            return;
        } 
        
        if (showInstructions && keyCode == ENTER) {
            showInstructions = false;
            showObstaclePhase = true;
            emperorActive = false; // remove emperor 
            ping.enableMovement(true);
            
            // teleport Ping to center for obstacle phase
            ping.x = width/2 - PING_SIZE/2;
            ping.y = height/2 - PING_SIZE/2;
        }
        
        ping.handleKeyPress(keyCode);
    }
 
    
  }


  
