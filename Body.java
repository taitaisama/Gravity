package gravity;

public class Body {
    public double radius;
    public position pos;
    boolean isselected;
    public Body(double r,position j){
        radius=Math.abs(r);
        pos=j;
        isselected=false;
    }
}