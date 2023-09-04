public class Planet{
    public double xxPos, yyPos, xxVel, yyVel, mass;
    public double G=6.67*Math.pow(10,-11);
    public String imgFileName;

    public Planet(double xP, double yP, double xV, double yV,double m, String img){
        xxPos = xP;
        yyPos = yP;
        xxVel = xV;
        yyVel = yV;
        mass = m;
        imgFileName = img;
    }
    public Planet(Planet p){
        xxPos = p.xxPos;
        yyPos = p.yyPos;
        xxVel = p.xxVel;
        yyVel = p.yyVel;
        mass = p.mass;
        imgFileName = p.imgFileName;
    }
    public double calcDistance(Planet p){
        return Math.sqrt((p.xxPos-this.xxPos)*(p.xxPos-this.xxPos) + (p.yyPos-this.yyPos)*(p.yyPos-this.yyPos));
    }
    public double calcForceExertedBy(Planet p){
        return G*p.mass*this.mass/this.calcDistance(p)/this.calcDistance(p);
    }
    public double calcForceExertedByX(Planet p){
        return calcForceExertedBy(p)*(p.xxPos-xxPos)/calcDistance(p);
    }
    public double calcForceExertedByY(Planet p){
        return calcForceExertedBy(p)*(p.yyPos-yyPos)/calcDistance(p);
    }
    public double calcNetForceExertedByX(Planet[] allPlanets){
        int i;
        double netx=0;
        for (i=0;i<allPlanets.length;i++){
            if (this.equals(allPlanets[i])){
                continue;
            }
            netx=netx+calcForceExertedByX(allPlanets[i]);
        }
        return netx;
    }
    public double calcNetForceExertedByY(Planet[] allPlanets){
        int i;
        double nety=0;
        for (i=0;i<allPlanets.length;i++){
            if (this.equals(allPlanets[i])){
                continue;
            }
            nety=nety+calcForceExertedByY(allPlanets[i]);
        }
        return nety;
    }
    public void update(double dt, double fX, double fY){
        double ax, ay;
        ax=fX/mass;
        ay=fY/mass;
        xxVel=xxVel+dt*ax;
        yyVel=yyVel+dt*ay;
        xxPos=xxPos+dt*xxVel;
        yyPos=yyPos+dt*yyVel;
    }   
    public void draw(){
        StdDraw.picture(xxPos, yyPos, "images/" + imgFileName);
    }
}