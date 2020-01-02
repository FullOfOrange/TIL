// 프로그래머스 해시 위장문제 해결

import java.util.*;

class Solution {
    public int solution(String[][] clothes) {
        int answer = 1;
        // 아래의 부분은 각각 의상의 개수를 세기 위해서 해쉬맵 사용
        Map<String, Integer> map = new HashMap<>();
        for (int i = 0; i < clothes.length; i++) {
            Integer temp = map.get(clothes[i][1]);
            if (temp == null)
                temp = 0;
            map.put(clothes[i][1], temp + 1);
        }
        // 1.
        // (a+1) * (b+1) * ... - 1 의 공식인데 이것을 만족하는 알고리즘만 짜면 된다...
        // 이것을 간단히 풀어보자.
        // ab + a + b 인데, 이렇게 하면 각각의 모든 경우의 수를 뽑을 수 있다. 머리 개좋다.
        // 2.
        // 순열 nPr - n개의 카드 중에서 r개를 뽑아서 순서 있게 나열할 수 있는 경우의 수.
        // 조합 nCr - n개의 카드 중에서 r개를 뽑아서 순서 없이 무작위로 나열할 수 있는 경우의 수.
        // 여기서 예를 들면 하의 1개와 상의 2개가 있을 때 이것을 아무렇게나 뽑아서 나열할 수 있는
        // 모든 경우의 수를 구하면 된다.

        Iterator<Integer> it = map.values().iterator();
        while (it.hasNext()) {
            answer *= it.next().intValue() + 1;
        }
        return answer - 1;
    }
}