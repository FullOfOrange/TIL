package Eight;

import java.util.Scanner;

public class CycleChar{
    static void Print(String s){
        int i,j;
        for(i = 0; i <= s.length(); i++) {
            j = i%s.length();
            do{
                System.out.print(s.charAt(j++));
                j%=s.length();
            }while(j != i%s.length());
            System.out.println();
        }
    }
    public static void main(String[] args){
        Scanner scanner = new Scanner(System.in);
        System.out.println("문자열을 입력하세요. 빈칸이 있어도 되고 영어 한글 모두 됩니다.");
        Print(scanner.nextLine());
    }
}