package tensor;

public interface Vector extends Cloneable{

    Scalar getElement(int i);
    void setElement(int i,Scalar scalar);
    void setElement(int i,Double d);
    int getSize();

    void add(Vector a) throws WrongLengthException;
    void mulScalar(Scalar a);

    Matrix vectorToNx1Matrix();
    Matrix vectorTo1xNMatrix();

    Vector clone();
}
