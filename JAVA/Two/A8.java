import java.util.Scanner;

class A8{
    private static final int X_RECT = 100, Y_RECT = 200;
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int x1,x2,y1,y2;
        System.out.print("두 점을 x1,y1,x2,y2 순으로 입력하세요>>");
        x1 = scanner.nextInt();
        y1 = scanner.nextInt();
        x2 = scanner.nextInt();
        y2 = scanner.nextInt();

        if(inRect(x1,y1,X_RECT,X_RECT,Y_RECT,Y_RECT) || inRect(x2,y2,X_RECT,X_RECT,Y_RECT,Y_RECT))
            System.out.println("충돌함");
        else
            System.out.println("충돌 안함");

        scanner.close();
    }
    public static boolean inRect(int x, int y, int rectx1, int recty1, int rectx2, int recty2){
        if((x>=rectx1 && x<=rectx2) && (y>=recty1 && y <= recty2))
            return true;
        else return false;
    }
}