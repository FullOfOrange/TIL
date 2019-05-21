interface I{
    void f();
}
class A{
    public void f(){ System.out.print("class"); }
}

public class test_interface extends A implements I{
    public static void main(String[] args){
        test_interface ti = new test_interface();
        ti.f();
    }
}