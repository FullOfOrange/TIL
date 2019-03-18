class Man{

}

class Power extends Man {
    private int kick;
    private int punch;

    public Power(int a, int b){
        this.kick = a;
        this.punch = b;
    }

    public void setKick(int a){
        this.kick = a;
    }

    public void setPunch(int a){
        this.punch = a;
    }
    public int getKick(){
        return this.kick;
    }
    public int getPunch(){
        return this.punch;
    }
}

class FourEight{
    public static void main(String args[]){
        Power p = new Power(5,10);
        Power q = new Power(6,11);
        System.out.println(p.getKick());
        System.out.println(p.getPunch());

        p.setKick(30);
        p.setPunch(20);

        System.out.println(p.getKick());
        System.out.println(p.getPunch());

        System.out.println(q.getKick());
        System.out.println(q.getPunch());
    }
}