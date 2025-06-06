/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package newgame.Entity;

/**
 *
 * @author vivianwu
 */

//import java.io.IOException;
//import javax.imageio.ImageIO;
import processing.core.PApplet;
//import static processing.core.PConstants.DOWN;
//import static processing.core.PConstants.LEFT;
//import static processing.core.PConstants.RIGHT;
//import static processing.core.PConstants.UP;
import processing.core.PImage;

public class Ping extends Entity{
    public int x, y;
    public String name;
    private int age;
    private PImage image;
    private PApplet app;
    public int speed;
    
    public Ping (PApplet p, int x, int y, String name, int age, int speed, String imagePath) {
        this.app = p;
        this.x = x;
        this.y = y;
        this.name = name;
        this.speed = speed;
        this.age = age;
        this.image = app.loadImage(imagePath);
        
        //getPlayerImage();
    }
    
    public void move() {
        x = 100;
        y = 100;
        speed = 4;
        direction = "down";
    }
    
    public void draw() {
        app.image(image, x, y);
    }
    /*
    public void getPlayerImage() {
        try {
            up1 = ImageIO.read(getClass().getResourceAsStream("images/ping_up_1.png"));
            up2 = ImageIO.read(getClass().getResourceAsStream("images/ping_up_2.png"));
            down1 = ImageIO.read(getClass().getResourceAsStream("images/ping.png"));
            down2 = ImageIO.read(getClass().getResourceAsStream("images/ping_down_2.png"));
            left1 = ImageIO.read(getClass().getResourceAsStream("images/ping_left_1.png"));
            left2 = ImageIO.read(getClass().getResourceAsStream("images/ping_left_2.png"));
            right1 = ImageIO.read(getClass().getResourceAsStream("images/ping_right_1.png"));
            right2 = ImageIO.read(getClass().getResourceAsStream("images/ping_right_2.png"));

        } catch (IOException e) {
        }
    }
    
    public void update() {
        if (keyPressed) {
        if (keyCode == LEFT) {
          x -= speed;
        } else if (keyCode == RIGHT) {
          x += speed;
        } else if (keyCode == UP) {
          y -= speed;
        } else if (keyCode == DOWN) {
          y += speed;
        } 
    }
} */
}
