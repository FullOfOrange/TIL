package tensor;

import java.util.ArrayList;
import java.util.Arrays;

class VectorImpl implements Vector {
    private ArrayList<Scalar> vector; //내부에는 Scalar가 저장됨.
    /*생성자*/
    VectorImpl(int demention,Scalar scalar){ //Scalar로 내부를 채워넣음
        vector = new ArrayList<>();
        for(int x=0; x<demention; x++)
            vector.add(scalar);
    }
    VectorImpl(int demention,Double d){ //Scalar로 내부를 채워넣음
        this(demention,new ScalarImpl(d));
    }
    VectorImpl(int demention,double i, double j){//i~j까지 랜덤생성
        vector = new ArrayList<>();
        for(int x=0; x<demention; x++)
            vector.add(new ScalarImpl(i,j));
    }
    VectorImpl(Scalar[] scalars){//scalar를 받아서 씀
        vector = new ArrayList<>(Arrays.asList(scalars));
    }
    VectorImpl(double[] doubles){//double을 받아서 씀
        Scalar[] scalars = new Scalar[doubles.length];
        for(int i = 0; i < doubles.length; i++) {
            scalars[i] = new ScalarImpl(doubles[i]);
        }
        vector = new ArrayList<>(Arrays.asList(scalars));
    }
    @Override
    public Scalar getElement(int i){ return vector.get(i).clone(); }
    @Override
    public void setElement(int i,Scalar scalar){
        vector.set(i,scalar);
    }
    @Override
    public void setElement(int i,Double d){
        this.setElement(i,new ScalarImpl(d));
    }
    @Override
    public int getSize(){
        return vector.size();
    }
    @Override
    public void add(Vector a) throws WrongLengthException{
        try{
            if(getSize() != a.getSize())
                throw new WrongLengthException();
            for(int i = 0; i<getSize(); i++)
                setElement(i,ScalarImpl.add(getElement(i),a.getElement(i)));
        }catch(WrongLengthException e){
            System.out.println("length is wrong");
        }
    }
    @Override
    public void mulScalar(Scalar a){
        for(int i = 0; i<getSize(); i++){
            setElement(i,ScalarImpl.mul(getElement(i),a));
        }
    }
    /*Advenced Feauture*/
    @Override
    public Matrix vectorToNx1Matrix(){
        return vectorTo1xNMatrix().transpose();
        /*MatrixImpl temp = new MatrixImpl(getSize(),1,0.0);
        for(int i = 0; i < getSize(); i++)
            temp.setElement(i,0,getElement(i));
        return temp;
        */
    }
    @Override
    public Matrix vectorTo1xNMatrix() { return new MatrixImpl(1, this); }
    /*static method*/
    static Vector add(Vector a,Vector b) throws WrongLengthException{
        Vector temp = a.clone();
        temp.add(b);
        return temp;
    }
    static Vector mulScalar(Vector vector,Scalar scalar){
        Vector temp = vector.clone();
        temp.mulScalar(scalar);
        return temp;
    }

    @Override
    public boolean equals(Object obj){
        if (obj == null) return false;
        if (this.getClass() != obj.getClass()) return false;
        if (this == obj) return true;
        for(int i = 0; i< vector.size(); i++) {
            if(!getElement(i).equals(((Vector)obj).getElement(i))) return false;
        }
        return true;
    }
    @Override
    public String toString(){
        StringBuffer string = new StringBuffer();
        for(int i = 0; i<vector.size(); i++){
            string.append(vector.get(i).get()+" ");
        }
        return string.toString();
    }
    @Override
    public Vector clone(){
        try{
            VectorImpl temp = (VectorImpl) super.clone();
            temp.vector = (ArrayList<Scalar>)vector.clone(); // arraylist는 clone이 안된다. 얘도 객체이므로 반드시 클론을 해주자.
            for(int i = 0; i < getSize(); i++) {
                temp.setElement(i, getElement(i).clone());
            }
            return temp;
        }catch( CloneNotSupportedException e ){
            throw new AssertionError();
        }
    }
    public static void main(String[] args){
        Vector vector = Factory.newVector(10,23.4,123.5);
        System.out.println(vector.getElement(0).get());
        Matrix matrix = vector.vectorToNx1Matrix();
        System.out.println(vector.vectorTo1xNMatrix());
        System.out.println(matrix);
    }
}
