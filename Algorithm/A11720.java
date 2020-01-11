import java.util.*;

public class A11720 {
    public static void main(String[] args){

        Scanner sc = new Scanner(System.in);

        int n = sc.nextInt();
        String a = sc.next();

        long result = 0;

        for(int i = 0; i < n; i++)
            result += (a.codePointAt(i)-48);

        System.out.println(result);

        sc.close();
    }
}