import java.util.Scanner;

class Phone {
    private String name, tel;

    Phone(String name, String tel){
        this.name = name;
        this.tel = tel;
    }
    public String getTel(){
        return tel;
    }
    public static int search(Phone a[], String name){
        int i=0;
        while(i<a.length){
            if(a[i].name.equals(name))
                break;
            i++;
        }
        return i;
    }
}

class PhoneBook{

    public static void main(String[] args){
        Scanner scanner = new Scanner(System.in);
        System.out.print("인원수>>");
        int n = scanner.nextInt();

        Phone[] p = new Phone[n];

        for(int i=0; i<n; i++){
            System.out.print("이름과 전화번호(이름과 번호는 빈 칸없이 입력)>>");
            String name = scanner.next();
            String tel = scanner.next();
            p[i] = new Phone(name,tel);
        }
        System.out.println("저장되었습니다...");
        int geti=0;
        String searchname = null;
        while(true) {
            System.out.print("검색할 이름>>");
            searchname = scanner.next();
            if(searchname.equals("그만"))
                break;
            geti=Phone.search(p,searchname);
            if(geti==p.length)
                System.out.println(searchname+"이 없습니다.");
            else
                System.out.println(searchname+"의 번호는 "+p[geti].getTel()+" 입니다.");
        }
        scanner.close();
    }
}