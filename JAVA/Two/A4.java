import java.util.Scanner;

class A4{
    public static void main(String[] args){
        Scanner scanner = new Scanner(System.in);

        int[] a = new int[3];

        System.out.print("정수 세개 입력>>");

        for(int i = 0; i < 3; i++) {
            a[i] = scanner.nextInt();
        }
        int f=0,s=1;
        if(a[0]>a[1])
            f=1;
        if(a[1]>a[2])
            s=2;
        if(f==s){
            System.out.printf("중간 값은 %d 입니다.", (a[0] < a[2]) ? a[0] : a[2]);
        }else {
            System.out.printf("중간 값은 %d 입니다.", (a[f] > a[s]) ? a[f] : a[s]);
        }
        scanner.close();
    }
}
//A4.java:7: error: array dimension missing -> 어레이의 크기를 안정함.
//        int[] a = new int[];
//
//                            java A4
//정수 세개 입력>>100 30 20
//세 정수의 합은 30 입니다.%                                                                                                                                                                                                                                                fulloforange@foorgMac  ~/Current_Codes/TIL/JAVA/Two   master ●✚
// java A4
//정수 세개 입력>>100 20 30
//세 정수의 합은 30 입니다.%                                                                                                                                                                                                                                                fulloforange@foorgMac  ~/Current_Codes/TIL/JAVA/Two   master ●✚
// java A4
//정수 세개 입력>>20 100 30
//세 정수의 합은 30 입니다.%                                                                                                                                                                                                                                                fulloforange@foorgMac  ~/Current_Codes/TIL/JAVA/Two   master ●✚
// java A4
//정수 세개 입력>>20 30 100
//세 정수의 합은 30 입니다.%                                                                                                                                                                                                                                                fulloforange@foorgMac  ~/Current_Codes/TIL/JAVA/Two   master ●✚
// java A4
//정수 세개 입력>>30 20 100
//세 정수의 합은 30 입니다.%                                                                                                                                                                                                                                                fulloforange@foorgMac  ~/Current_Codes/TIL/JAVA/Two   master ●✚
// java A4
//정수 세개 입력>>30 100 20
//세 정수의 합은 30 입니다.%