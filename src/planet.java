
package gravity;

import java.awt.Color;

public class planet extends Body{
    public double xspeed;
    public double yspeed;
    public double mass;
    Color color = Color.blue;
    double [] xtrail = new double[10000];
    double [] ytrail = new double[10000];
    public planet(double m,double s,double r,double a,position j){
        super(r,j);
        for(int i =0;i<10000;i++){
            xtrail[i]=0;
            ytrail[i]=0;
        }
        xspeed = s;
        yspeed = a;
        mass=m;
    }
    public void addpos(position d){
        int i;
        for(i=0;i<9999;i++){
            if(xtrail[i]==0&&ytrail[i]==0){
                break;
            }
        }
        if (i==0){
            for (int j=0;j<10000;j++){
                xtrail[j]=d.x;
                ytrail[j]=d.y;
            }
        }
        else{
            for(i=0;i<9999;i++){
                xtrail[i]=xtrail[i+1];
                ytrail[i]=ytrail[i+1];
            }
            xtrail[9999]=d.x;
            ytrail[9999]=d.y;
        }
    }
}
