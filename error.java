
package gravity;
import java.awt.*;
import javax.swing.*;
public class error extends JFrame{
    JLabel a = new JLabel("Please enter numeric values");
    public error(){
        
        Container contents = getContentPane();
        contents.setLayout(new GridLayout(0,1));
        contents.add(a);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        pack();
    }
}
