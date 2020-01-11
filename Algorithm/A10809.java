import java.util.*;

public class A10809{
    public static void main(String[] args){

        Map<Integer, Integer> map = new HashMap<>();
        Scanner sc = new Scanner(System.in);

        String data = sc.next();

        for(int i = 97; i < 123; i++)
            map.put(i,-1);

        for(int i = 0; i < data.length(); i++)
            if(map.get(data.codePointAt(i)) == -1)
                map.put(data.codePointAt(i),i);

        for(int i = 97; i < 123; i++)
            System.out.print(map.get(i) + " ");

        System.out.println();
        sc.close();
    }
}