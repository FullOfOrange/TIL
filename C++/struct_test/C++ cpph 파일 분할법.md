## C++ cpp/h 파일 분할법

c++ struct/class 작성시에, .h 파일을 만들땐 선언부와 구현부를 분리해야함.

- Car.h

  여기에 선언문만 넣는다.

```c++
struct Car{
	private:
  	int car;
  	char carName;
  public:
  	int getCar();
  	char getCarName;
};
```

- Car.cpp

이곳에는 선언문에 포함된 구현부가 들어간다. 여기에서 .h를 include해줘야한다.

```c++
#include "Car.h"
int Car::getCar(){
  return car;
}
char Car::getCarName(){
	return carName;
}

```

이런식으로 두개의 파일로 분류하여 만든 다음, .cpp 파일을 컴파일만 하여 오브젝트 파일을 만들어 놓은 뒤에, main 을 만들시에, 실행파일을 만드는 것이다. 어려우면 make 쓰자.

​	$ g++ -o Car.cpp

​	$ g++ -c main.out main.cpp Car.o

이런식으로 명령을 서술하여 실행파일을 만든다.

