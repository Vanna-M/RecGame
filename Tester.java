import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class Tester implements KeyListener, MouseListener{

    private Room room;
    private JPanel diag;
    private JLabel text, space;
    private String toPrint;
    private String[] order;
    private int n;

    public Tester(){
        JFrame mainframe = new JFrame();
        mainframe.setVisible(true);
        mainframe.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        mainframe.setSize(1000,500);
        mainframe.addKeyListener(this);
        mainframe.addMouseListener(this);
        mainframe.setFocusable(true);
        mainframe.setFocusTraversalKeysEnabled(false);
        mainframe.setLayout(new BorderLayout());

        room = new Rm639(0,null, null, null);
        mainframe.add(room, BorderLayout.CENTER);

        n = 0;
        order = new String[3];
        order[0] = "TEST";
        order[1] = "Hello!";
        order[2] = "Success";
        toPrint = order[n];
        space = new JLabel("Press space to continue", JLabel.CENTER);
        text = new JLabel(toPrint, JLabel.CENTER);


        JButton exit = new JButton("Exit"); // create button w/word "Exit"
        exit.setActionCommand("Exit"); // when exit is pressed, send signal "Exit"
        exit.addActionListener(new ExitListener());
        mainframe.add(exit, BorderLayout.PAGE_END);

        diag = new JPanel();
        diag.setLayout(new BorderLayout());
        diag.add(text, BorderLayout.CENTER);
        diag.add(space, BorderLayout.PAGE_END);

        mainframe.add(diag, BorderLayout.PAGE_START);
    }

    //********KEYBOARD ACTIONS********

    // required to override KeyListener
    public void keyTyped(KeyEvent e) {
        return;
    }


    // allows for movement
    /** Handle the key-pressed event from the text field. */
    public void keyPressed(KeyEvent e) {
        if (e.getKeyCode() == KeyEvent.VK_RIGHT){ // right arrow key
            //System.out.println("Go right");
            room.right();
        }

        if (e.getKeyCode() == KeyEvent.VK_LEFT){ // left arrow key
            //System.out.println("Go left");
            room.left();
        }

        if (e.getKeyCode() == KeyEvent.VK_UP){ // up arrow key
            //System.out.println("Jump");
            room.up();
        }

        if (e.getKeyCode() == KeyEvent.VK_DOWN){ // down arrow key
            //System.out.println("Squat");
            room.down();
        }

        if (e.getKeyCode() == KeyEvent.VK_SPACE){
            progress();
        }
    }

    // required to override KeyListener
    /** Handle the key-released event from the text field. */
    public void keyReleased(KeyEvent e) {
        return;
    }

    public void mouseClicked(MouseEvent e){
        room.mouseClicked(e.getX(), e.getY());
    }
    public void mouseEntered(MouseEvent e){
        return;
    }
    public void mouseExited(MouseEvent e){
        return;
    }
    public void mousePressed(MouseEvent e){
        return;
    }
    public void mouseReleased(MouseEvent e){
        return;
    }

    // pause program for time ms
    public void pause(int time){
        try {
            Thread.sleep(time);                 //1000 milliseconds is one second.
        } catch(InterruptedException ex) {
            Thread.currentThread().interrupt();
        }
    }

    private class ExitListener implements ActionListener{
        public void actionPerformed(ActionEvent e) {
            String action = e.getActionCommand();
            if (action.equals("Exit")) System.exit(0); // if clicked, exit
            // NOTE : 0 just means we exited on purpose
            // if exit's parameter != 0, it's a code for what went wrong
            // that made the program exit
        }
    }

    public void progress(){
        toPrint = order[++n];
        text.setText(toPrint);
        //repaint();
    }

    public static void main(String[] args){
        Tester tester = new Tester();
    }

}
