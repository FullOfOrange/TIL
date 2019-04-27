import java.util.Scanner;

class A{
    private int a;
    public A(int a){
        this.a = a;
    }
    private int show(){
        return a;
    }
}
class B extends A{
    private int b;
    public B(int a){
        super(a);
        b = 4;
    }
    public int show(){
        return b;
    }
}
public class test{
    public static void main(String[] args) {
        A a = new B(3);
        System.out.println();
        B b = (B)a;
        B c;
        System.out.println(b.show());
    }
}