import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.awt.Rectangle.*;

public class ThirdFloorGym extends Room{

    private boolean dead; //it'd be better if this were kept track of throughout, easier to remove
    private boolean fell;
    private int[] holeX = {100,150,200,250,300,350,400,450,500,750,800,850,900,
                        100,150,200,250,300,600,650,950,
                        0,100,150,200,250,300,400,500,600,650,700,750,850,950,
                        0,200,250,300,400,500,600,700,850,950,
                        0,100,400,500,550,600,650,700,750,850,950,
                        0,100,400,500,550,
                        200,250,350,400,500,550,650,650,850,900,
                        200,250,350,400,500,550,900,950,
                        0,50,100,150,200,250,500,550,900,
                        450};
    private int[] holeY = {0,0,0,0,0,0,0,0,0,0,0,0,0,
                        50,50,50,50,50,50,50,50,
                        100,100,100,100,100,100,100,100,100,100,100,100,100,100,
                        150,150,150,150,150,150,150,150,150,150,
                        200,200,200,200,200,200,200,200,200,200,200,
                        250,250,250,250,250,
                        300,300,300,300,300,300,300,300,300,300,
                        350,350,350,350,350,350,350,350,
                        400,400,400,400,400,400,400,400,400,
                        450};

    public ThirdFloorGym(int val, Room left, Room right, Room prev){
        super(val, 0, left, right, prev);
        repaint();
    }
    public void paintComponent(Graphics g){
        super.paintComponent(g);
        setBackground(Color.ORANGE);
        loadMap(g);
        int holeNum = checkForHole();
        if (holeNum > -1) {
            fell = true;
            holeInFloor(holeNum, g);
        }
        System.out.println("x: " + _x);
        System.out.println("y: " + _y);
        animate();
    }

    public void loadMap(Graphics g){
        for (int i = 0; i < 1000; i = i + 50){
            for (int j = 0; j < 500; j = j + 50){
                g.setColor(Color.BLACK);
                g.drawRect(i, j, 50, 50);
            }
        }
    }

    public void holeInFloor(int holeNum, Graphics g){
        g.setColor(Color.BLUE);
        g.fillRect(holeX[holeNum], holeY[holeNum], 50, 50);
    }

    public void animate(){
        try {
            Thread.sleep(100);                 //1000 milliseconds is one second.
        } catch(InterruptedException ex) {
            Thread.currentThread().interrupt();
        }
        if (Math.abs(_y - _sy) < 20 && Math.abs(_x - _sx) < 20) dead = true;
        else if (_sy > _y) _sy -= 10;
        else if (_sy < _y) _sy += 10;
        else if (_sx > _x) _sx -= 10;
        else _sx += 10;

        repaint();
    }

    public int checkForHole(){
        for (int i = 0; i < holeX.length; i++){
            Rectangle square = new Rectangle(holeX[i], holeY[i], 50, 50);
            if (square.contains(_x, _y))
                    return i;
            }
        return -1;
    }

}
