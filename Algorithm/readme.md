# 알고리즘 풀다가 정리하는 JAVA 문법

### Tips

1. 대체로 java.util.* 을 import하면 앵간한 Util 성 함수는 다 가져다 쓸 수 있다. 일단 이걸 임포트하고 쓰자.

2. Array 값 순회
```java
for(String name: names){
    // 이곳에서 name이 for of 마냥 value를 순회하면서 확인한다.
}
```

3. String 클래스 특징
    - Sort가 가능하다. 배열형태로 있으면 소트 태워볼 생각도 해보자.
    - startsWith, endsWith 라는 메소드도 존재한다.

4. HashMap을 애용해보자. 적절한 반복이 있는놈들을 빠르게 셀 수 있다.
```java
Map<String, String> map = new HashMap<>();
```

5. Map 같은 Collection 의 형태를 상속받은 클래스의 경우엔 iterator 라는 메소드가 있다. 이거로 순회를 해보자.
Iterator<Integer> it = map.values().iterator();
while (it.hasNext()) {
    answer *= it.next().intValue() + 1;
}