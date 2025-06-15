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

public class Ping extends Entity{
    private final PApplet app; 
    private final PImage[][] animations = new PImage[4][2]; // stores animation movement frames
    private int currentFrame = 0; // the current animation frame (0 or 1)
    private int frameCounter = 0; // frame counter for animation timing
    private final int FRAME_DELAY = 10;
    private boolean canMove = false; // locks movement until user hits ENTER to start game
    private final boolean[] activeKeys = new boolean[4]; // tracks UP, RIGHT, DOWN, LEFT
    
    public Ping(PApplet sketch, float x, float y, int speed) {
        super(sketch, x, y);
        this.app = sketch;
        this.speed = speed;
        this.direction = "down"; // default facing down direction

        loadAnimationImages(); // load al animation frames
    }
    
    public void loadAnimationImages() {
        try {
            animations[0][0] = app.loadImage("images/ping.png");
            animations[0][1] = app.loadImage("images/ping_down_2.png");
            animations[1][0] = app.loadImage("images/ping_up_1.png");
            animations[1][1] = app.loadImage("images/ping_up_2.png");
            animations[2][0] = app.loadImage("images/ping_left_1.png");
            animations[2][1] = app.loadImage("images/ping_left_2.png");
            animations[3][0] = app.loadImage("images/ping_right_1.png");
            animations[3][1] = app.loadImage("images/ping_right_2.png");
        } catch (Exception e) {
            System.out.print(e);
        }
    }    
    
    public void enableMovement(boolean enable) {
        this.canMove = enable; // unlocks movement 
    }  
    
    public void draw() {
            PImage img = getCurrentImage(); // gets current animation frame based on the direction
            if (img != null) {
                app.image(img, x, y, 100, 100); /// draw character at current position
            }
    }

    
    public PImage getCurrentImage() {
        // default to first frame if null
    if (animations == null || animations[0][0] == null) {
        return sketch.loadImage("images/ping.png"); // Fallback image
    }
        // converts direction string to array index
        int animIndex = switch (direction) { // takes direction string 
            case "up" -> 1; // index 1
            case "left" -> 2; //index 2
            case "right" -> 3; //index3
            default -> 0; //index 0
        };
        return animations[animIndex][currentFrame] != null ? animations[animIndex][currentFrame] : animations[0][0]; // fallback to first frame
    }
    
    
    public void update() {
        if (!canMove) return;
        if (app.keyPressed) {
            if (app.keyCode == PApplet.LEFT) {
                x = Math.max(0, x - speed);
                direction = "left";
                animate();
            } else if (app.keyCode == PApplet.RIGHT) {
                x = Math.min(app.width - 50, x + speed);
                direction = "right";
                animate();
            } else if (app.keyCode == PApplet.UP) {
                y = Math.max(0, y - speed);
                direction = "up";
                animate();
            } else if (app.keyCode == PApplet.DOWN) {
                y = Math.min(app.height - 50, y + speed);
                direction = "down";
                animate();
            }
        }
    }
    
    private void animate() {
        frameCounter++;
        if (frameCounter > FRAME_DELAY) {
            currentFrame = (currentFrame + 1) % 2; // switch between 0 and 1
            frameCounter = 0;
        }
    }
    
    public void handleKeyPress(int keyCode) {
        if (keyCode == PApplet.UP) activeKeys[0] = true;
        if (keyCode == PApplet.RIGHT) activeKeys[1] = true;
        if (keyCode == PApplet.DOWN) activeKeys[2] = true;
        if (keyCode == PApplet.LEFT) activeKeys[3] = true;
        
    }
    
    public void handleKeyRelease(int keyCode) {
        if (keyCode == PApplet.UP) activeKeys[0] = false;
        if (keyCode == PApplet.RIGHT) activeKeys[1] = false;
        if (keyCode == PApplet.DOWN) activeKeys[2] = false;
        if (keyCode == PApplet.LEFT) activeKeys[3] = false;
    }
} 

