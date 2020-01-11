import java.util.*;

class Data {
    public int num;
    public String data;

    public Data (int num, String data){
        this.num = num;
        this.data = data;
    }
}

public class A2675{
    public static void main(String[] args){

        Scanner sc = new Scanner(System.in);
        int num_case = sc.nextInt();

        Data[] datas = new Data[num_case];

        for(int i = 0; i < num_case; i++){
            datas[i] = new Data(sc.nextInt(), sc.next());
        }

        for(int i = 0; i < num_case; i++ ) {
            for(int x = 0; x < datas[i].data.length(); x++){
                for(int y = 0; y < datas[i].num; y++){
                    System.out.print(datas[i].data.charAt(x));
                }
            }
            System.out.println();
        }
    }
}