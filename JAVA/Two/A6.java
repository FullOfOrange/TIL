import java.util.Scanner;

class A6{
    public static void main(String[] args){
        Scanner scanner = new Scanner(System.in);

        System.out.print("정수 입력(1~99)>>");
        int a = scanner.nextInt();
        if(a>=1 && a<=99) {
            boolean x = isThree(a / 10), y = isThree(a % 10);

            if (x && y) {
                System.out.println("박수짝짝");
            } else if (x ^ y) {
                System.out.println("박수짝");
            } else {
                System.out.println("없음");
            }
        }else{
            System.out.println("수의 범위가 올바르지 않음");
        }

        scanner.close();
    }
    public static boolean isThree(int a){
        if(a==3||a==6||a==9)
            return true;
        else
            return false;
    }
}
//A6.java:18: error: empty character literal
//            System.out.printf("박수%s%s\n",(a)?"짝":'',(b)?"짝":'');
//A6.java:18: error: not a statement
//       System.out.printf("박수%s%s\n",(a)?"짝":'',(b)?"짝":'');
//
//^[[AA6.java:10: error: non-static method isThree(int) cannot be referenced from a static context
//        boolean x=isThree(a/10), y=isThree(a%10);
