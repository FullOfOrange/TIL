
class Rectangle{
    private int x,y,width,height;

    Rectangle(int x,int y,int width,int height){
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
    }

    int square(){
        return width*height;
    }

    void show(){
        System.out.printf("%d %d %d %d",x,y,width,height);
    }

    boolean contains(Rectangle r){
        if(x<=r.getX()&&(x+height)>=r.getX()+r.getHeight()){
            if(x<=r.getY()&&(y+width)>=r.getY()+r.getWidth())
                return true;
            else return false;
        }else return false;
    }

    int getX(){ return x; }
    int getY(){ return y; }
    int getWidth(){ return width; }
    int getHeight(){ return height; }
}

class FourAfour{
    public static void main(String args[]){
        Rectangle r = new Rectangle(2,2,8,7);
        Rectangle s = new Rectangle(5,5,6,6);
        Rectangle t = new Rectangle(1,1,10,10);

        r.show();
        System.out.println(s.square());
        if(t.contains(r)) System.out.println("true");
    }
}