import java.util.Scanner;

class Grade{
    private int math,science,english;

    Grade(int math,int science,int english){
        this.math = math;
        this.science = science;
        this.english = english;
    };

    int average() { return (math+science+english)/3; }

}

class FourAtwo{
    public static void main(String [] args){
        Scanner scanner = new Scanner(System.in);

        System.out.print("입력 > ");
        int math = scanner.nextInt();
        int science = scanner.nextInt();
        int english = scanner.nextInt();
        Grade me = new Grade(math,science,english);
        System.out.println(me.average());

        scanner.close();
    }
}