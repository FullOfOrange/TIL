public class IPTV extends ColorTV{
    private String address;

    public IPTV(String address,int size, int color){
        super(size,color);
        this.address = address;
    }
    public String getAddress() { return address; }
    @Override
    protected void printProperty(){
        System.out.print("나의 IPTV는 "+getAddress()+" 주소의 "+getSize()+"인치 "+getColor()+"컬러");
    }
    public static void main(String[] args){
        IPTV iptv = new IPTV("192.1.1.2",32,2048);
        iptv.printProperty();
    }
}