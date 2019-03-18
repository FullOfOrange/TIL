import java.util.Scanner;

class Circle{
    private double x,y;
    private int radius;
    public Circle(double x, double y, int radius){
        this.x = x;
        this.y = y;
        this.radius = radius;
    }
    public static void show(Circle circles[],int n){
        int big = 0;
        for(int i=0;i<n;i++){
            if(circles[i].radius>circles[big].radius)
                big = i;
        }
        System.out.printf("가장 면적이 큰 원은 %f,%f,%d 이다",circles[big].x,circles[big].y,circles[big].radius);
    }
}

class FourAsix{
    public static void main(String[] args){
        Scanner scanner = new Scanner(System.in);
        Circle c [] = new Circle[3];
        for(int i=0; i<3; i++){
            System.out.print("x,y,r >>");
            double x = scanner.nextDouble();
            double y = scanner.nextDouble();
            int r = scanner.nextInt();
            c[i] = new Circle(x,y,r);
        }
        Circle.show(c,3);
    }
}