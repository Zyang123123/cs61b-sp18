//@Deprecated
public class NBody{
    public static double readRadius(String a){
        In in = new In(a);
		int N = in.readInt();
		double R = in.readDouble();     
        return R;   
    }
    public static Planet[] readPlanets(String a){
        In in = new In(a);
		int N = in.readInt();
		double R = in.readDouble(); 
        int i=0;
        Planet[] planets = new Planet[N];
        for (i=0;i<N;i=i+1){
            double xxPos=in.readDouble();
            double yyPos=in.readDouble();
            double xxVel=in.readDouble();
            double yyVel=in.readDouble();
            double mass=in.readDouble();
            String imgFileName=in.readString();
            planets[i]=new Planet(xxPos, yyPos, xxVel, yyVel, mass, imgFileName);
        }
        return planets;
    }
    public static void main(String[] args) {
        double T = Double.parseDouble(args[0]);
        double dt = Double.parseDouble(args[1]);
        String filename=args[2];
        double R = readRadius(filename);
        Planet[] planets = readPlanets(filename);
		/** Sets up the universe so it matches the radius of the universe 
		  *  */
		StdDraw.setScale(-R, R);
        StdDraw.picture(0, 0, "images/starfield.jpg");
        		/* Shows the drawing to the screen, and waits 2000 milliseconds. */
		//StdDraw.show();
		//StdDraw.pause(2000);	
        for (Planet p : planets){
            p.draw();
        }

        StdDraw.enableDoubleBuffering();
        double t=0;
        for (t=0;t<=T;t=t+dt){
            double[] xForces=new double[planets.length];
            double[] yForces=new double[planets.length];
            int i=0;
            for (i=0;i<planets.length;i=i+1){
                xForces[i]=planets[i].calcNetForceExertedByX(planets);
                yForces[i]=planets[i].calcNetForceExertedByY(planets);
            }
            for (i=0;i<planets.length;i=i+1){
                planets[i].update(dt, xForces[i], yForces[i]);
            }
            StdDraw.picture(0, 0, "images/starfield.jpg");
            for (Planet p : planets){
                p.draw();
            }  
            StdDraw.show();
			StdDraw.pause(10);         
        }
        StdOut.printf("%d\n", planets.length);
        StdOut.printf("%.2e\n", R);
        for (int i = 0; i < planets.length; i++) {
            StdOut.printf("%11.4e %11.4e %11.4e %11.4e %11.4e %12s\n",
            planets[i].xxPos, planets[i].yyPos, planets[i].xxVel,
            planets[i].yyVel, planets[i].mass, planets[i].imgFileName);   
        }
    }
}