
package gravity;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;
public class addasteroid extends JFrame implements ActionListener{
    Container contents = getContentPane();
    JPanel x = new JPanel(new GridLayout(0,1));
    JLabel d = new JLabel("Speed");
    JTextField e = new JTextField("3");
    JPanel n = new JPanel();
    JPanel p = new JPanel();
    static JButton h= new JButton("Cancel");
    static JButton i= new JButton("Confirm");
    public addasteroid(){
        
        x.add(d);
        x.add(e);
        n.add(h);
        h.addActionListener(this);
        n.add(i);
        i.addActionListener(this);
        p.add(n , BorderLayout.EAST);
        x.add(p);
        contents.add(x);
        setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
        pack();
    }
    @Override
    public void actionPerformed(ActionEvent ae) {
        if(ae.getSource()==h){
            Board.addasteroidh();
        }
        else{
            Board.addasteroidi( e.getText());
        }
    }
}
