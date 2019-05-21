package tensor;

public class Factory {
    public static Scalar newScalar(Double scalar){ return new ScalarImpl(scalar); }
    public static Scalar newScalar(Double i, Double j){ return new ScalarImpl(i,j); }

    public static Vector newVector(int demention,Scalar scalar){ return new VectorImpl(demention,scalar); }
    public static Vector newVector(int demention,Double d){ return new VectorImpl(demention,d); }
    public static Vector newVector(int demention,double i, double j) { return new VectorImpl(demention,i, j); }
    public static Vector newVector(Scalar[] scalars) { return new VectorImpl(scalars);}
    public static Vector newVector(double[] doubles){ return new VectorImpl(doubles); }

    public static Matrix newMatrix(int row, int col, double i, double j){ return new MatrixImpl(row,col,i,j); }
    public static Matrix newMatrix(int row, int col, Scalar scalar) { return new MatrixImpl(row,col,scalar); }
    public static Matrix newMatrix(int row, int col, Double d) { return new MatrixImpl(row,col,d); }
    public static Matrix newMatrix(String filepath) { return new MatrixImpl(filepath); }
    public static Matrix newMatrix(Scalar[][] scalars) { return new MatrixImpl(scalars); }
    public static Matrix newMatrix(double[][] doubles) { return new MatrixImpl(doubles); }
    public static Matrix newMatrix(int n) { return new MatrixImpl(n); }
}
