package lambdasinaction.chap6;

import java.util.Arrays;
import java.util.List;

public class Book {

    private final String title;
    private final String writer;
    private final int price;
    private final Type type;

    public Book(String title, String writer, int price, Type type) {
        this.title = title;
        this.writer = writer;
        this.price = price;
        this.type = type;
    }

    public String getTitle() {
        return title;
    }

    public String getWriter() {
        return writer;
    }

    public int getPrice() {
        return price;
    }

    public Type getType() {
        return type;
    }

    public enum Type { LITERATURE, ESSAY, OTHER }

    @Override
    public String toString() {
        return title;
    }

    public static final List<Book> book =
            Arrays.asList( new Book("그 겨울의 일주일", "메이브 빈치", 13320, Book.Type.LITERATURE),
                           new Book("데미안", "헤르만 헤세", 7920, Book.Type.LITERATURE),
                           new Book("동물농장", "조지 오웰", 7020, Book.Type.LITERATURE),
                           new Book("지성인의 언어", "육문희", 11520, Book.Type.OTHER),
                           new Book("불행 피하기 기술", "롤프 도벨리", 14220, Book.Type.OTHER),
                           new Book("개인주의자 선언", "문유석", 12150, Book.Type.OTHER),
                           new Book("어떻게 살 것인가", "유시민", 13500, Book.Type.OTHER),
                           new Book("시로 납치하다", "류시화", 11700, Book.Type.ESSAY),
                           new Book("무뎌진다는 것", "투에고", 11520, Book.Type.ESSAY));
}