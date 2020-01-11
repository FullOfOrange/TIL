import java.util.*;

public class AL1157{
    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);

        int[] array = new int[26];

        String data = sc.next();

        for(int i = 0; i < data.length(); i++){
            int temp = data.codePointAt(i);
            if(temp >= 97) temp -= 32;
            ++array[temp-65];
        }
        int top = 0;
        int charactor = 65;
        boolean dup = false;
        for(int i = 0; i < 26; i++){
            if(array[i] == top){
                dup = true;
            }
            if(array[i] > top){
                top = array[i];
                charactor = i;
                dup = false;
            }

        }
        if(dup == true) System.out.println("?");
        else System.out.printf("%c\n", charactor+65);
        sc.close();
    }
}