public class Point3D extends Point{
    private int z;
    Point3D(int x,int y, int z){
        super(x,y);
        this.z = z;
    }
    @Override
    protected void move(int x, int y){
        super.move(x,y);
    }
    protected void move(int x, int y,int z){
        super.move(x,y);// super method는 이런식으로 하는겅ㅁ.
        this.z = z;
    }
    void moveUp(){
        ++this.z;
    }
    void moveDown(){
        --this.z;
    }
    int getZ(){
        return z;
    }
    public String toString(){
        return "("+getX()+","+getY()+","+getZ()+")";
    }
    public static void main(String[] args){
        Point3D p = new Point3D(1,2,3);
        System.out.println(p.toString()+"입니다.");

        p.moveUp();
        System.out.println(p.toString()+"입니다.");

        p.moveDown();
        p.move(10,10);
        System.out.println(p.toString()+"입니다.");

        p.move(100,200,300);
        System.out.println(p.toString()+"입니다.");
    }
}