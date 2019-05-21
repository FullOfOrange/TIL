package Four.app;

import Four.base.Shape;
import Four.derived.Circle;

public class GraphicEditor{
    public static void main(String[] args){
        Shape shape = new Circle();
        shape.draw();
    }
}