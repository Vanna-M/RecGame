import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.awt.Image;
import java.awt.image.BufferedImage;
import javax.imageio.ImageIO;
import java.io.File;
import java.io.IOException;

public class Drawing{

    // all of the villains will send in the picture at the given coors
    public static void dean(Graphics g, int x, int y, int villain){
        BufferedImage d = null; // declare image
        try {
            d = ImageIO.read(new File("Dean.png")); // here's the source, labelled in order
        }
        catch (IOException e) { // required
        }
        g.drawImage(d, x, y, null);
        // put image d on Graphics g at position x,y
        //we're not modifying him in-game, so ImageObserver is null
    }

    public static void frisk(Graphics g, int x, int y, int w, int h){
        BufferedImage img = null; // declare image
        try {
            img = ImageIO.read(new File("Frisk.png")); // here's the source
        }
        catch (IOException e) { // required
        }
        g.drawImage(img, x, y, w, h, null);
        // put image d on Graphics g at position x,y
        //we're not modifying him in-game, so ImageObserver is null
    }

    public static void desk(Graphics g, int x, int y, int size){
        BufferedImage img = null;
        try {
            img = ImageIO.read(new File("Desk.png"));
        }
        catch (IOException e){
        }
        g.drawImage(img, x, y, size, size, null);
    }

    public static void talk(Graphics g, int x, int y, String message){
        g.drawString(message, x + 50, y - 30);
    }

    public static void talk(Graphics g, int x, int y, int villain){
		String[] messages = {"dean1 says this","dean2 says this","dean3 says this"};
        g.drawString(messages[villain-1], x + 50, y - 30);
    }

    public static void background(Graphics g, int w, int h, int n){
        BufferedImage bckgrnd = null; // declare image
        if (n == 0){
            try {
                bckgrnd = ImageIO.read(new File("Rm639.png")); // here's the source
            }
            catch (IOException e) { // required
            }
        }
        g.setColor(Color.WHITE);
        g.drawImage(bckgrnd,0,0,w,h,null);
    }

    // doors always in same place?
    // picture is 78 x 85
    public static void door(Graphics g){
        BufferedImage door = null;
        try{
            door = ImageIO.read(new File("Door.png"));
        }
        catch (IOException e){
        }
        g.drawImage(door,50,0,null);
        g.drawImage(door,850,0,null);
    }
}
