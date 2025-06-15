/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */


/**
 *
 * @author vivianwu
 */


package newgame;

import java.util.ArrayList;
import newgame.Entity.Bee;
import newgame.Entity.Entity;
import newgame.Entity.Ping;
import processing.core.PApplet;
import processing.core.PImage;

public class NewSketch extends PApplet{
    private static final int START_SCREEN = 0;
    private static final int INSTRUCTIONS = 1;
    private static final int SEED_SELECT = 2;
    private static final int OBSTACLE_PHASE = 3;
    private static final int CEREMONY = 4;
    private static final int FAIL = 5;
    private static final int DEATH = 6;
    private int sceneState = START_SCREEN;
   
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
    
    // Second Scene
    private float emperorX, emperorY;
    private final int PING_SIZE = 50;
    private final int EMPEROR_SIZE = 150;
    
    private Entity emperor;
    private boolean emperorActive = true;
    
    // obstacle phase
    private PImage selectImg;
    private PImage originalSeedImg;
    private PImage newSeedImg;
    private PImage warningImg;
    private PImage beeImg;
    private PImage heartImg;
    private PImage heartLostImg;
    private PImage emptyInventoryImg;
    private PImage waterInventoryImg;
    private PImage wellImg;
    private PImage waterCollectedImg;
    private PImage potImg;
    private PImage ceremonyImg;
    private PImage failImg;
    private PImage deathImg;  
    private ArrayList<Bee> bees = new ArrayList<>();
    private Entity pot, well;
    private boolean hasWater = false;
    private int health = 3;
    private boolean showWaterPopup = false;
    private int popupTimer = 0;
    private float camX = 0, camY = 0;
    
    
    public void settings() {
        size (1200, 1000);
    }
    
    public void setup() {
        gameBackground = loadImage("images/gameBackground.png");
        startScreen = loadImage("images/EMPTY POT.png");
        emperorImg = loadImage("images/emperor.png");
        obstacleBgImg = loadImage("images/obstacle_bg.png");
        instructionImg = loadImage("images/instructions.png");
        
        selectImg = loadImage("images/select.png");
        originalSeedImg = loadImage("images/originalSeed.png");
        newSeedImg = loadImage("images/newSeed.png");
        warningImg = loadImage("images/warning.png");
        beeImg = loadImage("images/bee.png");
        heartImg = loadImage("images/heart.png");
        heartLostImg = loadImage("images/heartLost.png");
        emptyInventoryImg = loadImage("images/emptyInventory.png");
        waterInventoryImg = loadImage("images/waterInventory.png");
        wellImg = loadImage("images/well.png");
        waterCollectedImg = loadImage("images/waterCollected.png");
        potImg = loadImage("images/pot.png");
        ceremonyImg = loadImage("images/ceremonyImg.png");
        failImg = loadImage("images/fail.png");
        deathImg = loadImage("images/death.png");
        
        emperorX = width - EMPEROR_SIZE - 180;
        emperorY = 100;  
        
        
        // emperor entity
        ping = new Ping(this, 25, height - PING_SIZE - 50, 5);
    
        emperor = new Entity(this);
        emperor.x = width - EMPEROR_SIZE - 20;
        emperor.y = 20;
        emperor.image = loadImage("images/emperor.png");
    
        pot = new Entity(this, width/2 - 25, height/2 - 25);
        pot.image = potImg;
    
        well = new Entity(this, width/4, height/4);
        well.image = wellImg;
    
        for(int i=0; i<5; i++) {
            bees.add(new Bee(this, random(width), random(height)));
        }
        
    }
    
    public void draw() {
        switch(sceneState) {
            case START_SCREEN:
                image(startScreen, 0, 0);
                break;
                
            case INSTRUCTIONS:
                displayOriginalGame();
                break;
                
            case SEED_SELECT:
                displaySeedSelection();
                break;
                
            case OBSTACLE_PHASE:
                runObstaclePhase();
                break;
                
            case CEREMONY:
                image(ceremonyImg, 0, 0);
                break;
                
            case FAIL:
                image(failImg, 0, 0);
                break;
                
            case DEATH:
                image(deathImg, 0, 0);
                break;
        }
    }
    
    public void displayOriginalGame() {
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
    
    private void displaySeedSelection() {
        image(selectImg, 0, 0);
        image(originalSeedImg, width/4 - 100, height/2 - 50, 200, 100);
        image(newSeedImg, 3*width/4 - 100, height/2 - 50, 200, 100);
    }

    private void runObstaclePhase() {
        camX = lerp(camX, width/2 - ping.x, 0.1f);
        camY = lerp(camY, height/2 - ping.y, 0.1f);
        translate(camX, camY);
        
        for(Bee bee : bees) {
            image(beeImg, bee.x, bee.y);
            if(dist(ping.x, ping.y, bee.x, bee.y) < 30) {
                bees.remove(bee);
                health--;
                if(health <= 0) sceneState = DEATH;
                break;
            }
        }
        
        image(wellImg, well.x, well.y);
        
        pushMatrix();
        resetMatrix();
        
        image(potImg, pot.x, pot.y);
        
        for(int i=0; i<3; i++) {
            image(i < health ? heartImg : heartLostImg, 20 + i*40, 20);
        }
        
        image(hasWater ? waterInventoryImg : emptyInventoryImg, 
             width/2 - 50, height - 100);
             
        if(showWaterPopup) {
            image(waterCollectedImg, width/2 - 200, height/2 - 150, 400, 300);
            if(millis() - popupTimer > 3000) {
                showWaterPopup = false;
            }
        }
        
        popMatrix();
        
        ping.update();
        ping.draw();
    }
    
    public void mousePressed() {
        if(sceneState == SEED_SELECT) {
            if(mouseX > width/4 - 100 && mouseX < width/4 + 100 &&
               mouseY > height/2 - 50 && mouseY < height/2 + 50) {
                sceneState = OBSTACLE_PHASE;
            } 
            else if(mouseX > 3*width/4 - 100 && mouseX < 3*width/4 + 100 &&
                    mouseY > height/2 - 50 && mouseY < height/2 + 50) {
                sceneState = FAIL;
            }
        }
        else if(sceneState == OBSTACLE_PHASE) {
            if(dist(ping.x, ping.y, well.x, well.y) < 50) {
                hasWater = true;
                showWaterPopup = true;
                popupTimer = millis();
            }
            else if(hasWater && dist(ping.x, ping.y, pot.x, pot.y) < 50) {
                sceneState = CEREMONY;
            }
        }
    }
     
    public void keyPressed() {
        if (sceneState == START_SCREEN && keyCode == ENTER) {
            gameStarted = true;
            ping.enableMovement(true); // Only allow movement after ENTER
            sceneState = INSTRUCTIONS;
        } 
        
        else if (sceneState == INSTRUCTIONS && keyCode == ENTER) {
            showInstructions = false;
            showObstaclePhase = true;
            emperorActive = false; // remove emperor 
            ping.enableMovement(true);
            sceneState = SEED_SELECT;
            
            // teleport Ping to center for obstacle phase
            ping.x = width/2 - PING_SIZE/2;
            ping.y = height/2 - PING_SIZE/2;
        }
        
        ping.handleKeyPress(keyCode);
    }
    
    public void keyReleased() {
        ping.handleKeyRelease(keyCode);
    }
    
  }


  
