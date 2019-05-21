package Ten;

import java.util.Scanner;

public class Gambling {
    private boolean winstate = false;
    private int i = 0;
    private Person[] a;
    public Gambling(Person[] p){ a = p; }

    public boolean getWinState(){
        return winstate;
    }
    public int randomValue(){
        return (int)(Math.random()*3)+1;
    }
    public void startGame(){
        i%=a.length;
        int[] state = new int[3];
        Scanner scanner = new Scanner(System.in);
        System.out.print(a[i].getName()+":<Enter>");
        scanner.nextLine();
        for (int i = 0; i < state.length; i++) {
            state[i] = randomValue();
            System.out.print(state[i]+" ");
        }
        if(state[0]==state[1]&&state[1]==state[2]) {
            winstate = true;
            System.out.println(a[i].getName() + "님이 이겼습니다!");
        }else System.out.println("아쉽군요!");
        i++;
    }
    public static void main(String[] args){
        Person[] p = new Person[2];
        p[0] = new Person("수희");
        p[1] = new Person("연수");

        Gambling gm = new Gambling(p);
        while(!gm.getWinState()){
            gm.startGame();
        }
    }
}
