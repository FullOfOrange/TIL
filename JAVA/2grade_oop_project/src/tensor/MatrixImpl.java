package tensor;

import java.io.*;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

class MatrixImpl implements Matrix {
    private ArrayList<Vector> matrix;
    MatrixImpl(int m, int n, Scalar scalar){
        matrix = new ArrayList<>();
        for(int x = 0; x<m; x++)
            matrix.add(Factory.newVector(n,scalar));
    }
    MatrixImpl(int m, int n, Double d){
        this(m,n,Factory.newScalar(d));
    }
    MatrixImpl(int m, int n, double i, double j){
        matrix = new ArrayList<>();
        for(int x = 0; x < m; x++)
            matrix.add(Factory.newVector(n,i,j));
    }
    MatrixImpl(String filepath) {//이건 파일 불러오기.
        //반환용 리스트
        List<List<String>> ret = new ArrayList<List<String>>();
        BufferedReader br = null;

        try {
            br = Files.newBufferedReader(Paths.get("/Users/fulloforange/Book1.csv"));
            Charset.forName("UTF-8");
            String line = "";

            while ((line = br.readLine()) != null) {
                //CSV 1행을 저장하는 리스트
                List<String> tmpList = new ArrayList<String>();
                String array[] = line.split(",");
                //배열에서 리스트 반환
                tmpList = Arrays.asList(array);
                System.out.println(tmpList);
                ret.add(tmpList);
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if (br != null) {
                    br.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }//미완
    MatrixImpl(Scalar[][] scalars){
        matrix = new ArrayList<>();
        for(int x=0; x<scalars.length; x++){
            matrix.add(Factory.newVector(scalars[x]));
        }
    }
    MatrixImpl(double[][] doubles){
        matrix = new ArrayList<>();
        for(int x=0; x<doubles.length; x++){
            matrix.add(Factory.newVector(doubles[x]));
        }
    }
    MatrixImpl(int n){//단위행렬 생성임.
        matrix = new ArrayList<>();
        for(int i = 0; i<n; i++){
            Vector temp = Factory.newVector(n,(double)0);
            temp.setElement(i,(double)1);
            matrix.add(temp);
        }
    }
    MatrixImpl(int n, Vector vector){ // vector로 채워진 Matrix 생성 (Default로 제공함.)
        matrix = new ArrayList<>();
        for(int i = 0; i < n; i++)
            matrix.add(i,vector);
    }
    @Override
    public void setElement(int i, int j, Scalar scalar){//완료
        matrix.get(i).setElement(j,scalar);
    }
    @Override
    public void setElement(int i, int j, Double d){//완료
        this.setElement(i,j,Factory.newScalar(d));
    }
    @Override
    public Scalar getElement(int i, int j){ return matrix.get(i).getElement(j); }//완료
    @Override
    public int getRowSize(){ return matrix.size(); }//완료
    @Override
    public int getColumnSize(){ return matrix.get(0).getSize(); }//완료
    @Override
    public void add(Matrix a) throws WrongLengthException {
        try{
            if(getRowSize()!=a.getRowSize() || getColumnSize()!=a.getColumnSize())
                throw new WrongLengthException();
        }catch (WrongLengthException e){
            System.out.println("길이가 맞지 않습니다.");
            return;
        }
        for(int i = 0; i<getRowSize(); i++)
            for(int j = 0; j<getColumnSize(); j++)
                setElement(i,j,a.getElement(i,j));
    }
    @Override
    public void mulRightSide(Matrix a) throws WrongLengthException{ matrix = MatrixImpl.mul(this,a).matrix; }
    @Override
    public void mulLeftSide(Matrix a) throws WrongLengthException{ a.mulRightSide(this); }
    /*Advenced Feauture
    public Vector getRowtoVector(int rownum){
        VectorImpl temp = new VectorImpl(getRowSize(),0.0);
        for(int i = 0; i < getRowSize(); i++) {
            temp.setElement(i,getElement());
        }
    }*/
    @Override
    public Vector getRowToVector(int row) throws WrongIndexException{
        try{
            if(row<0||getRowSize()<=row) throw new WrongIndexException();
        }catch (WrongIndexException e){
            System.out.println("길이가 맞지 않습니다.");
            return null;
        }
        return matrix.get(row).clone();
    }
    @Override
    public Vector getColumnToVector(int col) throws WrongIndexException{//예외처리쪽 확인.
        try{
            if(col<0||getColumnSize()<=col) throw new WrongIndexException();
        }catch (WrongIndexException e){
            System.out.println("길이가 맞지 않습니다.");
            return null;
        }
        VectorImpl vector = new VectorImpl(getRowSize(),0.0);
        for(int x = 0; x<getRowSize(); x++)
            vector.setElement(x,getElement(x,col).get());
        return vector;
    }
    @Override
    public Matrix transpose(){
        Matrix temp = new MatrixImpl(getColumnSize(),getRowSize(),0.0);
        for(int i = 0; i < getRowSize(); i++)
            for(int j = 0; j < getColumnSize(); j++)
                temp.setElement(j,i,getElement(i,j));
        return temp;
    }
    @Override
    public void joinVertical(Matrix a) throws WrongLengthException{
        try{
            if(getColumnSize()!=a.getColumnSize()) throw new WrongLengthException();
        }catch (WrongLengthException e){
            System.out.println("길이가 맞지 않습니다.");
            return;
        }
        matrix = joinVertical(this, a).matrix;
    }
    @Override
    public void joinHorizontal(Matrix a) throws WrongLengthException{
        try{
            if(getColumnSize()!=a.getColumnSize()) throw new WrongLengthException();
        }catch (WrongLengthException e){
            System.out.println("길이가 맞지 않습니다.");
            return;
        }
        matrix = joinHorizontal(this, a).matrix;
    }
    @Override
    public MatrixImpl getSubMatrix(int rowstart,int rowend,int colstart,int colend){
        try{
            if(rowstart<0||colstart<0||rowend>getRowSize()||colend>getColumnSize()) throw new WrongIndexException();//각각의 입력 범위가 수를 넘어갔을때에.
        }catch (WrongIndexException e){
            System.out.println("길이가 맞지 않습니다.");
            return null;
        }
        MatrixImpl temp = new MatrixImpl(rowend-rowstart,colend-colstart,0.0);
        for(int i=0; i<rowend-rowstart; i++)
            for(int j = 0; j<colend-colstart; j++)
                temp.setElement(i,j,getElement(i+rowstart,j+colstart));
        return temp;
    }
    @Override
    public MatrixImpl getMinorMatrix(int row,int col){ // 미완
        try{
            if(0>row||row>=getRowSize()||0>col||col>=getColumnSize()) throw new WrongLengthException();//각각의 입력 범위가 수를 넘어갔을때에.
        }catch (WrongLengthException e){
            System.out.println("길이가 맞지 않습니다.");
            return null;
        }
        return null;
    }
    @Override
    public Double getTrace(){
        try{// 대각행렬이 아님.
            if(!isSquare()) throw new WrongLengthException();//각각의 입력 범위가 수를 넘어갔을때에.
        }catch (WrongLengthException e){
            System.out.println("길이가 맞지 않습니다.");
            return 0.0;
        }
        double sum = 0.0;
        for(int i = 0; i < getRowSize(); i++){
            sum+=getElement(i,i).get();
        }
        return sum;
    }
    @Override
    public boolean isSquare(){
        if(getRowSize()==getColumnSize()) return true;
        else return false;
    }
    @Override
    public boolean isUpperTriangular(){
        try{
            if(!isSquare()) throw new NotSquareException();
        }catch (NotSquareException e){
            System.out.println(e);
        }
        for(int i = 1; i < getRowSize(); i++)
            for(int j = 1; j <= i; j++)
                if(getElement(i,j).get()!=0.0) return false;
        return true;
    }
    @Override
    public boolean isLowerTriangular(){
        try{
            if(!isSquare()) throw new NotSquareException();
        }catch (NotSquareException e){
            System.out.println(e);
        }
        for(int i = 1; i < getColumnSize(); i++)
            for(int j = 1; j <= i; j++)
                if(getElement(j,i).get()!=0.0) return false;
        return true;
    }
    @Override
    public boolean isIdentity(){
        try{
            if(!isSquare()) throw new NotSquareException();
        }catch (NotSquareException e){
            System.out.println(e);
        }
        for(int i = 0; i < getRowSize(); i++)
            if(getElement(i,i).get()!=1.0) return false;
        if(!isLowerTriangular()||!isUpperTriangular()) return false;
        return true;
    }
    @Override
    public boolean isZero(){
        for(int i = 0; i < getRowSize(); i++)
            for(int j = 0; j < getColumnSize(); j++)
                if(getElement(i,j).get()!=0.0) return false;
        return true;
    }
    /*elementary operation*/
    @Override
    public void tradeRow(int row1, int row2) throws WrongIndexException{
        if(row1<0||row2<0||row1>=getRowSize()||row2>=getRowSize()) throw new WrongIndexException();
        VectorImpl temp = (VectorImpl)matrix.get(row1);
        matrix.set(row1,matrix.get(row2));
        matrix.set(row2,temp);
    }
    @Override
    public void tradeColumn(int col1, int col2) throws WrongIndexException{
        if(col1<0||col2<0||col1>=getColumnSize()||col2>=getColumnSize()) throw new WrongIndexException();
        VectorImpl temp = (VectorImpl)getColumnToVector(col1);
        for(int i = 0; i < getRowSize(); i++){
            setElement(i,col1,getElement(i,col2).get());
            setElement(i,col2,temp.getElement(i));
        }
    }
    @Override
    public void scalarMulToRow(int row, Scalar scalar){
        for(int i = 0; i < getColumnSize(); i++)
            setElement(row,i,Tensors.mulScalar(getElement(row,i),scalar));
    }
    @Override
    public void scalarMulToColumn(int col, Scalar scalar){
        for(int i = 0; i < getRowSize(); i++)
            setElement(i,col,Tensors.mulScalar(getElement(i,col),scalar));
    }
    @Override
    public void rowMulToRow(int row, int multirow, Scalar scalar){
        matrix.get(row).add(VectorImpl.mulScalar(matrix.get(multirow),scalar));
    }
    @Override
    public void colMulToCol(int col, int multicol, Scalar scalar){
        Vector temp = getColumnToVector(col);
        temp.add(VectorImpl.mulScalar(getColumnToVector(multicol),scalar));
        for(int i = 0; i<getRowSize(); i++)
            setElement(i,col,temp.getElement(i).get());
    }
    /*RREF*/
    public MatrixImpl RREF() {
        MatrixImpl rref = (MatrixImpl)clone();
        int pivot_row,pivot_col = 0,cur_row;
        for (pivot_row = 0; pivot_row < getRowSize()&&pivot_col < getColumnSize(); pivot_row++) {//row 순회용
            for(cur_row = pivot_row; pivot_col < getColumnSize() && Math.abs(getElement(cur_row,pivot_col).get()) == 0.0; pivot_col++) //column 순회용(밑에 pivot이 결정되면, 탈출)
                for (; cur_row < getRowSize(); cur_row++);  //pivot 설정용 row 순회(0이 아닌것 찾는거)
            for(int i = pivot_row+1; i < getRowSize(); i++) {
                rref.rowMulToRow(i, pivot_row, ScalarImpl.div(ScalarImpl.mul(new ScalarImpl(-1.0),rref.getElement(i, pivot_col)),rref.getElement(pivot_row,pivot_col)));
            }
            rref.matrix.get(pivot_row).mulScalar(ScalarImpl.div(new ScalarImpl(1.0),rref.getElement(pivot_row,pivot_col)));
            ++pivot_col;
        }
        
        return rref;
    }
    /*static Method*/
    static MatrixImpl add(Matrix a, Matrix b) throws WrongLengthException{
        Matrix temp = a.clone();
        temp.add(b);
        return (MatrixImpl)temp;
    }
    static MatrixImpl mul(Matrix a, Matrix b) throws WrongLengthException{
        try{
            if(a.getColumnSize()!=b.getRowSize())
                throw new WrongLengthException();
            Matrix temp = Factory.newMatrix(a.getRowSize(),b.getColumnSize(),0.0);
            for(int x = 0; x < a.getRowSize(); x++)
                for(int z = 0; z < b.getColumnSize(); z++)
                    for(int y = 0; y < a.getColumnSize(); y++)
                        temp.setElement(x,z,ScalarImpl.mul(a.getElement(x,y),b.getElement(y,z)));
            return (MatrixImpl)temp;
        }catch (WrongLengthException e){
            System.out.println("길이가 맞지 않습니다.");
            return (MatrixImpl)a;
        }
    }
    static MatrixImpl joinVertical(Matrix a,Matrix b){//세로로 합쳐짐
        try{
            if(a.getColumnSize()!=b.getColumnSize()) throw new WrongLengthException();
        }catch (WrongLengthException e){
            System.out.println("길이가 맞지 않습니다.");
            return null;
        }
        Scalar[][] scalars = new Scalar[a.getRowSize()+b.getRowSize()][a.getColumnSize()];
        for(int i = 0; i<a.getRowSize(); i++)
            for(int j = 0; j < a.getColumnSize(); j++ )
                scalars[i][j] = a.getElement(i, j);
        for(int i = 0; i<b.getRowSize(); i++)
            for(int j = 0; j < b.getColumnSize(); j++ )
                scalars[i+a.getRowSize()][j] = b.getElement(i, j);
        return new MatrixImpl(scalars);
    }
    static MatrixImpl joinHorizontal(Matrix a,Matrix b){//가로로 합쳐짐
        try{
            if(a.getRowSize()!=b.getRowSize()) throw new WrongLengthException();
        }catch (WrongLengthException e){
            System.out.println("길이가 맞지 않습니다.");
            return null;
        }
        Scalar[][] scalars = new Scalar[a.getRowSize()][a.getColumnSize()+b.getColumnSize()];
        for(int i = 0; i<a.getRowSize(); i++)
            for(int j = 0; j < a.getColumnSize(); j++ )
                scalars[i][j] = a.getElement(i, j);
        for(int i = 0; i<b.getRowSize(); i++)
            for(int j = 0; j < b.getColumnSize(); j++ )
                scalars[i][j+a.getColumnSize()] = b.getElement(i,j);
        return new MatrixImpl(scalars);
    }
    /*ObjectClass Override*/
    @Override
    public boolean equals(Object obj) {
        if (obj == null) return false;
        if (this.getClass() != obj.getClass()) return false;
        if (this == obj) return true;
        if (getRowSize() != ((Matrix)obj).getRowSize()||getColumnSize() != ((Matrix)obj).getColumnSize()) return false;
        for(int i = 0; i < getRowSize(); i++)
            for(int j = 0; j < getColumnSize(); j++)
                if (!getElement(i,j).equals(((Matrix) obj).getElement(i,j))) return false;
        return true;
    }
    @Override
    public String toString(){//완
        StringBuffer string = new StringBuffer();
        for(int i = 0; i<matrix.size(); i++)
            string.append(matrix.get(i).toString()).append('\n');
        return string.toString();
    }
    @Override
    public Matrix clone(){
        try{
            MatrixImpl temp = (MatrixImpl) super.clone();
            temp.matrix = (ArrayList<Vector>)matrix.clone(); // arraylist는 clone이 안된다. 얘도 객체이므로 반드시 클론을 해주자.
            for(int i = 0; i < getRowSize(); i++)
                temp.matrix.set(i,matrix.get(i).clone());
            return temp;
        }catch( CloneNotSupportedException e ){
            throw new AssertionError();
        }
    }
    public static void main(String[] args){
        Matrix matrix = Factory.newMatrix(50,10,0.1, 145.0);
        System.out.print(matrix);
        matrix.setElement(1,2,3.0);
        System.out.println(matrix.RREF());
    }
}

