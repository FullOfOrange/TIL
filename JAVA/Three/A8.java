import java.util.Scanner;

class A8{
    public static void main(String[] args){
        Scanner scanner = new Scanner(System.in);
        System.out.printf("정수 몇개?");
        int num_array = scanner.nextInt();
        int array[] = new int[num_array];
        int rand;
        for(int i=0; i<array.length; i++){
            rand = (int)(Math.random()*100)+1;
            for(int j=0; j<i; j++) {
                if (array[j] == rand) {
                    j = -1;
                    rand = (int) (Math.random() * 100) + 1;
                }
            }
            System.out.printf("%d ",array[i]=rand);
            if(i%10==9)
                System.out.println();
            if(i==array.length-1)
                System.out.println();
        }
        /*
        Arrays.sort(array);
        for(int x = 0; x<array.length; x++) {
            System.out.printf("%d ", array[x]);
            if (x % 10 == 9)
                System.out.println();
        }
        */
        scanner.close();
    }
}