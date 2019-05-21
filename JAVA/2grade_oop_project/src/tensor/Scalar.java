package tensor;

public interface Scalar extends Comparable<Scalar>,Cloneable{
    void set(Double value);
    Double get();

    void add(Scalar s);
    void mul(Scalar s);
    void div(Scalar s);

    Scalar clone();

}
