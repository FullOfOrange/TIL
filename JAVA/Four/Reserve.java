import java.util.Scanner;
class Seats{
    private String[][] seats = new String[3][10];

    Seats() {//생성자 초기화시, ---으로 초기화한다.
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 10; j++) {
                this.seats[i][j] = "---";
            }
        }
    }
    private String getSeatGrade(int a){
        if(a==0) return "S";
        else if(a==1) return "A";
        else return "B";
    }

    protected void showSeats(int a){
        System.out.print(getSeatGrade(a)+">>");
        for(int i=0; i<10; i++){
            System.out.print(" "+seats[a][i]);
        }
        System.out.println();
    }
    protected boolean reserveSeat(String name,int num,int seat){
        if(seats[seat][num].equals("---")){
            seats[seat][num] = name;

            return true;
        }else
            return false;
    }
    protected boolean deleteSeatReserve(String name,int seat){
        int i=0;
        while(i<10){
            if(seats[seat][i].equals(name)) {
                seats[seat][i] = "---";
                break;
            }
            i++;
        }
        if(i==10) return false;
        else return true;
    }
}


class Reserve{
    public static void main(String[] args){
        Scanner scanner = new Scanner(System.in);

        Seats seats = new Seats();//객체 생성

        System.out.println("명품몬서트홀 예약 시스템입니다.");

        int selectmenu = 0;
        int selectgrade = 0;
        String name = null;
        int seatnum = 0;
        while(true) {
            System.out.print("예약 :1, 조회:2, 취소:3, 끝내기:4 >>");
            selectmenu = scanner.nextInt();
            if(selectmenu==4)
                break;
            else if(selectmenu==1){//예약
                System.out.print("죄석구분 S(1), A(2), B(3) >>");
                try {
                    selectgrade = scanner.nextInt();
                    seats.showSeats(selectgrade - 1);

                    System.out.print("이름>>");
                    name = scanner.next();
                    System.out.print("번호>>");
                    seatnum = scanner.nextInt();

                    if (seats.reserveSeat(name, seatnum - 1, selectgrade - 1)) ;
                    else System.out.println("예약에 실패하였습니다.");
                }catch(ArrayIndexOutOfBoundsException e){
                    System.out.println("없는 좌석입니다.");
                }
            }else if(selectmenu==2){//조회
                for(int i = 0; i<3; i++)
                    seats.showSeats(i);
            }else if(selectmenu==3){//취소
                System.out.print("죄석구분 S(1), A(2), B(3) >>");
                selectgrade = scanner.nextInt();
                seats.showSeats(selectgrade-1);

                System.out.print("이름>>");
                name = scanner.next();

                if(seats.deleteSeatReserve(name,selectgrade-1));
                else System.out.println("취소에 실패하였습니다.");
            }
        }
    }
}