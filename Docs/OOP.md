# OOP | JAVA

### 설계 원칙

SOLID 라는 5원칙을 지켜야 한다

##### SRP (Single responsibility) - 단일 책임 원칙

클래스는 단 하나의 책임을 가져야 하고, 변경하는 이유 또한 하나여야한다. 클래스에게 많은 책임을 주어선 안된다.

##### OCP(Open-closed) - 개방-폐쇄 원칙

확장이 쉬워야하고, 변경은 하지 않아야 한다. 

##### LSP (Lixkov Substtitution) - 리스코프 치환 원칙

상위타입의 객체를 하위타입으로 치환하더라도, 상위타입을 사용하는 프로그램은 정상적으로 동작해야한다.
상속관계가 아닌 클래스들을 상속관계로 설정하면 이 원칙에 위배된다.

##### ISP - 인터페이스 분리 원칙

각각의 클라이언트가 사용하는 인터페이스를 분리해서 클라이언트가 사용하지 않는 인터페이스가 변경되어도 문제가 없도록 만들어야한다. impl 같은 방식으로 이것을 분리하는 것인듯

##### DIP - 의존 역전 원칙

고수준 모듈은 저수준 모듈의 구현에 의존해선 안된다. 고수준에서 (예를 들면 인터페이스 같은 추상화 된 것들) 만든 것에 저수준이 의존해야 한다. 한마디로, 기능 변경이 일어날땐 그 기능을 활용하는 메소드의 활용법은 변경되어서는 안된다는 것.

### 특징

추상화, 캡슐화, 상속, 다형성 의 특징이 있다.

#### 추상화



#### 다형성

동일한 메소드를 호출하지만 결론적으론 다른 결과를 일으키는 것.

Person 을 상속받는 Teacher와 Student 가 있고, 그들이 study라는 행위를 할 수 있다고 하자.

```java
class / Interface Person {
  public void study() {
    System.out.print("person")
  }
}

class Teacher extends Person {
  public void study() {
    System.out.print("make leacture ppt")
  }
}

class Student extends Person {
  public void study() {
    System.out.print("do homework")
  }
}
Person teacher = new Teacher();
Person student = new Student();
teacher.study() // make leacture ppt
student.study() // do homework
```

위와 같은 형태로 돌아가는 놈이다. 그니까 인터페이스나, 클래스로써 정의된 친구들의 상속된 것들을 모두 하나로 묶어서 제한해둔 뒤에, 이것의 호출을 통해서 하나의 타입이 여러개의 일을 할 수 있게 하는.. 그런 것 이다. 명확한 예시는 들지를 못하겠다.

**결론**

부모 클래스의 메소드를 자식 클래스가 오버라이드 되어있어, 현재 어떤 클래스 객체가 참조되는지 관계가 없이 데이터를 접근 가능하게 하는 것. 이런 점이 Interface를 빛나게 해주는 것 같다.

**주의점**

만약 Teacher class에 다른 메소드가 있는데 Person으로 형변환을 했을 경우에는 이것을 사용하지 못한다. 이 형으로 인해서 Teacher만큼의 메모리 공간이 생겼지만 Person 의 메모리 영역밖에 사용을 못하는 것 처럼 보이기 때문