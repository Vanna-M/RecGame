import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class Rm639 extends Room{

    private int deskX1, deskY1, deskX2, deskY2, deskSize;
    private boolean note, found, done;

    public Rm639(int val, Room left, Room right, Room prev){
        super(val, 0, left, right, prev);
        deskX1 = 500;
        deskY1 = _y - 2*Math.abs(h - deskSize);
        deskSize = 100;
        deskX2 = deskX1 + deskSize;
        deskY2 = deskY1 + deskSize;
        repaint();
    }
    public Rm639(){
        this(0, null, null, null);
    }

    public void paintComponent(Graphics g){
    //    super.paintComponent(g, 39);

        if (_y > 0 && _y < 85){
            if (_x > 50 && _x < 50 + 58) System.out.println("left door");
            if (_x > 850 && _x < 850 + 58) System.out.println("right door");
        }
        if (_x + w + 20 > 1000) _x = 1000 - w;
        else if (_x < 0) _x = 0;
        if (_y + h + h + 20 > 500) _y = 500 - h - h;
        else if (_y - 20 < 0) _y = 0;
        // you crashed into the desk
        if (_x > deskX1 - w && _x < deskX2){
            if (_y > deskY1 - h && _y < deskY2){
                // bottom
                if (_y > deskY2 - h) _y = deskY2;
                // top
                else if (_y < deskY1) _y = deskY1 - h;
                // right
                else if (_x > deskX2 - w) _x = deskX2;
                // left
                else _x = deskX1 - w;
            }
        }

        Drawing.woodenBackground(g, this.getWidth(), this.getHeight());
        super.paintComponent(g, 1);
        Drawing.door(g);
        Drawing.desk(g, deskX1, deskY1, deskSize);
        Drawing.frisk(g, _x,_y, w, h);
        if (found){
            Drawing.talk(g,100,100,"Congratulations! You found a note. It says: \"Believe this to be true, the answer is inside of you\". What could that mean?");
            found = false;
        }
        if (done){
            Drawing.talk(g,100,100,"You found another piece of the rec!");
            done = false;
        }


    }

    public void mouseClicked(int x, int y){
        if (note){
            if (_x < x && _x + w > x && _y < y && _y + h > y){
                done = true;
                repaint();
            }
        }
        else if (deskX1 - w < x && deskX2 > x && deskY1 - h < y && deskY2 > y){
            note = true;
            found = true;
            repaint();
        }
    }
}
