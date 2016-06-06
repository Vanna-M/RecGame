import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class ThirdFloorGym extends Room{

    private int _x,_y,_sx,_sy,_w,_h;
    private boolean dead; //it'd be better if this were kept track of throughout, easier to remove
    private boolean fell;
    private Room prev, next;

    public ThirdFloorGym(){
        _x = 800;
        _y = 300;
        _sx = 200;
        _sy = 300;
        _w = 38;
        _h = 58;
        _villain = 1;
        dead = false;
        fell = false;
        prev = null;
        next = null;
        repaint();
    }

    public void paintComponent(Graphics g){
        super.paintComponent(g);
        setBackground(Color.ORANGE);
        loadMap(g);
        Drawing2.dean(g, _sx, _sy, _villain);
        if (_x < 0) _x = 999;
        if (_x > 1000) _x = 1;
        if (_y > 500) _y = 1;
        if (_y < 0) _y = 499;
        Drawing2.frisk(g,_x,_y);
        if (dead && _villain > 0) Drawing2.talk(g, _sx, _sy, 1);
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
        g.setColor(Color.YELLOW);
        g.fillRect(950,0,50,50);
        g.fillRect(950,300,50,50);
        g.fillRect(950,450,50,50);    
    }

    public void holeInFloor(int holeNum, Graphics g){
        int[] holeX = {100,150,200,250,300,350,400,450,500,750,800,850,900,
                        100,150,200,250,300,600,650,950,
                        0,100,150,200,250,300,400,500,600,650,700,750,850,950,
                        0,200,250,300,400,500,600,700,850,950,
                        0,100,400,500,550,600,650,700,750,850,950,
                        0,100,400,500,550,
                        200,250,350,400,500,550,650,650,850,900,
                        200,250,350,400,500,550,900,950,
                        0,50,100,150,200,250,500,550,900,
                        450};
        int[] holeY = {0,0,0,0,0,0,0,0,0,0,0,0,0,
                        50,50,50,50,50,50,50,50,
                        100,100,100,100,100,100,100,100,100,100,100,100,100,100,
                        150,150,150,150,150,150,150,150,150,150,
                        200,200,200,200,200,200,200,200,200,200,200,
                        250,250,250,250,250,
                        300,300,300,300,300,300,300,300,300,300,
                        350,350,350,350,350,350,350,350,
                        400,400,400,400,400,400,400,400,400,
                        450};
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
        int x = _x + _w;
        int y = _y + _h;
        int[] holeX = {100,150,200,250,300,350,400,450,500,750,800,850,900,950,
                        100,150,200,250,300,600,650,950,
                        0,100,150,200,250,300,400,500,600,650,700,750,850,950,
                        0,200,250,300,400,500,600,700,850,950,
                        0,100,400,500,550,600,650,700,750,850,950,
                        0,100,400,500,550,
                        200,250,350,400,500,550,650,650,850,900,
                        200,250,350,400,500,550,900,950,
                        0,50,100,150,200,250,500,550,900,
                        450};
        int[] holeY = {0,0,0,0,0,0,0,0,0,0,0,0,0,0,
                        50,50,50,50,50,50,50,50,
                        100,100,100,100,100,100,100,100,100,100,100,100,100,100,
                        150,150,150,150,150,150,150,150,150,150,
                        200,200,200,200,200,200,200,200,200,200,200,
                        250,250,250,250,250,
                        300,300,300,300,300,300,300,300,300,300,
                        350,350,350,350,350,350,350,350,
                        400,400,400,400,400,400,400,400,400,
                        450};
        for (int i = 0; i < holeX.length; i++){
            if ((x >= holeX[i] && x <= holeX[i] + 50) && (y >= holeY[i] && y <= holeY[i] + 50))
                    return i;
            }
        return -1;
    }

    public void right(){
        _x += 20;
        repaint();
    }

    public void left(){
        _x -= 20;
        repaint();
    }

    public void up(){
        _y -= 20;
        repaint();
    }

    public void down(){
        _y += 20;
        repaint();
    }

    public void mouseClicked(int x, int y){
        return;
    }

    public int getVillain(){
        return _villain;
    }

    public int getVal(){
        return 0;
    }

}
