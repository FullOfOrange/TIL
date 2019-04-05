import java.util.Scanner;

class A6{
    public static void main(String[] args){
        int [] unit = {50000,10000,1000,500,100,50,10,1};
        Scanner scanner = new Scanner(System.in);
        int money = scanner.nextInt();
        int a;
        for(int i=0; i<unit.length; i++){
            if((a=money/unit[i])!=0) {
                System.out.printf("%d원 짜리 : %d개\n", unit[i], a);
                money %= unit[i];
            }
        }
        scanner.close();
    }
}