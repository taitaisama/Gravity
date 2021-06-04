
package gravity;

import java.awt.Color;

public class star extends Body{
    public double mass;
    Color color = Color.yellow;
    public star(double m,double r,position j){
        super(r,j);
        mass=m;
    }
}
