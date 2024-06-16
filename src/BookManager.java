import java.lang.module.FindException;
import java.util.ArrayList;
import java.util.Arrays;
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

    // 기본 탐색 함수
    public void searchbook(String id) {
        Book book = bookstorage.stream().filter(x -> x.getId().equals(id)).findFirst().orElseThrow(() -> new NoSuchElementException("검색된 도서가 없습니다."));
        System.out.println("검색 결과:\n"+book.printbook());
    }
    public void removebook(String id) {
        Book book = bookstorage.stream().filter(x -> x.getId().equals(id)).findFirst().orElseThrow(() -> new NoSuchElementException("해당 ID("+id+")의 도서를 찾을 수 없습니다."));
        bookstorage.remove(book);
        System.out.println(book.printbook()+"도서를 삭제하였습니다.");
    }

    // List<Book> 타입으로 저장된 bookstoarge를 int[] 타입으로 변환하는 함수
    // 책 고유 id를 integer로 저장
    // 정렬도 해당 method에서 하도록 추가함
    public int[] convert_to_int_ordered_list(List<Book> bookstorage) {
        int[] intArray = new int[bookstorage.size()];

        for (int i = 0; i < bookstorage.size(); i++) {
            intArray[i] = Integer.parseInt(bookstorage.get(i).getId());
        }
        Arrays.sort(intArray);
        return intArray;
    }

    // 책 고유 id로 이진탐색하는 함수
    // 반복문 방식으로 구현
    public boolean search_bs(String target_id, int[] sortedArray) {//인자 'List<Book>타입 bookstorage' 에서 'int[]타입 sortedArray'로 변경
    	int left = 0;
        int right = bookstorage.size() - 1;
        int mid;
        //int [] intArray = convert_to_int_list(bookstorage);
        int wrapped_target_id = Integer.parseInt(target_id);

        /*
        이미 정렬된 배열을 인자로 받았음
        -------
        // 이진탐색 전, 배열 오름차순 정렬
        Arrays.sort(intArray); 
        -------
        */

        // 이진 탐색
        while (left <= right) {
            mid = (left + right) / 2;
            if (sortedArray[mid] < wrapped_target_id)
                left = mid + 1;
            else if (sortedArray[mid] > wrapped_target_id)
                right = mid - 1;
            else {
                // target_id 찾음
                System.out.println("==이진 탐색==");
                System.out.println("검색 결과: ID " + sortedArray[mid] + " 도서 찾음");
                return true;
            }
        }
        // target_id가 bookstoarge 리스트에 존재하지 않는 경우
        throw new NoSuchElementException("해당 ID의 도서를 찾을 수 없습니다.");
    }
}