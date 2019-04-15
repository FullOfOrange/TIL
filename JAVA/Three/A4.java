import java.util.Scanner;
class A4{
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String s = scanner.next();
        char c = s.charAt(0);
        char pivot='a';
        while(c!=pivot-1){
            for(int i=0; i <= c-pivot; i++)
                System.out.printf("%c",pivot+i);
            System.out.println();
            --c;
        }
        scanner.close();
    }
}