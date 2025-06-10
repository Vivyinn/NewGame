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
    private PApplet app;
    private PImage[] upImages = new PImage[2];
    private PImage[] downImages = new PImage[2];
    private PImage[] leftImages = new PImage[2];
    private PImage[] rightImages = new PImage[2];
    private int currentFrame = 0;
    private int frameCounter = 0;
    private final int FRAME_DELAY = 10;
    
    public Ping (PApplet p, int x, int y, int speed, String imagePath) {
        this.app = p;
        this.x = x;
        this.y = y;
        this.speed = speed;
        this.direction = "down";            

        loadAnimationImages();
    }
    
    public void loadAnimationImages() {
        upImages[0] = app.loadImage("images/ping_up_1.png");
        upImages[1] = app.loadImage("images/ping_up_2.png");
        downImages[0] = app.loadImage("images/ping.png");
        downImages[1] = app.loadImage("images/ping_down_2.png");
        leftImages[0] = app.loadImage("images/ping_left_1.png");
        leftImages[1] = app.loadImage("images/ping_left_2.png");
        rightImages[0] = app.loadImage("images/ping_right_1.png");
        rightImages[1] = app.loadImage("images/ping_right_2.png");
    }    
        
    public void draw() {
        PImage currentImage = getCurrentImage();
        app.image(currentImage, x, y);
    }
    
    public PImage getCurrentImage() {
        switch(direction) {
            case "up":
                return upImages[currentFrame];
            case "down":
                return downImages[currentFrame];
            case "left":
                return leftImages[currentFrame];
            case "right":
                return rightImages[currentFrame];
            default:
                return downImages[currentFrame];
        }
    }

    
    public void update() {
        if (keyPressed) {
            if (keyCode == LEFT) {
                x -= speed;
                direction = "left";
                animate();
            } else if (keyCode == RIGHT) {
                x += speed;
                direction = "right";
                animate();
            } else if (keyCode == UP) {
                y -= speed;
                direction = "up";
                animate();
            } else if (keyCode == DOWN) {
                y += speed;
                direction = "down";
                animate();
            } 
        }
    }
    
    private void animate() {
        frameCounter++;
        if (frameCounter > FRAME_DELAY) {
            currentFrame = (currentFrame + 1 % 2);
            frameCounter = 0;
        }
    }
} 

