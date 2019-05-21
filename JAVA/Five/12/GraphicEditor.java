import java.util.Scanner;
public class GraphicEditor{
    private static Shape start = null, end = null;
    private static void showAll(){
        Shape current = start;
        if(current == null)
            return;
        do{
            current.draw();
            current = current.getNext();
        }while (current != null);
    }
    private static boolean insertShape(int num){
        Shape temp;
        switch(num){
            case 1:
                temp = new Line();
                break;
            case 2:
                temp = new Rect();
                break;
            case 3:
                temp = new Circle();
                break;
            default:
                return false;
        }
        if(start==null)
            start = end = temp;
        else {
            end.setNext(temp);
            end = temp;
        }
        return true;
    }
    private static boolean delete(int num){
        if(num<=0) return false; //0 이하 삭제불가
        Shape current = start,prev = null; //현재 위치, 이전 위치
        while(--num != 0){
            if(current == null)
                return false; //이동하다가 현재 위치가 null이면 삭제 불가
            prev = current;
            current = current.getNext();
        }
        if(current == null) return false;
        if(end == start) start = end = null;
        else if(current.getNext()==null){
            prev.setNext(null);
            end = prev;
        }
        else
            if(prev==null)
                start = current.getNext();
            else
                prev.setNext(current.getNext());
        current = null;
        return true;
    }
    public static void main(String[] args){
        Scanner scanner = new Scanner(System.in);
        System.out.println("그래픽 에디터 beauty을 실행합니다.");
        int menu, menu2;
        while(true) {
            System.out.print("삽입(1), 삭제(2), 모두보기(3), 종료(4)>>");
            menu = scanner.nextInt();
            if(menu == 4) break;
            switch(menu){
                case 1:
                    System.out.print("Line(1), Rect(2), Circle(3)>>");
                    if(!GraphicEditor.insertShape(scanner.nextInt()))
                        System.out.println("도형 삽입에 실패하였습니다.");
                    break;
                case 2:
                    System.out.print("삭제할 도형의 위치>>");
                    if(!GraphicEditor.delete(scanner.nextInt()))
                        System.out.println("삭제할 수 없습니다.");
                    break;
                case 3:
                    GraphicEditor.showAll();
                    break;
                default:
                    System.out.println("잘못된 메뉴입니다.");
                    break;
            }
        }
    }
}