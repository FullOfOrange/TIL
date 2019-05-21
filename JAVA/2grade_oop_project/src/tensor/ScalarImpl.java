package tensor;

import java.math.BigDecimal;
import java.math.RoundingMode;

class ScalarImpl implements Scalar {
    private Double scalar;

    ScalarImpl(Double scalar){ this.scalar = scalar; }
    ScalarImpl(Double i, Double j){ //i~j까지 랜덤생성
        BigDecimal ba = new BigDecimal(Math.round(Math.random()*(j-i)*1.0E+10));
        BigDecimal bb = new BigDecimal(1.0E+10);
        BigDecimal bi = new BigDecimal(i.toString());
        this.scalar = ba.divide(bb).add(bi).doubleValue();
        //this.scalar = Math.random()*(j-i)+i;
    }
    @Override
    public void set(Double value){
        this.scalar = value;
    }
    @Override
    public Double get(){
        return this.scalar;
    }
    @Override
    public void add(Scalar s){ scalar = scalar + ((ScalarImpl)s).scalar; }
    @Override
    public void mul(Scalar s){ scalar = scalar * ((ScalarImpl)s).scalar; }

    public void div(Scalar s){ scalar = Double.valueOf(div(this,s).get()); }

    /*static method*/
    static Scalar add(Scalar a,Scalar b){
        BigDecimal ba = new BigDecimal(a.get().toString());
        BigDecimal bb = new BigDecimal(b.get().toString());
        ba = ba.add(bb);
        return new ScalarImpl(ba.doubleValue());
    }
    static Scalar mul(Scalar a,Scalar b){
        BigDecimal ba = new BigDecimal(a.get().toString());
        BigDecimal bb = new BigDecimal(b.get().toString());
        ba = ba.multiply(bb);
        return new ScalarImpl(ba.doubleValue());
    }
    static Scalar div(Scalar a,Scalar b){
        BigDecimal ba = new BigDecimal(a.get().toString());
        BigDecimal bb = new BigDecimal(b.get().toString());
        ba = ba.divide(bb,32, RoundingMode.HALF_EVEN);
        return new ScalarImpl(ba.doubleValue());
    }
    //static Scalar mul(Scalar a,Scalar b){ return Factory.newScalar((double)Math.round(a.get()*b.get()*100)/100); }
    /*ObjectClass Override*/
    @Override
    public boolean equals(Object obj){
        if (obj == null) return false;
        if (this.getClass() != obj.getClass()) return false;
        if (this == obj) return true;
        if (this.get().doubleValue() == ((Scalar)obj).get().doubleValue()) return true;
        return false;
    }
    @Override
    public String toString(){
        return String.valueOf(scalar);
    }

    @Override
    public Scalar clone(){
        try{
            ScalarImpl temp =  (ScalarImpl) super.clone();
            temp.scalar = scalar;
            return temp;
        }catch( CloneNotSupportedException e ){
            throw new AssertionError();
        }
    }
    @Override
    public int compareTo(Scalar s) {
        if(scalar>s.get()) return 1;
        else if(scalar==s.get()) return 0;
        else return -1;
    }
    public static void main(String[] args){
        Scalar scalar = Factory.newScalar(32.14);
        Scalar scalar1 = Factory.newScalar(2.13);
        System.out.println(Tensors.mulScalar(scalar,scalar1));
    }
}
