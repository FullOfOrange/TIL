import java.util.Scanner;

class A2{
    public static void main(String[] args){
        Scanner scanner = new Scanner(System.in);

        System.out.print("2자리수 정수 입력(10~99)>>");
        int a = scanner.nextInt();
        if(a>=10 && a<=99) {
            if (a / 10 == a % 10)
                System.out.println("Yes! 10의 자리와 1의 자리가 같습니다.");
            else
                System.out.println("No! 10의 자리와 1의 자리가 같지 않습니다.");
        }else{
            System.out.println("범위가 올바르지 않습니다.");
        }
        scanner.close();
    }
}
// java A2
//2자리수 정수 입력(10~99)>>88
//Yes! 10의 자리와 1의 자리가 같습니다.
// fulloforange@foorgMac  ~/Current_Codes/TIL/JAVA/Two   master ●✚
// java A2
//2자리수 정수 입력(10~99)>>09
//No! 10의 자리와 1의 자리가 같지 않습니다.