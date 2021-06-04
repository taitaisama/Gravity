package gravity;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Toolkit;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.event.MouseWheelEvent;
import java.awt.event.MouseWheelListener;
import javax.swing.JPanel;

public class Canvas extends JPanel implements MouseListener, MouseWheelListener, MouseMotionListener, KeyListener {

    boolean line = false;
    int r;
    int x;
    int y;
    int XX;
    int YY;
    int counter = 0;
    Color color;
    double angle;
    boolean egbody = false;

    public Canvas() {
        setFocusable(true);
        grabFocus();
        addMouseListener(this);
        addMouseWheelListener(this);
        addMouseMotionListener(this);
        addKeyListener(this);
        setPreferredSize(new Dimension(1000, 700));
        this.setBackground(Color.BLACK);
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        drawThings(g);
    }

    public void drawegbody(int R, int X, int Y) {
        r = R;
        x = X;
        y = Y;
        egbody = true;
        repaint();
    }

    public void drawline(int X, int Y, int X1, int Y1) {
        egbody = true;
        line = true;
        XX = X1;
        YY = Y1;
        repaint();
    }

    public void drawThings(Graphics g) {
        if (egbody) {
            g.setColor(color);
            g.fillOval(x, y, r, r);
        }
        if (line) {
            g.setColor(Color.white);
            g.drawLine(x + r / 2, y + r / 2, XX, YY);
            g.drawString(Integer.toString((int) Math.round(angle)), (int) x, (int) y);
        }
        for (int i = 0; i < Board.num(Board.planets); i++) {
            if (Board.ispartytime) {
                g.setColor(new Color((int) (Math.random() * 255), (int) (Math.random() * 255), (int) (Math.random() * 255)));
            } else {
                g.setColor(Board.planets[i].color);
            }
            g.fillOval((int) (((Board.planets[i].pos.x - Board.planets[i].radius) * Board.scale) - (500 * (Board.scale - 1))), (int) (((Board.planets[i].pos.y - Board.planets[i].radius) * Board.scale) - (350 * (Board.scale - 1))), (int) (Board.planets[i].radius * Board.scale * 2), (int) (Board.planets[i].radius * Board.scale * 2));
            g.drawPolyline(doublearraytointarray(Board.planets[i].xtrail, 500/*,Board.planets[i].radius*/), doublearraytointarray(Board.planets[i].ytrail, 350/*,Board.planets[i].radius*/), 9999);
        }
        for (int i = 0; i < Board.num(Board.asteroids); i++) {
            if (Board.ispartytime) {
                g.setColor(new Color((int) (Math.random() * 255), (int) (Math.random() * 255), (int) (Math.random() * 255)));
            } else {
                g.setColor(Board.asteroids[i].color);
            }
            g.fillOval((int) (((Board.asteroids[i].pos.x - Board.asteroids[i].radius) * Board.scale) - (500 * (Board.scale - 1))), (int) (((Board.asteroids[i].pos.y - Board.asteroids[i].radius) * Board.scale) - (350 * (Board.scale - 1))), (int) (Board.asteroids[i].radius * Board.scale * 2), (int) (Board.asteroids[i].radius * Board.scale * 2));
            g.drawPolyline(doublearraytointarray(Board.asteroids[i].xtrail, 500/*,Board.asteroids[i].radius*/), doublearraytointarray(Board.asteroids[i].ytrail, 350/*,Board.asteroids[i].radius*/), 9999);
        }
        for (int i = 0; i < Board.num(Board.stars); i++) {
            if (Board.ispartytime) {
                g.setColor(new Color((int) (Math.random() * 255), (int) (Math.random() * 255), (int) (Math.random() * 255)));
            } else {
                g.setColor(Board.stars[i].color);
            }
            g.fillOval((int) (((Board.stars[i].pos.x - (Board.stars[i].radius) / 2) * Board.scale) - (500 * (Board.scale - 1))), (int) (((Board.stars[i].pos.y - (Board.stars[i].radius) / 2) * Board.scale) - (350 * (Board.scale - 1))), (int) (Board.stars[i].radius * Board.scale * 2), (int) (Board.stars[i].radius * Board.scale * 2));
        }
        g.setColor(Color.white);
        g.drawString("Zoom: ", 830, 675);
        g.drawString(Double.toString(Board.scale), 830, 690);
    }

    @Override
    public void mouseClicked(MouseEvent me) {
        if (Board.isplanet2 == true) {
            Board.isplanet2 = false;
            Board.createplanet(Math.round(angle), new position((x + (500 * (Board.scale - 1))) / Board.scale, (y + (350 * (Board.scale - 1))) / Board.scale));
            egbody = false;
            line = false;
            repaint();
        } else if (Board.isplanet1 == true) {
            x = me.getX();
            y = me.getY();
            Board.isplanet1 = false;
            Board.isplanet2 = true;
            color = Color.BLUE;
            drawegbody((int) (Board.radius * Board.scale * 2), (int) (x - Board.radius * Board.scale), (int) (y - Board.radius * Board.scale));
        } else if (Board.isstar1 == true) {
            x = me.getX();
            y = me.getY();
            Board.isstar1 = false;
            color = Color.YELLOW;
            r = (int) (Board.radius * Board.scale * 2);
            x = (int) (x - Board.radius * Board.scale);
            y = (int) (y - Board.radius * Board.scale);
            repaint();
            Board.createstar(new position((x + (500 * (Board.scale - 1))) / Board.scale, (y + (350 * (Board.scale - 1))) / Board.scale));
        } else if (Board.isasteroid1 == true) {
            x = me.getX();
            y = me.getY();
            Board.isasteroid1 = false;
            Board.isasteroid2 = true;
            color = Color.white;
            drawegbody((int) (Board.radius * Board.scale * 2), (int) (x - Board.radius * Board.scale), (int) (y - Board.radius * Board.scale));
        } else if (Board.isasteroid2 == true) {
            Board.isasteroid2 = false;
            Board.createasteroid(Math.round(angle), new position((x + (500 * (Board.scale - 1))) / Board.scale, (y + (350 * (Board.scale - 1))) / Board.scale));
            egbody = false;
            line = false;
            repaint();
        } else {
            Gravity.b.objectSelect(me.getX(), me.getY());
        }
    }

    @Override
    public void mousePressed(MouseEvent me) {
    }

    @Override
    public void mouseReleased(MouseEvent me) {
    }

    @Override
    public void mouseEntered(MouseEvent me) {
    }

    @Override
    public void mouseExited(MouseEvent me) {
    }

    @Override
    public void mouseWheelMoved(MouseWheelEvent mwe) {
        int lol = mwe.getWheelRotation();
        Board.zoom(lol);
        repaint();
    }

    @Override
    public void mouseDragged(MouseEvent me) {
    }

    @Override
    public void mouseMoved(MouseEvent me) {
        if (Board.isplanet2) {
            drawline((int) x, (int) y, (int) me.getX(), (int) me.getY());
            angle((int) x + r / 2, (int) y + r / 2, me.getX(), me.getY());
        } else if (Board.isasteroid2) {
            drawline((int) x, (int) y, (int) me.getX(), (int) me.getY());
            angle((int) x + r / 2, (int) y + r / 2, (int) me.getX(), (int) me.getY());
        }
    }

    public void angle(int x, int y, int X, int Y) {
        angle = Math.toDegrees(Math.atan2(Y - y, X - x));
        if (angle < 0) {
            angle += 360;
        }
        angle -= 360;
        angle = Math.abs(angle);
    }

    public int[] doublearraytointarray(double[] s, int o) {
        int[] u = new int[10000];
        for (int i = 0; i < 10000; i++) {
            u[i] = (int) (((s[i]) * Board.scale) - (o * (Board.scale - 1)));
        }
        return u;
    }

    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {
        int keyCode = e.getKeyCode();
        switch (keyCode) {
            case KeyEvent.VK_DOWN:
                for (int i = 0; i < Board.num(Board.planets); i++) {
                    Board.planets[i].pos.y -= 10 / Board.scale;
                    if (Board.planets[i].xtrail[0] != 0 && Board.planets[i].ytrail[0] != 0) {
                        for (int j = 0; j < 10000; j++) {
                            Board.planets[i].ytrail[j] -= 10 / Board.scale;
                        }
                    }
                }
                for (int i = 0; i < Board.num(Board.asteroids); i++) {
                    Board.asteroids[i].pos.y -= 10 / Board.scale;
                    if (Board.asteroids[i].xtrail[0] != 0 && Board.asteroids[i].ytrail[0] != 0) {
                        for (int j = 0; j < 10000; j++) {
                            Board.asteroids[i].ytrail[j] -= 10 / Board.scale;
                        }
                    }
                }
                for (int i = 0; i < Board.num(Board.stars); i++) {
                    Board.stars[i].pos.y -= 10 / Board.scale;
                }
                break;
            case KeyEvent.VK_UP:
                for (int i = 0; i < Board.num(Board.planets); i++) {
                    Board.planets[i].pos.y += 10 / Board.scale;
                    if (Board.planets[i].xtrail[0] != 0 && Board.planets[i].ytrail[0] != 0) {
                        for (int j = 0; j < 10000; j++) {
                            Board.planets[i].ytrail[j] += 10 / Board.scale;
                        }
                    }
                }
                for (int i = 0; i < Board.num(Board.asteroids); i++) {
                    Board.asteroids[i].pos.y += 10 / Board.scale;
                    if (Board.asteroids[i].xtrail[0] != 0 && Board.asteroids[i].ytrail[0] != 0) {
                        for (int j = 0; j < 10000; j++) {
                            Board.asteroids[i].ytrail[j] += 10 / Board.scale;
                        }
                    }
                }
                for (int i = 0; i < Board.num(Board.stars); i++) {
                    Board.stars[i].pos.y += 10 / Board.scale;
                }
                break;
            case KeyEvent.VK_RIGHT:
                for (int i = 0; i < Board.num(Board.planets); i++) {
                    Board.planets[i].pos.x -= 10 / Board.scale;
                    if (Board.planets[i].xtrail[0] != 0 && Board.planets[i].ytrail[0] != 0) {
                        for (int j = 0; j < 10000; j++) {
                            Board.planets[i].xtrail[j] -= 10 / Board.scale;
                        }
                    }
                }
                for (int i = 0; i < Board.num(Board.asteroids); i++) {
                    Board.asteroids[i].pos.x -= 10 / Board.scale;
                    if (Board.asteroids[i].xtrail[0] != 0 && Board.asteroids[i].ytrail[0] != 0) {
                        for (int j = 0; j < 10000; j++) {
                            Board.asteroids[i].xtrail[j] -= 10 / Board.scale;
                        }
                    }
                }
                for (int i = 0; i < Board.num(Board.stars); i++) {
                    Board.stars[i].pos.x -= 10 / Board.scale;
                }
                break;
            case KeyEvent.VK_LEFT:
                for (int i = 0; i < Board.num(Board.planets); i++) {
                    Board.planets[i].pos.x += 10 / Board.scale;
                    if (Board.planets[i].xtrail[0] != 0 && Board.planets[i].ytrail[0] != 0) {
                        for (int j = 0; j < 10000; j++) {
                            Board.planets[i].xtrail[j] += 10 / Board.scale;
                        }
                    }
                }
                for (int i = 0; i < Board.num(Board.asteroids); i++) {
                    Board.asteroids[i].pos.x += 10 / Board.scale;
                    if (Board.asteroids[i].xtrail[0] != 0 && Board.asteroids[i].ytrail[0] != 0) {
                        for (int j = 0; j < 10000; j++) {
                            Board.asteroids[i].xtrail[j] += 10 / Board.scale;
                        }
                    }
                }
                for (int i = 0; i < Board.num(Board.stars); i++) {
                    Board.stars[i].pos.x += 10 / Board.scale;
                }
                break;
        }
        repaint();
    }

    @Override
    public void keyReleased(KeyEvent e) {

    }
}
