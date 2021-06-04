
package gravity;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
public class ColorPicker extends JFrame implements ActionListener{
    Container contents = getContentPane();
    JTextField R  = new JTextField("255");
    JTextField G  = new JTextField("255");
    JTextField B  = new JTextField("255");
    JLabel r = new JLabel("RED: ");
    JLabel g = new JLabel("GREEN: ");
    JLabel b = new JLabel("BLUE: ");
    JPanel panel = new JPanel();
    JPanel color = new JPanel();
    JPanel color1 = new JPanel();
    JButton OK = new JButton("OK");
    ColorPicker(){
        panel.setLayout(new GridLayout(0,2));
        panel.add(r);
        panel.add(R);
        R.addActionListener(this);
        panel.add(g);
        panel.add(G);
        G.addActionListener(this);
        panel.add(b);
        panel.add(B);
        B.addActionListener(this);
        color.setBackground(new Color(255,255,255));
        panel.add(color);
        color.setPreferredSize(new Dimension(100,50));
        color1.setBackground(new Color(255,255,255));
        panel.add(color1);
        panel.add(OK);
        OK.addActionListener(this);
        color1.setPreferredSize(new Dimension(100,50));
        contents.add(panel);
        pack();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource()==R){
            boolean j=false;
            try{
                int a = Integer.parseInt(R.getText());
            }
            catch(NumberFormatException err){
                j=true;
                error s =new error();
                s.setVisible(true);
                R.setText("255");
            }
            if(Integer.parseInt(R.getText())<=255){
                color.setBackground(new Color(Integer.parseInt(R.getText()),Integer.parseInt(G.getText()),Integer.parseInt(B.getText())));
                color1.setBackground(new Color(Integer.parseInt(R.getText()),Integer.parseInt(G.getText()),Integer.parseInt(B.getText())));
            }
            else{
                R.setText("255");
                color.setBackground(new Color(Integer.parseInt(R.getText()),Integer.parseInt(G.getText()),Integer.parseInt(B.getText())));
                color1.setBackground(new Color(Integer.parseInt(R.getText()),Integer.parseInt(G.getText()),Integer.parseInt(B.getText())));
            }
        }
        else if(e.getSource()==G){
            boolean j=false;
            try{
                int a = Integer.parseInt(G.getText());
            }
            catch(NumberFormatException err){
                j=true;
                error s =new error();
                s.setVisible(true);
                G.setText("255");
            }
            if(Integer.parseInt(G.getText())<=255){
                color.setBackground(new Color(Integer.parseInt(R.getText()),Integer.parseInt(G.getText()),Integer.parseInt(B.getText())));
                color1.setBackground(new Color(Integer.parseInt(R.getText()),Integer.parseInt(G.getText()),Integer.parseInt(B.getText())));
            }
            else{
                G.setText("255");
                color.setBackground(new Color(Integer.parseInt(R.getText()),Integer.parseInt(G.getText()),Integer.parseInt(B.getText())));
                color1.setBackground(new Color(Integer.parseInt(R.getText()),Integer.parseInt(G.getText()),Integer.parseInt(B.getText())));
            }
        }
        else if(e.getSource()==B){
            boolean j=false;
            try{
                int a = Integer.parseInt(B.getText());
            }
            catch(NumberFormatException err){
                j=true;
                error s =new error();
                s.setVisible(true);
                B.setText("255");
            }
            if(Integer.parseInt(B.getText())<=255){
                color.setBackground(new Color(Integer.parseInt(R.getText()),Integer.parseInt(G.getText()),Integer.parseInt(B.getText())));
                color1.setBackground(new Color(Integer.parseInt(R.getText()),Integer.parseInt(G.getText()),Integer.parseInt(B.getText())));
            }
            else{
                B.setText("255");
                color.setBackground(new Color(Integer.parseInt(R.getText()),Integer.parseInt(G.getText()),Integer.parseInt(B.getText())));
                color1.setBackground(new Color(Integer.parseInt(R.getText()),Integer.parseInt(G.getText()),Integer.parseInt(B.getText())));
            }
        }
        else if(e.getSource()==OK){
            Board.r.Color.setBackground(new Color(Integer.parseInt(R.getText()),Integer.parseInt(G.getText()),Integer.parseInt(B.getText())));
            Board.changeColor(new Color(Integer.parseInt(R.getText()),Integer.parseInt(G.getText()),Integer.parseInt(B.getText())));
        }
    }
}
