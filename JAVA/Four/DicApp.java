import java.util.Scanner;

class Dictionary{
    private static String[] kor = {"사랑","아기","돈","미래","희망"};
    private static String[] eng = {"love","baby","money","future","hope"};
    public static String kor2Eng(String word){
        int i=0;
        while(i<kor.length){
            if(kor[i].equals(word))
                break;
            i++;
        }
        if(i==kor.length) return "ㅇ";
        else return eng[i];
    }
}
class DicApp{
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("한영 단어 검색 프로그램입니다.");

        String word = null;
        while(true) {
            System.out.print("한글 단어?");

            word = scanner.next();
            if (word.equals("그만"))
                break;
            String temp = Dictionary.kor2Eng(word);
            if(temp.equals("ㅇ"))
                System.out.println(word+"는 저의 사전에 없습니다.");
            else
                System.out.println(word+"은 "+temp);
        }
        scanner.close();
    }
}