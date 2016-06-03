import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class SimoneDungeon extends Room{

    private int _x,_y,_sx,_sy;
    private boolean dead;

    public SimoneDungeon(){
        _x = 800;
        _y = 300;
        _sx = 200;
        _sy = 300;
        dead = false;
        repaint();
    }

    public void paintComponent(Graphics g){
        super.paintComponent(g);
        Drawing.dean(g, _sx, _sy);
        if (_x < 0) _x = 999;
        if (_x > 1000) _x = 1;
        if (_y > 500) _y = 1;
        if (_y < 0) _y = 499;
        Drawing.frisk(g,_x,_y);
        if (dead) Drawing.talk(g, _sx, _sy, "You cannot stay in school after five.");
        animate();
    }

    public void animate(){
        try {
            Thread.sleep(100);                 //1000 milliseconds is one second.
        } catch(InterruptedException ex) {
            Thread.currentThread().interrupt();
        }
        if (Math.abs(_y - _sy) < 100 && Math.abs(_x - _sx) < 100) dead = true;
        else if (_sy - 30 > _y) _sy -= 10;
        else if (_sy + 30 < _y) _sy += 10;
        else if (_sx - 30 > _x) _sx -= 10;
        else _sx += 10;
        repaint();
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
        return 1;
    }

    public int getVal(){
        return 0;
    }

}
