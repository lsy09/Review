package java8inaction.chap7;

public class Spliterator {

    public static final String SENTENCE =
            " Nel   mezzo del cammin  di nostra  vita " +
                    "mi  ritrovai in una  selva oscura" +
                    " che la  dritta via era   smarrita ";

    public static void main(String[] args) {
        System.out.println("foun : "+ countWordsIteratively(SENTENCE)+ " words");
    }

    /**
     * 커스텀 Spliterator 구현하기
     */
    public static int countWordsIteratively(String s){
        int counter = 0;
        boolean lastSpace = true;
        for(char c : s.toCharArray()){
            if(Character.isWhitespace(c)){
                lastSpace = true;
            }else{
                if(lastSpace){
                    counter++;
                }
                lastSpace = false;
            }
        }
        return counter;
    }


}
