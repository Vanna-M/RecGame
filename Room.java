import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class Room extends JPanel{

	// gets int corresponding to villain
    /*
        0 = no villain
        1 = Dean
        2 = Big Evil
        3 = Disciplinary
    */
	//protected int _villain = (int)(Math.random() * 4);
	protected int _x,_y,_sx,_sy, _val, _vill, _w, _h, _sh, _sw;
    private boolean dead;
    protected Room _left, _right, _prev;

	public Room(int val, int vill, Room left, Room right, Room prev){
        _val = val;
        _vill = vill;
		_x = 800;
        _y = 300;
        _sx = 200;
        _sy = 300;
        _w = 38;
        _h = 50;
        if (_vill == 1){
            _sh = 119;
            _sw = 44;
        }
        dead = false;
        _left = left;
        _right = right;
        _prev = prev;
        repaint();
	}

	public int getVal(){
		return _val;
	}

	public int getVillain(){
		return _vill;
	}

	public void paintComponent(Graphics g, int villain){
        super.paintComponent(g);
        Drawing.background(g, this.getWidth(), this.getHeight(), 0);
		if (_vill != 0)
			Drawing.dean(g, _sx, _sy, _vill);
        if (_x < 0) _x = 999;
        if (_x > 1000) _x = 1;
        if (_y > 500) _y = 1;
        if (_y < 0) _y = 499;
        Drawing.frisk(g,_x,_y, w, h);
		if(_vill != 0 && dead)
			Drawing.talk(g, _sx, _sy, _vill);
        animate();
    }

	public void animate(){
        try {
            Thread.sleep(100);                 //1000 milliseconds is one second.
        } catch(InterruptedException ex) {
            Thread.currentThread().interrupt();
        }
		if (_y < _sy && _y > _sy - 113){
            if (_x > _sx && _x < _sx + 86){
                dead = true;
            }
            else if (_x > _sx + 86) _sx+=10;
            else _sx -= 10;
        }
        else if (_y < _sy - 113) _sy -= 10;
        else _sy += 10;
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

}
