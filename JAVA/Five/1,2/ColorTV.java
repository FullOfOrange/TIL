//1번 문제
public class ColorTV extends TV{
    private int color;
    public ColorTV(int size, int color) {
        super(size);
        this.color = color;
    }
    protected int getColor() { return color; }
    protected void printProperty(){ System.out.print(getSize()+"인치 "+getColor()+"컬러"); }
    public static void main(String[] args){
        ColorTV myTV = new ColorTV(32,1024);
        myTV.printProperty();
    }
}