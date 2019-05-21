public class Oval implements Shape{
    private int x,y;
    Oval(int x,int y){
        this.x=x;
        this.y=y;
    }
    @Override
    public void draw(){
        System.out.println(x+"x"+y+"에 내접하는 타원입니다.");
    }
    @Override
    public double getArea(){ return x*y*PI; }

    public static void main(String[] args){
        Shape donut = new Oval(20, 30);
        donut.redraw();
        System.out.println("면적은 "+ donut.getArea());
    }
}