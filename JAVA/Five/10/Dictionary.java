class Dictionary extends PairMap{
    //protected String keyArray [];
    //protected String valueArray [];
    Dictionary(int number) {
        keyArray = new String[number];
        valueArray = new String[number];
    }
    String get(String key) {
        for(int i = 0; i<keyArray.length; i++) {
            if(keyArray[i]==key)
                return valueArray[i];
        }
        return null;
    }
    void put(String key, String value) {
        for(int i = 0; i<keyArray.length; i++) {
            if(keyArray[i]==key){
                valueArray[i]=value;
                return;
            }
        }
        for(int i = 0;i<keyArray.length; i++){
            if(keyArray[i]==null){
                keyArray[i]=key;
                valueArray[i]=value;
                return;
            }
        }
    }
    String delete(String key){
        for(int i = 0; i<keyArray.length; i++) {
            if(keyArray[i]==key)
                keyArray[i]=valueArray[i]=null;
        }
        return null;
    }
    int length() {
        int sum = 0;
        for(int i = 0;i<keyArray.length; i++){
            if(keyArray[i]!=null){
                sum++;
            }
        }
        return sum;
    }
}
