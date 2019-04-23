import java.util.Scanner;
//문제 6번
class Circle {
    private double x,y;
    private int radius;

    public Circle(double x,double y,int radius){
        this.x=x;
        this.y=y;
        this.radius=radius;
    }

    public void show(){ System.out.println("("+x+","+y+")"+radius);}

    public static Circle compareCircle(Circle a[]) {
        int max=0;
        for(int i=0; i<3; i++)
            if(a[i].radius>a[max].radius) max=i;
        return a[max];
    }
}
public class CircleManager {
    public static void main(String[] args){
        Scanner scanner = new Scanner(System.in);

        Circle c[] = new Circle[3];
        for(int i = 0; i<3; i++){
            System.out.print("x,y,radius >>");
            double x = scanner.nextDouble();
            double y = scanner.nextDouble();
            int radius = scanner.nextInt();
            c[i] = new Circle(x,y,radius);
        }
        System.out.print("가장 면적이 큰 원은");
        Circle.compareCircle(c).show();
        scanner.close();
    }
}