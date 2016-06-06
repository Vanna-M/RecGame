import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.awt.Rectangle.*;

public class Rm639 extends Room{

    private int deskX1, deskY1, deskX2, deskY2, deskSize;
    private boolean note, found, done;

    public Rm639(int val, Room left, Room right, Room prev){
        super(val, 1, left, right, prev);
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

        
        super.paintComponent(g);
        Drawing.door(g);
        Drawing.desk(g, deskX1, deskY1, deskSize);
        if (found){
            Drawing.talk(g,100,100,"Congratulations! You found a note. It says: \"Believe this to be true, the answer is inside of you\". What could that mean?");
        }
        if (done){
            Drawing.talk(g,100,110,"You found another piece of the rec!");
        }


    }

    public void mouseClicked(int x, int y){
        Rectangle frisk = new Rectangle(_x, _y, _w + 50, _h + 50);
        Rectangle desk = new Rectangle(deskX1, deskY1, deskSize, deskSize);

        if (desk.contains(x,y)){
            note = true;
            found = true;
        }
        if (note){
            if (frisk.contains(x,y)){
                done = true;
                found = false;
            }
        }
        repaint();
    }
}
