class A10{
    public static void main(String[] args){
        int array[][] = new int[4][4];
        int i,j,max=0;
        while(max!=10) {
            i = (int) (Math.random() * 4);
            j = (int) (Math.random() * 4);
            System.out.println(i+""+j);
            if (array[i][j] == 0) {
                array[i][j] = (int)(Math.random() * 10) + 1;
                ++max;
            }
        }
        for(int x=0; x<array.length; x++){
            for(int y=0; y<array[x].length; y++){
                System.out.printf("%d ",array[x][y]);
            }
            System.out.println();
        }
    }
}