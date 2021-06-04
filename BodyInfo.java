
package gravity;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
public class BodyInfo extends JFrame implements ActionListener{
    Container contents = getContentPane();
    JLabel mass = new JLabel("Mass: ");
    JLabel type = new JLabel("Type: ");
    JLabel speed = new JLabel("Speed: ");
    JLabel direction = new JLabel("Direction: ");
    JLabel x = new JLabel("x: ");
    JLabel y = new JLabel("y: ");
    JLabel color = new JLabel("Color: ");
    JButton delete = new JButton("Delete");
    JLabel Type = new JLabel();
    JTextField Mass = new JTextField();
    JTextField Speed = new JTextField();
    JTextField Direction = new JTextField();
    JTextField X = new JTextField();
    JTextField Y = new JTextField();
    JButton Color = new JButton();
    JButton OK = new JButton("Done");
    JPanel panel = new JPanel();
    BodyInfo(asteroid a){
        panel.setLayout(new GridLayout(0,2));
        setText(a);
        panel.add(type);
        panel.add(Type);
        panel.add(speed);
        panel.add(Speed);
        Speed.addActionListener(this);
        panel.add(direction);
        panel.add(Direction);
        Direction.addActionListener(this);
        panel.add(x);
        panel.add(X);
        X.addActionListener(this);
        panel.add(y);
        panel.add(Y);
        Y.addActionListener(this);
        panel.add(color);
        panel.add(Color);
        panel.add(OK);
        OK.addActionListener(this);
        Color.addActionListener(this);
        panel.add(delete);
        delete.addActionListener(this);
        contents.add(panel);
        setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
        pack();
    }
    BodyInfo(star a){
        panel.setLayout(new GridLayout(0,2));
        setText(a);
        panel.add(type);
        panel.add(Type);
        panel.add(mass);
        panel.add(Mass);
        Mass.addActionListener(this);
        panel.add(x);
        panel.add(X);
        X.addActionListener(this);
        panel.add(y);
        panel.add(Y);
        Y.addActionListener(this);
        panel.add(color);
        panel.add(Color);
        Color.addActionListener(this);
        panel.add(OK);
        OK.addActionListener(this);
        panel.add(delete);
        delete.addActionListener(this);
        contents.add(panel);
        setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
        pack();
    }
    BodyInfo(planet a){
        panel.setLayout(new GridLayout(0,2));
        setText(a);
        panel.add(type);
        panel.add(Type);
        panel.add(speed);
        panel.add(Speed);
        panel.add(mass);
        panel.add(Mass);
        Mass.addActionListener(this);
        Speed.addActionListener(this);
        panel.add(direction);
        panel.add(Direction);
        Direction.addActionListener(this);
        panel.add(x);
        panel.add(X);
        X.addActionListener(this);
        panel.add(y);
        panel.add(Y);
        Y.addActionListener(this);
        panel.add(color);
        panel.add(Color);
        Color.addActionListener(this);
        panel.add(OK);
        OK.addActionListener(this);
        panel.add(delete);
        delete.addActionListener(this);
        contents.add(panel);
        setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
        pack();
    }
    public void setText(asteroid a){
        Type.setText("Asteroid");
        double l=Math.sqrt((a.xspeed*a.xspeed)+(a.yspeed*a.yspeed));
        Speed.setText(Double.toString(l));
        double u =(Math.toDegrees(Math.acos(a.xspeed/l)));
        if(a.yspeed>=0){
            u=360-u;
        }
        Direction.setText(Double.toString(u));
        X.setText(Double.toString(a.pos.x));
        Y.setText(Double.toString(a.pos.y));
        Color.setBackground(a.color);
    }
    public void setText(star a){
        Type.setText("Star");
        Mass.setText(Double.toString(a.mass));
        X.setText(Double.toString(a.pos.x));
        Y.setText(Double.toString(a.pos.y));
        Color.setBackground(a.color);
    }
    public void setText(planet a){
        Type.setText("Planet");
        Mass.setText(Double.toString(a.mass));
        double l=Math.sqrt((a.xspeed*a.xspeed)+(a.yspeed*a.yspeed));
        Speed.setText(Double.toString(l));
        double u =(Math.toDegrees(Math.acos(a.xspeed/l)));
        if(a.yspeed>=0){
            u=360-u;
        }
        Direction.setText(Double.toString(u));
        X.setText(Double.toString(a.pos.x));
        Y.setText(Double.toString(a.pos.y));
        Color.setBackground(a.color);
    }
    @Override
    public void actionPerformed(ActionEvent e) {
            if(e.getSource()==Mass){
                boolean iserror=false;
                try{
                    Board.changemass(Double.valueOf(Mass.getText()));
                }
                catch(NumberFormatException err){
                    error er = new error();
                    er.setVisible(true);
                    iserror=true;
                }
                if(!iserror){
                    Board.changemass(Double.valueOf(Mass.getText()));
                }
        }
        else if(e.getSource()==X){
            boolean iserror=false;
            try{
                Board.changeX(Double.valueOf(X.getText()));
            }
            catch(NumberFormatException err){
                error er = new error();
                er.setVisible(true);
                iserror=true;
            }
            if(!iserror){
                Board.changeX(Double.valueOf(X.getText()));
            }
        }
        
        else if(e.getSource()==Y){
            boolean iserror=false;
            try{
                Board.changeY(Double.valueOf(Y.getText()));
            }
            catch(NumberFormatException err){
                error er = new error();
                er.setVisible(true);
                iserror=true;
            }
            if(!iserror){
                Board.changeY(Double.valueOf(Y.getText()));
            }
        }
        else if(e.getSource()==Speed){
            boolean iserror=false;
            try{
                Board.changespeed(Double.valueOf(Speed.getText())*Math.cos(Math.toRadians(360-Double.valueOf(Direction.getText()))), Double.valueOf(Speed.getText())*Math.sin(Math.toRadians(360-Double.valueOf(Direction.getText()))));
            }
            catch(NumberFormatException err){
                error er = new error();
                er.setVisible(true);
                iserror=true;
            }
            if(!iserror){
                Board.changespeed(Double.valueOf(Speed.getText())*Math.cos(Math.toRadians(360-Double.valueOf(Direction.getText()))), Double.valueOf(Speed.getText())*Math.sin(Math.toRadians(360-Double.valueOf(Direction.getText()))));
            }
        }
        else if(e.getSource()==Direction){
            boolean iserror=false;
            try{
                Board.changespeed(Double.valueOf(Speed.getText())*Math.cos(Math.toRadians(360-Double.valueOf(Direction.getText()))), Double.valueOf(Speed.getText())*Math.sin(Math.toRadians(360-Double.valueOf(Direction.getText()))));
            }
            catch(NumberFormatException err){
                error er = new error();
                er.setVisible(true);
                iserror=true;
            }
            if(!iserror){
                Board.changespeed(Double.valueOf(Speed.getText())*Math.cos(Math.toRadians(360-Double.valueOf(Direction.getText()))), Double.valueOf(Speed.getText())*Math.sin(Math.toRadians(360-Double.valueOf(Direction.getText()))));
            }
        }
        else if(e.getSource()==Color){
            ColorPicker lel = new ColorPicker();
            lel.setVisible(true);
        }
        else if(e.getSource()==OK){
            for(int i=0;i<1;i++){
                if(!(Type.getText().equals("Star"))){
                    try{
                        Board.changespeed(Double.valueOf(Speed.getText())*Math.cos(Math.toRadians(360-Double.valueOf(Direction.getText()))), Double.valueOf(Speed.getText())*Math.sin(Math.toRadians(360-Double.valueOf(Direction.getText()))));
                    }
                    catch(NumberFormatException err){
                        error er = new error();
                        er.setVisible(true);
                        break;
                    }
                    Board.changespeed(Double.valueOf(Speed.getText())*Math.cos(Math.toRadians(360-Double.valueOf(Direction.getText()))), Double.valueOf(Speed.getText())*Math.sin(Math.toRadians(360-Double.valueOf(Direction.getText()))));
                }
                if(!(Type.getText().equals("Asteroid"))){
                    try{
                        Board.changemass(Double.valueOf(Mass.getText()));
                    }
                    catch(NumberFormatException err){
                        error er = new error();
                        System.out.println(Mass.getText());
                        er.setVisible(true);
                        break;
                    }
                    Board.changemass(Double.valueOf(Mass.getText()));
                }
                try{
                    Board.changeX(Double.valueOf(X.getText()));
                }
                catch(NumberFormatException err){
                    error er = new error();
                    System.out.println(X.getText());
                    er.setVisible(true);
                    break;
                }
                Board.changeX(Double.valueOf(X.getText()));
                try{
                    Board.changeY(Double.valueOf(Y.getText()));
                }
                catch(NumberFormatException err){
                    System.out.println(Y.getText());
                    error er = new error();
                    er.setVisible(true);
                    break;
                }
                Board.changeY(Double.valueOf(Y.getText()));
            }
            Gravity.b.editDone();
        }
        else if(e.getSource()==delete){
            Gravity.b.delete();
        }
    }
    
}
