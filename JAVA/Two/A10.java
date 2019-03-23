import java.util.Scanner;
import java.lang.Math;

class A10 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.print("첫번째 원의 중심과 반지름 입력>>");
        double[] c1 = new double[3];
        for(int i=0; i<3; i++){
            c1[i] = scanner.nextDouble();
        }

        System.out.print("두번째 원의 중심과 반지름 입력>>");
        double[] c2 = new double[3];
        for(int j=0; j<3; j++){
            c2[j] = scanner.nextDouble();
        }

        if(isCircleDuplicate(c1[0],c1[1],c1[2],c2[0],c2[1],c2[2]))
            System.out.println("겹칩니다.");
        else System.out.println("안겹칩니다.");

        scanner.close();
    }
    public static boolean isCircleDuplicate(double x1, double y1, double r1, double x2, double y2, double r2){
        if(Math.sqrt(((x1-x2)*(x1-x2))+((y1-y2)*(y1-y2)))<(r1+r2))
            return true;
        else return false;
    }
}

//A10.java:27: error: incompatible types: boolean cannot be converted to double
//        if(Math.sqrt(((x1-y1)*(x1-y1))+(x2-y2)*(x2-y2)<r1+r2))
//                                                      ^
//Note: Some messages have been simplified; recompile with -Xdiags:verbose to get full output
//2 errors