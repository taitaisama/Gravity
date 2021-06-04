
package gravity;

import javax.swing.*;
import java.awt.*;

public class number extends JFrame{
    public number(String a,int b){
        
        JPanel p = new JPanel(new GridLayout(0,1));
        Container contents = getContentPane();
        p.add(new JLabel("You have exeeded the number of "+a+" that can be created."));
        p.add(new JLabel("There can be no more than "+b+" "+a+", please delete some if you want to continue"));
        contents.add(p);
        pack();
    }
}
