class final_test{
    enum day {안녕,반가,나는,바보,메롱,멍청이}

    private int a;
    public final_test(int a){
        this.a = a;
    }
    public int getA(){
        return a;
    }
    public static void main(String[] args){
        String c = "one";
        System.out.println("c"+c.hashCode());
        String d = "one";
        System.out.println("d"+d.hashCode());
        d = d+"two";
        System.out.println("d"+d.hashCode());
        System.out.println(c==d);
    }
}