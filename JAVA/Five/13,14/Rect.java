public class Rect implements Shape{
    private int x,y;
    Rect(int x,int y){
        this.x=x;
        this.y=y;
    }
    @Override
    public void draw(){
        System.out.println(x+"x"+y+"크기의 사각형 입니다.");
    }
    @Override
    public double getArea(){ return x*y; }

    public static void main(String[] args){
        Shape [] list = new Shape[3];
        list[0] = new Circle(10);
        list[1] = new Oval(20,30);
        list[2] = new Rect(10,40);

        for(int i=0; i<list.length; i++) list[i].redraw();
        for(int i=0; i<list.length; i++) System.out.println("면적은 "+ list[i].getArea());
    }
}