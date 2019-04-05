class A2 {
    public static void main(String[] args) {
        int n[][] = {{1}, {1, 2, 3}, {1}, {1, 2, 3, 4}, {1, 2}};

        for (int i = 0; i < n.length; i++) {
            for (int j = 0; j < n[i].length; j++) {
                System.out.printf("%d ",n[i][j]);
            }
            System.out.printf("\n");
        }
    }
}