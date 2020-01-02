import java.util.*;

class Solution {
    public class All {
        public Music[] musics;
        public int play;

        public All(Music[] musics, int play) {
            this.musics = musics;
            this.play = play;
        }

    }

    public class Music {
        public int id;
        public int play;

        public Music(int id, int play) {
            this.id = id;
            this.play = play;
        }
    }

    public int[] solution(String[] genres, int[] plays) {        
        Map<String, Music[]> map = new HashMap<>();
        
        for(int i = 0; i < genres.length(); i++){
            Music[] temp = map.get(genres[i]);  
            if(temp == null){
                temp = new All([new Music(i, plays[i]), new Music(-1, -1)],plays[i]);
            }else{
                temp.play += plays[i];
                if(temp.musics[0].play <= plays[i]){
                    temp.musics[1] = temp.musics[0];
                    
                }
            }
         
            map.put(genres[i], temp);
        }
        
        int[] answer = {};
        return answer;
    }
}