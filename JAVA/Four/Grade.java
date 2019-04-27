import java.util.Scanner;
//문제 2번
public class Grade{
    private int math,science,english;

    Grade(int math, int science, int english){
        this.math = math;
        this.science = science;
        this.english = english;
    }

    float average(){ return (math+science+english)/3; }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.print("수학,과학,영어 순으로 3개의 점수 입력>>");
        int math = scanner.nextInt();
        int science = scanner.nextInt();
        int english = scanner.nextInt();

        Grade me = new Grade(math,science,english);
        System.out.println("세 과목의 평균은 "+me.average()+" 입니다.");

        scanner.close();
    }
}