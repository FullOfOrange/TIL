import java.util.Scanner;

class A16{
    public static void main(String[] args){
        Scanner scanner = new Scanner(System.in);

        String[] result = {"가위","바위","보"};

        String input = new String();

        while(true){
            System.out.printf("가위 바위 보!>>");
            input = scanner.next();
            if(input.equals("그만")) {
                System.out.println("게임을 종료합니다..");
                break;
            }
            int random = (int)(Math.random()*3);

            if(input.equals(result[random])) {
                System.out.print("사용자 = " + input + " , " + "컴퓨터 = " + result[random] + ", ");
                System.out.println("비겼습니다.");
            }else{
                if(input.equals("가위")) {
                    System.out.print("사용자 = " + input + " , " + "컴퓨터 = " + result[random] + ", ");
                    System.out.println((random != 1) ? "사용자가 이겼습니다." : "컴퓨터가 이겼습니다.");
                }else if(input.equals("바위")) {
                    System.out.print("사용자 = "+input+" , "+"컴퓨터 = "+result[random]+", ");
                    System.out.println((random != 2) ? "사용자가 이겼습니다." : "컴퓨터가 이겼습니다.");
                }else if(input.equals("보")) {
                    System.out.print("사용자 = "+input+" , "+"컴퓨터 = "+result[random]+", ");
                    System.out.println((random != 0) ? "사용자가 이겼습니다." : "컴퓨터가 이겼습니다.");
                }else
                    System.out.println("잘못된 입력입니다.");
            }
        }
        scanner.close();
    }
}