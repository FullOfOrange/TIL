package tensor;

public interface Matrix extends Cloneable{
    void setElement(int i, int j, Scalar scalar);
    void setElement(int i, int j, Double d);
    Scalar getElement(int i, int j);
    int getColumnSize();
    int getRowSize();

    void add(Matrix a) throws WrongLengthException;
    void mulRightSide(Matrix a) throws WrongLengthException;
    void mulLeftSide(Matrix a) throws WrongLengthException;

    void joinVertical(Matrix a) throws WrongLengthException;
    void joinHorizontal(Matrix a) throws WrongLengthException;
    Vector getRowToVector(int row) throws WrongIndexException;
    Vector getColumnToVector(int col)throws WrongIndexException;

    MatrixImpl getSubMatrix(int rowstart,int rowend,int colstart,int colend);
    MatrixImpl getMinorMatrix(int row,int col);
    Matrix transpose();
    Double getTrace();

    boolean isSquare();
    boolean isUpperTriangular();
    boolean isLowerTriangular();
    boolean isIdentity();
    boolean isZero();

    void tradeRow(int row1, int row2);
    void tradeColumn(int col1, int col2);
    void scalarMulToRow(int row, Scalar scalar);
    void scalarMulToColumn(int col, Scalar scalar);
    void rowMulToRow(int row, int multirow, Scalar scalar);
    void colMulToCol(int col, int multicol, Scalar scalar);

    MatrixImpl RREF();

    Matrix clone();
}
