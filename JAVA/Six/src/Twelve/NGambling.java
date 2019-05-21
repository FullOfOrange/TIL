package Twelve;

import Ten.Gambling;
import Ten.Person;

import java.util.Scanner;

public class NGambling {
    public static void main(String[] args){
        int person_num;
        Scanner scanner = new Scanner(System.in);
        System.out.print("게임에 참여할 선수 숫자>>");
        person_num = scanner.nextInt();
        //scanner.nextLine();
        Person[] p = new Person[person_num];
        for(int i = 0; i < person_num; i++){
            System.out.print(i+1+"번째 선수 이름>>");
            p[i] = new Person(scanner.next());
        }
        Gambling gm = new Gambling(p);
        while(!gm.getWinState()){
            gm.startGame();
        }
    }
}
