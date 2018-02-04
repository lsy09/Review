package java8inaction.chap6;

import static java.util.stream.Collectors.reducing;
import static java8inaction.chap6.Book.book;

public class Quiz {
    /**
     * 리듀싱으로 문자열 연결하기
     * redusing 컬렉터로 올바르게 바꾼 코드는?
     *
     * String shortBook = book.stream().map(Book::getTitle).collect(joining());
     *
     * #### 범용 reducing으로 joining을 구현할 수 있음을 보여주는 예제 이며, 실무에서는 joining을 사용하는것이 가독성과 기능에 좋다.
     */

    public static void main(String[] args) {
        System.out.println("shortBook1 : "+shortBook1());
        System.out.println("shortBook2 : "+shortBook2());
        System.out.println("shortBook3 : "+shortBook3());
    }

    /**
     * 1.
     * 원래의 joining 컬렉터처럼 각 책을 책이름으로 변환한다음에 문자열 누적자로 사용해서 문자열 스트림을 리듀스 하면서 요리명을 하나씩 연결 한다.
     */
    private static String shortBook1(){
        return book.stream().map(Book::getTitle).collect(reducing((s1, s2) -> s1+s2)).get();
    }

    /**
     * 2.
     * reducing은 BinaryOperator<T>, 즉 BiFunction<T, T, T>를 인수로 받는다. 즉, reducing은 두 인수를 받아 같은 형식으로 반환하는 함수를 인수로 받는다.
     * 하지만 shortBook2의 표현식은 두 개의 요리를 인수로 받아 문자열을 반환한다.
     */
    private static String shortBook2(){
//        return book.stream().collect(reducing(d1, d2) -> d1.getTitle() + d2.getTitle())).get();
        return null;
    }

    /**
     * 3.
     * 빈 문자열을 포함하는 누적자를 이용해서 리듀싱 과정을 시작, 스트림의 Book을 방문하면서 각 책을 책이름으로 변환한 다음에 누적자로 추가한다
     * **세 개의 인수를 갖는 reducing은 누적자 초기값을 설정할 수 있으므로 Optional을 반환 할 필요가 없다.
     */
    private static String shortBook3(){
        return book.stream().collect(reducing("",Book::getTitle, (s1, s2) -> s1 + s2));
    }

}




