package lambdasinaction.chap6;

import java.util.Comparator;
import java.util.IntSummaryStatistics;
import java.util.function.BinaryOperator;

import static java.util.stream.Collectors.*;
import static lambdasinaction.chap6.Book.book;
import static lambdasinaction.chap6.Dish.menu;

public class Exercise {

    public static void main(String ... args) {
//        System.out.println("howManyBooks : " +howManyBooks());
//        System.out.println("findMostBookPriceComparator : " + findMostBookPriceComparator());
//        System.out.println("totalPrices : " + totalPrices());
//        System.out.println("avgPrice : " + avgPrice());
//        System.out.println("bookStatistics : " + bookStatistics());
//        System.out.println("shortBook : " + shortBook());
        System.out.println("twoTotalPrices : " + twoTotalPrices());
    }

    // 6.2 팩토리 메서드가 반환하는 컬렉터로 book의 갯수를 계산한다.
    private static long howManyBooks(){
//        return book.stream().collect(counting());
        // 위의 불필요한 과정을 생략
        return book.stream().count();
    }

    // 6.2.1 스트림값에서 최댓값, 최솟값 검색
    private static Book findMostBookPriceComparator(){
        // price 추출
        Comparator<Book> bookPriceComparator = Comparator.comparingInt(Book :: getPrice);
        // max값 구하기(요약연산 summarization)
//        BinaryOperator<Book> morePriceOf = BinaryOperator.maxBy(bookPriceComparator);
//        return book.stream().collect(reducing(morePriceOf)).get();

        // max값 구하기(요약연산 summarization)
        BinaryOperator<Book> morePriceOf = BinaryOperator.minBy(bookPriceComparator);
        return book.stream().collect(reducing(morePriceOf)).get();
    }

    // 6.2.2 요약연산
    /**
     * Collectors.summingInt라는 특별한 요약 팩토리 메서드를 제공한다.
     * summingInt는 객체를 int로 매핑하는 함수를 인수로 받는다.
     * summingLong, summingDouble도 summingInt와 같은 방식으로 동작(각각 long, double형식으로 데이터로 요약한다는 점만 다름)
     */
    private static int totalPrices(){
        return book.stream().collect(summingInt(Book::getPrice));
    }

    /**
     * averagingInt, averagingLong, averagingDouble등으로 다양한 형식의 숫자 집합의 평균 계산을 할수 있다.
     */
    private static double avgPrice(){
        return book.stream().collect(averagingInt(Book::getPrice));
    }

    /**
     * 두 개의 연산을 한 번에 수행해야 할 경우, 팩토리 메서드 summarizingInt가 반환하는 컬렉터를 사용 할수 있다.
     */
    private static IntSummaryStatistics bookStatistics(){
        return book.stream().collect(summarizingInt(Book::getPrice));
    }

    // 6.2.3 문자열 연결
    /**
     * joining 팩토리 메서드를 이용, 각 객체에 toString 메서드를 호출하여 추출한 모든 문자열을 하나의 문자열로 연결 반환 한다.
     */
    private static String shortBook(){
//        return book.stream().map(Book::getTitle).collect(joining());
        // 리스트를 콤마로 구분
        return book.stream().map(Book::getTitle).collect(joining(", "));
    }

    // 6.2.4 범용 리듀싱 요약 연산
    /**
     * 위의 모든 컬렉터는 reducing 팩토리 메서드로 정의 할수 있다.
     *
     * reducing 은 세개의 인수를 받는다.
     * 1. 리듀싱 연산의 시작값이거나 스트림에 인수가 없을 때는 반환값이다.(숫자합계에서는 인수가 없을 때   반환값으로 0이 적합하다.)
     * 2. 인수는 6.2.2 정수로 반환할 때 사용한 변환 함수다.
     * 3. 같은 종류의 두 항목을 하나의 값으로 더하는 BinaryOperator다(아래에서는 두개의 int가 사용되었다..
     */
    private static int twoTotalPrices(){
        return book.stream().collect(reducing(0, Book::getPrice, (i, j) -> i+j));
    }

}



/**
 * 팩토리 메서드 (Factory Method)
 * 일반적으로 객체를 생성할 때는 생성자(Constructor)를 Object object = new Object(); 와 같이 사용하는데
 * Static Factory Method 는 public static method 로서 외부 클래스에서 바로 접근할 수 있는 method 로,
 * 생성자의 역할을 한다.
 * 1. 생성자와 달리 자기나름의 이름을 가질수 있다.
 * 2. 중복 Signature Constructor 의 형태가 가능하다.
 * 3. 생성자와 달리 호출 될때마다 새로운 객체(instance)를 생성 할 필요가 있다.
 * 4. static factory method 는 반환하는 타입의 subType 도 반환할 수 있다.
 */