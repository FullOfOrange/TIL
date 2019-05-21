package tensor;

public class Tensors {
    public static Matrix addMatrix(Matrix a, Matrix b) throws WrongLengthException{ return MatrixImpl.add(a,b); }
    public static Matrix mulMatrix(Matrix a, Matrix b) throws WrongLengthException{ return MatrixImpl.mul(a,b); }
    public static Matrix joinHorizontalMatrix(Matrix a, Matrix b) throws WrongLengthException{ return MatrixImpl.joinHorizontal(a,b); }
    public static Matrix joinVerticalMatrix(Matrix a, Matrix b) throws WrongLengthException{ return MatrixImpl.joinVertical(a,b); }

    public static Vector addVector(Vector a,Vector b) throws WrongLengthException{ return VectorImpl.add(a,b); }
    public static Vector mulVectorWithScalar(Vector vector,Scalar scalar){ return VectorImpl.mulScalar(vector,scalar); }

    public static Scalar addScalar(Scalar a,Scalar b){ return ScalarImpl.add(a, b); }
    public static Scalar mulScalar(Scalar a,Scalar b){ return ScalarImpl.mul(a, b); }
}
