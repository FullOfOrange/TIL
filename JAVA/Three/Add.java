class Add{
    public static void main(String[] args){
        int total = 0;
        System.out.println(args.length);
        for(int i=0; i<args.length; i++) {
            try {
                total += Integer.parseInt(args[i]);
            }catch(NumberFormatException e){ e.printStackTrace(); }
        }
        System.out.println(total);
    }
}