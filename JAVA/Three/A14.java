import java.util.Scanner;

class A14{
    public static void main(String[] args){
        Scanner scanner = new Scanner(System.in);

        String course[] = {"Java","C++","HTML5","컴퓨터구조","안드로이드"};
        int score[] = {95, 88, 12, 42, 13};

        String input = new String();

        while(true){
            System.out.printf("과목 이름>>");
            input = scanner.next();
            if(input.equals("그만"))
                break;
            int i=0;
            while(i!=5){
                if(input.equals(course[i]))
                    break;
                ++i;
            }
            if(i==5)
                System.out.println("없는 과목입니다.");
            else
                System.out.println(input + "의 점수는 " + score[i]);
        }
        scanner.close();
    }
}