package Six;

import java.nio.file.SecureDirectoryStream;
import java.util.Calendar;
import java.util.Scanner;

public class SecGame{
    private String name;
    private int sec,result;

    SecGame(String name){ this.name = name; }

    public String getName() { return name; }
    public int getResult() { return result; }

    public void startGame(){
        Scanner scanner = new Scanner(System.in);
        System.out.print(getName()+"시작 <Enter>키>>");
        scanner.nextLine();
        sec = Calendar.getInstance().get(Calendar.SECOND);
        System.out.println("현재 초 시간 = "+sec);
        System.out.print("10초 예상 후 <Enter>키>>");
        scanner.nextLine();
        result = Calendar.getInstance().get(Calendar.SECOND);
        System.out.println("현재 초 시간 = "+result);
        if(result<sec)
            result = 60+result-sec;
        else result -= sec;
    }
    public static void Winnner(SecGame a, SecGame b){
        System.out.print(a.getName() +"의 결과 "+a.getResult()+", "+b.getName() +"의 결과 "+b.getResult()+", ");
        if(Math.abs(10-a.getResult())<Math.abs(10-b.getResult()))
            System.out.println("승자는 "+a.getName());
        else if(Math.abs(10-a.getResult())>Math.abs(10-b.getResult()))
            System.out.println("승자는 "+b.getName());
        else
            System.out.println("비겼습니다.");
    }
    public static void main (String[] args){
        SecGame a = new SecGame("황기태");
        SecGame b = new SecGame("이재문");
        a.startGame();
        b.startGame();
        SecGame.Winnner(a,b);
    }
}