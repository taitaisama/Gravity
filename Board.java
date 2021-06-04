package gravity;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JPanel;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.Timer;

public class Board extends JFrame implements ActionListener {

    double DELAY = 5;
    
    static boolean ispartytime = false;
    static addasteroid as = new addasteroid();
    static addstar st = new addstar();
    static addplanet pl = new addplanet();
    static error errorwindow = new error();
    static double mass;
    static double xspeed;
    static double yspeed;
    static double speed;
    static double radius;
    static boolean isplanet1;
    static boolean isplanet2;
    static boolean isasteroid1;
    static boolean isasteroid2;
    static boolean isstar1;
    long time;
    static BodyInfo r;
    number n;
    static boolean iserror = false;
    Timer timer = new Timer((int) DELAY, this);
    static double scale = 1;
    static JButton play = new JButton("►");
    boolean isplay = false;
    static planet[] planets = new planet[21];
    static star[] stars = new star[11];
    static asteroid[] asteroids = new asteroid[41];
    static JButton planet = new JButton("add planet");
    static JButton delete = new JButton("Clear");
    static JButton star = new JButton("add star");
    static JButton asteroid = new JButton("add asteroid");
    static JButton d1 = new JButton("Clear off screen");
    static JButton party = new JButton("Party Mode!");
    Container contents = getContentPane();
    JPanel main = new JPanel();
    JPanel left = new JPanel();
    static Canvas canvas;

    public Board() {
        canvas = new Canvas();
        setSize(1000, 800);
        left.setLayout(new FlowLayout());
        left.add(play);
        left.setBackground(Color.black);
        play.setBackground(Color.black);
        play.setForeground(Color.white);
        play.setBorderPainted(false);
        play.addActionListener(this);
        left.add(planet);
        planet.setBackground(Color.black);
        planet.setForeground(Color.white);
        planet.setBorderPainted(false);
        planet.addActionListener(this);
        left.add(star);
        star.setBackground(Color.black);
        star.setForeground(Color.white);
        star.setBorderPainted(false);
        star.addActionListener(this);
        left.add(asteroid);
        asteroid.setBackground(Color.black);
        asteroid.setForeground(Color.white);
        asteroid.setBorderPainted(false);
        asteroid.addActionListener(this);
        left.add(delete);
        delete.setBackground(Color.black);
        delete.setForeground(Color.white);
        delete.setBorderPainted(false);
        delete.addActionListener(this);
        left.add(d1);
        d1.setBackground(Color.black);
        d1.setForeground(Color.white);
        d1.setBorderPainted(false);
        d1.addActionListener(this);
        left.add(party);
        party.setBackground(Color.black);
        party.setForeground(Color.white);
        party.setBorderPainted(false);
        party.addActionListener(this);
        main.add(left, BorderLayout.NORTH);
        main.add(canvas, BorderLayout.CENTER);
        canvas.setFocusable(true);
        main.setPreferredSize(new Dimension(1200, 1500));
        main.setBackground(Color.BLACK);
        contents.add(main);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setTitle("Gravity");
        pack();
    }

    @Override
    public void actionPerformed(ActionEvent e) {

        if (e.getSource() == asteroid) {
            arange();
            if (num(asteroids) == 41) {
                n = new number("asteroids", 40);
                n.setVisible(true);
            } else {
                as.setVisible(true);
                setenabled(false);
            }
        } else if (e.getSource() == timer) {
            mutate();
            canvas.repaint();
        } else if (e.getSource() == star) {
            arange();
            if (num(stars) == 10) {
                n = new number("stars", 10);
                n.setVisible(true);
            } else {
                st.setVisible(true);
                setenabled(false);
            }
        } else if (e.getSource() == planet) {
            arange();
            if (num(planets) == 21) {
                n = new number("planets", 20);
                n.setVisible(true);
            } else {
                pl.setVisible(true);
                setenabled(false);
            }
        } else if (e.getSource() == play) {
            if (isplay) {
                timer.stop();
                isplay = false;
                play.setText("►");
            } else {
                timer.start();
                time = System.currentTimeMillis();
                isplay = true;
                play.setText("▋▋");
            }
            canvas.grabFocus();
        } else if (e.getSource() == delete) {
            for (int i = 0; i < stars.length; i++) {
                stars[i] = null;
            }
            for (int i = 0; i < planets.length; i++) {
                planets[i] = null;
            }
            for (int i = 0; i < asteroids.length; i++) {
                asteroids[i] = null;
            }
            arange();
            timer.start();
            time = System.currentTimeMillis();
            isplay = true;
            play.setText("▋▋");
            canvas.grabFocus();
        } else if (e.getSource() == d1) {
            for (int i = 0; i < stars.length; i++) {
                if (stars[i] != null) {
                    if ((stars[i].pos.x * scale) - (500 * (scale - 1)) > 1000 || (stars[i].pos.x * scale) - (500 * (scale - 1)) < 0 || (stars[i].pos.y * scale) - (500 * (scale - 1)) > 700 || (stars[i].pos.y * scale) - (500 * (scale - 1)) < 0) {
                        stars[i] = null;
                        arange();
                    }
                }
            }
            for (int i = 0; i < planets.length; i++) {
                if (planets[i] != null) {
                    if ((planets[i].pos.x * scale) - (500 * (scale - 1)) > 1000 || (planets[i].pos.x * scale) - (500 * (scale - 1)) < 0 || (planets[i].pos.y * scale) - (500 * (scale - 1)) > 700 || (planets[i].pos.y * scale) - (500 * (scale - 1)) < 0) {
                        planets[i] = null;
                        arange();
                    }
                }
            }
            for (int i = 0; i < asteroids.length; i++) {
                if (asteroids[i] != null) {
                    if ((asteroids[i].pos.x * scale) - (500 * (scale - 1)) > 1000 || (asteroids[i].pos.x * scale) - (500 * (scale - 1)) < 0 || (asteroids[i].pos.y * scale) - (500 * (scale - 1)) > 700 || (asteroids[i].pos.y * scale) - (500 * (scale - 1)) < 0) {
                        asteroids[i] = null;
                        arange();
                    }
                }
            }
            canvas.grabFocus();
        } else if (e.getSource() == party) {
            ispartytime = !ispartytime;
            canvas.grabFocus();
        }
    }

    public void mutate() {

        for (int i = 0; i < planets.length; i++) {
            if (planets[i] != null) {
                boolean dobreak = false;
                planets[i].addpos(planets[i].pos);
                planets[i].pos.x += planets[i].xspeed;
                planets[i].pos.y += planets[i].yspeed;
                for (int j = 0; j < stars.length; j++) {
                    if (stars[j] != null) {
                        if ((Math.pow((Math.pow((stars[j].pos.x + stars[j].radius / 2 - planets[i].pos.x + planets[i].radius / 2), 2) + Math.pow((stars[j].pos.y + stars[j].radius / 2 - planets[i].pos.y + planets[i].radius / 2), 2)), 0.5)) < stars[j].radius / 2) {
                            planets[i] = null;
                            arange();
                            dobreak = true;
                        }
                        if (dobreak) {
                            break;
                        }
                        planets[i].xspeed += ((stars[j].pos.x - planets[i].pos.x + planets[i].radius / 2 + stars[j].radius / 2) * 6.6741 * Math.pow(10, -1) * stars[j].mass) / (Math.pow((Math.pow((stars[j].pos.x + stars[j].radius / 2 - planets[i].pos.x + planets[i].radius / 2), 2) + Math.pow((stars[j].pos.y + stars[j].radius / 2 - planets[i].pos.y + planets[i].radius / 2), 2)), 1.5));
                        planets[i].yspeed += ((stars[j].pos.y + stars[j].radius / 2 - planets[i].pos.y + planets[i].radius / 2) * 6.6741 * Math.pow(10, -1) * stars[j].mass) / (Math.pow((Math.pow((stars[j].pos.x + stars[j].radius / 2 - planets[i].pos.x + planets[i].radius / 2), 2) + Math.pow((stars[j].pos.y + stars[j].radius / 2 - planets[i].pos.y + planets[i].radius / 2), 2)), 1.5));
                    }
                }
                if (!dobreak) {
                    for (int j = 0; j < planets.length; j++) {
                        if (planets[j] != null) {
                            if (j != i) {
                                planets[i].xspeed += ((planets[j].pos.x + planets[j].radius / 2 - planets[i].pos.x + planets[i].radius / 2) * 6.6741 * Math.pow(10, -1) * planets[j].mass) / (Math.pow((Math.pow(planets[j].pos.x + planets[j].radius / 2 - planets[i].pos.x + planets[i].radius / 2, 2) + Math.pow(planets[j].pos.y + planets[j].radius / 2 - planets[i].pos.y + planets[i].radius / 2, 2)), 1.5));
                                planets[i].yspeed += ((planets[j].pos.y + planets[j].radius / 2 - planets[i].pos.y + planets[i].radius / 2) * 6.6741 * Math.pow(10, -1) * planets[j].mass) / (Math.pow((Math.pow(planets[j].pos.x + planets[j].radius / 2 - planets[i].pos.x + planets[i].radius / 2, 2) + Math.pow(planets[j].pos.y + planets[j].radius / 2 - planets[i].pos.y + planets[i].radius / 2, 2)), 1.5));
                            }
                        }
                    }
                }
            }
        }
        for (int i = 0; i < asteroids.length; i++) {
            if (asteroids[i] != null) {
                boolean dobreak = false;
                asteroids[i].addpos(asteroids[i].pos);
                asteroids[i].pos.x += asteroids[i].xspeed * DELAY / 5;
                asteroids[i].pos.y += asteroids[i].yspeed * DELAY / 5;
                for (int j = 0; j < stars.length; j++) {
                    if (stars[j] != null) {
                        if ((Math.pow((Math.pow(stars[j].pos.x + stars[j].radius / 2 - asteroids[i].pos.x, 2) + Math.pow(stars[j].pos.y + stars[j].radius / 2 - asteroids[i].pos.y, 2)), 0.5)) < stars[j].radius / 2) {
                            asteroids[i] = null;
                            arange();
                            dobreak = true;
                        }
                        if (dobreak) {
                            break;
                        }
                        asteroids[i].xspeed += ((stars[j].pos.x + stars[j].radius / 2 - asteroids[i].pos.x) * 6.6741 * Math.pow(10, -1) * stars[j].mass) / (Math.pow((Math.pow(stars[j].pos.x + stars[j].radius / 2 - asteroids[i].pos.x, 2) + Math.pow(stars[j].pos.y + stars[j].radius / 2 - asteroids[i].pos.y, 2)), 1.5));
                        asteroids[i].yspeed += ((stars[j].pos.y + stars[j].radius / 2 - asteroids[i].pos.y) * 6.6741 * Math.pow(10, -1) * stars[j].mass) / (Math.pow((Math.pow(stars[j].pos.x + stars[j].radius / 2 - asteroids[i].pos.x, 2) + Math.pow(stars[j].pos.y + stars[j].radius / 2 - asteroids[i].pos.y, 2)), 1.5));
                    }
                }
                if (!dobreak) {
                    for (int j = 0; j < planets.length; j++) {
                        if (planets[j] != null) {
                            if ((Math.pow((Math.pow(planets[j].pos.x + planets[j].radius / 2 - asteroids[i].pos.x, 2) + Math.pow(planets[j].pos.y + planets[j].radius / 2 - asteroids[i].pos.y, 2)), 0.5)) < planets[j].radius / 2) {
                                asteroids[i] = null;
                                arange();
                                break;
                            }

                            asteroids[i].xspeed += ((planets[j].pos.x + planets[j].radius / 2 - asteroids[i].pos.x) * 6.6741 * Math.pow(10, -1) * planets[j].mass) / (Math.pow((Math.pow(planets[j].pos.x + planets[j].radius / 2 - asteroids[i].pos.x, 2) + Math.pow(planets[j].pos.y + planets[j].radius / 2 - asteroids[i].pos.y, 2)), 1.5));
                            asteroids[i].yspeed += ((planets[j].pos.y + planets[j].radius / 2 - asteroids[i].pos.y) * 6.6741 * Math.pow(10, -1) * planets[j].mass) / (Math.pow((Math.pow(planets[j].pos.x + planets[j].radius / 2 - asteroids[i].pos.x, 2) + Math.pow(planets[j].pos.y + planets[j].radius / 2 - asteroids[i].pos.y, 2)), 1.5));
                        }
                    }
                }
            }
        }
    }

    public static int num(Body[] a) {
        int i;
        for (i = 0; i < a.length; i++) {
            if (a[i] == null) {
                break;
            }
        }
        return i;
    }

    public static void createplanet(double a, position p) {

        xspeed = speed * Math.cos(Math.toRadians(360 - a));
        yspeed = speed * Math.sin(Math.toRadians(360 - a));
        arange();
        p.x += radius;
        p.y += radius;
        planets[num(planets)] = new planet(mass, xspeed, radius, yspeed, p);
        setenabled(true);
    }

    public static void createstar(position p) {
        p.x += radius / 2;
        p.y += radius / 2;
        arange();
        stars[num(stars)] = new star(mass, radius, p);
        setenabled(true);
    }

    public static void createasteroid(double a, position p) {

        xspeed = speed * Math.cos(Math.toRadians(360 - a));
        yspeed = speed * Math.sin(Math.toRadians(360 - a));
        p.x += radius;
        p.y += radius;
        arange();
        asteroids[num(asteroids)] = new asteroid(xspeed, radius, yspeed, p);
        setenabled(true);
    }

    public static void addplaneti(String m, String s) {
        iserror = false;
        try {
            mass = Double.valueOf(m);
            speed = Double.valueOf(s);
            radius = Math.cbrt(mass) * 2;
        } catch (NumberFormatException err) {
            iserror = true;
        }
        if (iserror) {
            errorwindow.setVisible(true);
        } else {
            mass = Double.valueOf(m);
            speed = Double.valueOf(s);
            radius = Math.cbrt(mass) * 2;
            pl.setVisible(false);
            isplanet1 = true;
        }
        canvas.grabFocus();
    }

    public static void addplaneth() {
        pl.setVisible(false);
        setenabled(true);
        canvas.grabFocus();
    }

    public static void addstari(String m) {
        iserror = false;
        try {
            mass = Double.valueOf(m);
            radius = Math.cbrt(mass) * 2;
        } catch (NumberFormatException err) {
            iserror = true;
        }
        if (iserror) {
            errorwindow.setVisible(true);
        } else {
            mass = Double.valueOf(m);
            radius = Math.cbrt(mass) * 2;
            st.setVisible(false);
            isstar1 = true;
        }
        canvas.grabFocus();
    }

    public static void addstarh() {
        st.setVisible(false);
        setenabled(true);
        canvas.grabFocus();
    }

    public static void addasteroidi(String s) {
        iserror = false;
        try {
            speed = Double.valueOf(s);
            radius = 5;
        } catch (NumberFormatException err) {
            iserror = true;
        }
        if (iserror) {
            errorwindow.setVisible(true);
        } else {
            speed = Double.valueOf(s);
            radius = 5;
            as.setVisible(false);
            isasteroid1 = true;
        }
        canvas.grabFocus();
    }

    public static void addasteroidh() {
        as.setVisible(false);
        setenabled(true);
        canvas.grabFocus();
    }

    public static void zoom(int n) {
        if (n < 0) {
            for (int i = 0; i < (-n); i++) {
                scale = scale + scale / 10;
            }
        } else {
            for (int i = 0; i < n; i++) {
                scale = scale - scale / 10;
            }
        }

    }

    public static void arange() {
        for (int i = 0; i < planets.length; i++) {
            if (i != 0) {
                if (planets[i] != null && planets[i - 1] == null) {
                    planets[i - 1] = planets[i];
                    planets[i] = null;
                }
            }
        }
        for (int i = 0; i < stars.length; i++) {
            if (i != 0) {
                if (stars[i] != null && stars[i - 1] == null) {
                    stars[i - 1] = stars[i];
                    stars[i] = null;
                }
            }
        }
        for (int i = 0; i < asteroids.length; i++) {
            if (i != 0) {
                if (asteroids[i] != null && asteroids[i - 1] == null) {
                    asteroids[i - 1] = asteroids[i];
                    asteroids[i] = null;
                }
            }
        }
    }

    public static void changemass(double a) {
        for (int i = 0; i < planets.length; i++) {
            if (planets[i] != null) {
                if (planets[i].isselected) {
                    planets[i].mass = a;
                    planets[i].radius = Math.cbrt(a) * 2;
                }
            }
        }
        for (int i = 0; i < stars.length; i++) {
            if (stars[i] != null) {
                if (stars[i].isselected) {
                    stars[i].mass = a;
                    stars[i].radius = Math.cbrt(a) * 2;
                }
            }
        }
        canvas.repaint();
        canvas.grabFocus();
    }

    public static void changeX(double a) {
        for (int i = 0; i < planets.length; i++) {
            if (planets[i] != null) {
                if (planets[i].isselected) {
                    planets[i].pos.x = a;
                }
            }
        }
        for (int i = 0; i < asteroids.length; i++) {
            if (asteroids[i] != null) {
                if (asteroids[i].isselected) {
                    asteroids[i].pos.x = a;
                }
            }
        }
        for (int i = 0; i < stars.length; i++) {
            if (stars[i] != null) {
                if (stars[i].isselected) {
                    stars[i].pos.x = a;
                }
            }
        }
        canvas.repaint();
        canvas.grabFocus();
    }

    public static void changeY(double a) {
        for (int i = 0; i < planets.length; i++) {
            if (planets[i] != null) {
                if (planets[i].isselected) {
                    planets[i].pos.y = a;
                }
            }
        }
        for (int i = 0; i < asteroids.length; i++) {
            if (asteroids[i] != null) {
                if (asteroids[i].isselected) {
                    asteroids[i].pos.y = a;
                }
            }
        }
        for (int i = 0; i < stars.length; i++) {
            if (stars[i] != null) {
                if (stars[i].isselected) {
                    stars[i].pos.y = a;
                }
            }
        }
        canvas.repaint();
        canvas.grabFocus();
    }

    public static void changespeed(double a, double b) {
        for (int i = 0; i < planets.length; i++) {
            if (planets[i] != null) {
                if (planets[i].isselected) {
                    planets[i].xspeed = a;
                    planets[i].yspeed = b;
                }
            }
        }
        for (int i = 0; i < asteroids.length; i++) {
            if (asteroids[i] != null) {
                if (asteroids[i].isselected) {
                    asteroids[i].xspeed = a;
                    asteroids[i].yspeed = b;
                }
            }
        }
        canvas.repaint();
        canvas.grabFocus();
    }

    public static void changeColor(Color a) {
        for (int i = 0; i < planets.length; i++) {
            if (planets[i] != null) {
                if (planets[i].isselected) {
                    planets[i].color = a;
                }
            }
        }
        for (int i = 0; i < asteroids.length; i++) {
            if (asteroids[i] != null) {
                if (asteroids[i].isselected) {
                    asteroids[i].color = a;
                }
            }
        }
        for (int i = 0; i < stars.length; i++) {
            if (stars[i] != null) {
                if (stars[i].isselected) {
                    stars[i].color = a;
                }
            }
        }
        canvas.repaint();
        canvas.grabFocus();
    }

    public void objectSelect(double x, double y) {
        double min = 100000;
        int z = 0;
        int u = 0;
        for (int i = 0; i < planets.length; i++) {
            if (planets[i] != null) {
                if (Math.sqrt((Math.pow(Math.abs(x - ((planets[i].pos.x * scale) - (500 * (scale - 1)) + (planets[i].radius / 2 * scale))), 2)) + (Math.pow(Math.abs(y - ((planets[i].pos.y * scale) - (350 * (scale - 1)) + (planets[i].radius / 2 * scale))), 2))) < min) {
                    min = Math.sqrt((Math.pow(Math.abs(x - ((planets[i].pos.x * scale) - (500 * (scale - 1)) + (planets[i].radius / 2 * scale))), 2)) + (Math.pow(Math.abs(y - ((planets[i].pos.y * scale) - (350 * (scale - 1)) + (planets[i].radius / 2 * scale))), 2)));
                    z = 0;
                    u = i;
                }
            }
        }
        for (int i = 0; i < stars.length; i++) {
            if (stars[i] != null) {
                if (Math.sqrt((Math.pow(Math.abs(x - ((stars[i].pos.x * scale) - (500 * (scale - 1)) + (stars[i].radius / 2 * scale))), 2)) + (Math.pow(Math.abs(y - ((stars[i].pos.y * scale) - (350 * (scale - 1)) + (stars[i].radius / 2 * scale))), 2))) < min) {
                    min = Math.sqrt((Math.pow(Math.abs(x - ((stars[i].pos.x * scale) - (500 * (scale - 1)) + (stars[i].radius / 2 * scale))), 2)) + (Math.pow(Math.abs(y - ((stars[i].pos.y * scale) - (350 * (scale - 1)) + (stars[i].radius / 2 * scale))), 2)));
                    z = 1;
                    u = i;
                }
            }
        }
        for (int i = 0; i < asteroids.length; i++) {
            if (asteroids[i] != null) {
                if (Math.sqrt((Math.pow(Math.abs(x - ((asteroids[i].pos.x * scale) - (500 * (scale - 1)) + (asteroids[i].radius / 2 * scale))), 2)) + (Math.pow(Math.abs(y - ((asteroids[i].pos.y * scale) - (350 * (scale - 1)) + (asteroids[i].radius / 2 * scale))), 2))) < min) {
                    min = Math.sqrt((Math.pow(Math.abs(x - ((asteroids[i].pos.x * scale) - (500 * (scale - 1)) + (asteroids[i].radius / 2 * scale))), 2)) + (Math.pow(Math.abs(y - ((asteroids[i].pos.y * scale) - (350 * (scale - 1)) + (asteroids[i].radius / 2 * scale))), 2)));
                    z = 2;
                    u = i;
                }
            }
        }
        boolean check = true;
        switch (z) {
            case 0: {
                if (planets[u] != null) {
                    if (planets[u].isselected) {
                        check = false;
                    }
                    break;
                }
            }
            case 1: {
                if (stars[u] != null) {
                    if (stars[u].isselected) {
                        check = false;
                    }
                    break;
                }
            }
            default: {
                if (asteroids[u] != null) {
                    if (asteroids[u].isselected) {
                        check = false;
                    }
                    break;
                }
            }
        }
        if (check) {
            if (min <= 30) {
                if (r != null) {
                    r.dispose();
                }
                timer.stop();
                isplay = false;
                play.setText("►");
                setenabled(false);
                for (int i = 0; i < planets.length; i++) {
                    if (planets[i] != null) {
                        planets[i].isselected = false;
                    }
                }
                for (int i = 0; i < stars.length; i++) {
                    if (stars[i] != null) {
                        stars[i].isselected = false;
                    }
                }
                for (int i = 0; i < asteroids.length; i++) {
                    if (asteroids[i] != null) {
                        asteroids[i].isselected = false;
                    }
                }
                switch (z) {
                    case 0: {
                        r = new BodyInfo(planets[u]);
                        planets[u].isselected = true;
                        break;
                    }
                    case 1: {
                        r = new BodyInfo(stars[u]);
                        stars[u].isselected = true;
                        break;
                    }
                    default: {
                        r = new BodyInfo(asteroids[u]);
                        asteroids[u].isselected = true;
                        break;
                    }
                }
                r.setVisible(true);
            }
        }
    }

    public void editDone() {
        for (int i = 0; i < planets.length; i++) {
            if (planets[i] != null) {
                planets[i].isselected = false;
            }
        }
        for (int i = 0; i < stars.length; i++) {
            if (stars[i] != null) {
                stars[i].isselected = false;
            }
        }
        for (int i = 0; i < asteroids.length; i++) {
            if (asteroids[i] != null) {
                asteroids[i].isselected = false;
            }
        }
        r.dispose();
        timer.start();
        isplay = true;
        play.setText("▋▋");
        setenabled(true);
    }

    public void delete() {
        for (int i = 0; i < planets.length; i++) {
            if (planets[i] != null) {
                if (planets[i].isselected) {
                    planets[i] = null;
                    arange();
                }
            }
        }
        for (int i = 0; i < asteroids.length; i++) {
            if (asteroids[i] != null) {
                if (asteroids[i].isselected) {
                    asteroids[i] = null;
                    arange();
                }
            }
        }
        for (int i = 0; i < stars.length; i++) {
            if (stars[i] != null) {
                if (stars[i].isselected) {
                    stars[i] = null;
                    arange();
                }
            }
        }
        r.dispose();
        timer.start();
        isplay = true;
        play.setText("▋▋");
        setenabled(true);
        canvas.repaint();
        canvas.grabFocus();
    }

    public static void setenabled(boolean a) {
        planet.setEnabled(a);
        star.setEnabled(a);
        asteroid.setEnabled(a);
        play.setEnabled(a);
        delete.setEnabled(a);
        d1.setEnabled(a);
        party.setEnabled(a);
    }
}
