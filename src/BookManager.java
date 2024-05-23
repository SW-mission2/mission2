import java.lang.module.FindException;
import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

public class BookManager {
    public static List<Book> bookstorage = new ArrayList<>();

    public class Book {
        private String id;
        private String title;
        private String author;
        private int published;

        public Book(String id, String title, String author, int published){
            this.id = id;
            this.title = title;
            this.author = author;
            this.published = published;
        }

        public String printbook() {
            return "Book{id: '"+id+"', 제목: '"+title+"', 저자: '"+author+"', 출판년도: "+published+"}";
        }

        public String getId() {
            return id;
        }
    }

    public void addbook(String id, String title, String author, int published) {
        if (bookstorage.stream().anyMatch(x -> x.getId().equals(id))) {
            throw new FindException("해당 ID("+id+") 는 이미 존재합니다.");
        }
        Book book = new Book(id, title, author, published);
        bookstorage.add(book);
        System.out.println(book.printbook()+"도서가 추가되었습니다.");
    }
    public void searchbook(String id) {
        Book book = bookstorage.stream().filter(x -> x.getId().equals(id)).findFirst().orElseThrow(() -> new NoSuchElementException("검색된 도서가 없습니다."));
        System.out.println("검색 결과:\n"+book.printbook());
    }
    public void removebook(String id) {
        Book book = bookstorage.stream().filter(x -> x.getId().equals(id)).findFirst().orElseThrow(() -> new NoSuchElementException("해당 ID("+id+")의 도서를 찾을 수 없습니다."));
        bookstorage.remove(book);
        System.out.println(book.printbook()+"도서를 삭제하였습니다.");
    }
}
