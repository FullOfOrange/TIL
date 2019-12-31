// 프로그래머스 전화번호 목록 풀이

class Solution {
    public boolean solution(String[] pb) {
        for(int i = 0; i < pb.length-1; i++){
            int temp = pb[i].hashCode();
            for(int j = 0; j < pb.length; j++){
                if(pb[i].length() >= pb[j].length())
                    continue;
                if(temp == pb[j].substring(0,pb[i].length()).hashCode())
                    return false;
            }
        }
        return true;
    }
}