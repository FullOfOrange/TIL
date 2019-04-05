import java.util.Scanner;
import java.lang.String;

class A12 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        double a,b;
        char o;

        System.out.print("연산>>");
        a = scanner.nextDouble();
        o = scanner.next().charAt(0);
        b = scanner.nextDouble();

        if(o=='/'&&b==0)
            System.out.println("0으로 나눌 수 없습니다.");
        else if(o!='+'&&o!='-'&&o!='*'&&o!='/')
            System.out.println("연산자가 올바르지 않습니다.");
        else System.out.printf("%f%c%f의 결과는 %f\n",a,o,b,jav ,o));

        scanner.close();
    }
    public static double ifOperate(double a, double b, char o){
        if(o=='+')
            return a+b;
        else if(o=='-')
            return a-b;
        else if(o=='*')
            return a*b;
        else if(o=='/')
            return a/b;
        else
            return 0;
    }
    public static double switchOperate(double a, double b, char o){
        switch(o){
            case '+':
                return a+b;
            case '-':
                return a-b;
            case '*':
                return a*b;
            case '/':
                return a/b;
            default:
                return 0;
        }
    }
}