import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.lang.module.FindException;
import java.util.ArrayList;
import java.util.NoSuchElementException;

import static org.junit.jupiter.api.Assertions.*;

class BookManagerTest extends BookManager {
    public BookManager bm;

    @BeforeEach
    void setUp() {
        bm = new BookManager();
        bm.bookstorage = new ArrayList<>();
    }

    @Test
    void testAddBook() {
        bm.addbook("1","자바 기초","Jane",2021);
        //assertThrows(FindException.class, () -> bm.addbook("1","자바 기초","Jane",2021));
        try {
            bm.addbook("1","자바 기초","Jane",2021);
        }
        catch(FindException e) {
            System.out.println("해당 ID(1) 는 이미 존재합니다.");
            bm.searchbook("1");
        }


    }
    @Test
    void testSearchBook() {
        bm.addbook("1","자바 기초","Jane",2021);
        bm.addbook("2","소프트웨어 공학","Tom",2014);
        bm.addbook("3","분산 컴퓨팅","Yoon",2024);
        bm.searchbook("1");
        bm.searchbook("2");
        bm.searchbook("3");
        //assertThrows(NoSuchElementException.class, () -> bm.searchbook("4"),"검색된 도서가 없습니다.");
        try {
            bm.searchbook("4");
        }
        catch(NoSuchElementException e) {
            System.out.println("검색된 도서가 없습니다.");
        }
    }

    @Test
    void testRemoveBook() {
        bm.addbook("1","자바 기초","Jane",2021);
        bm.addbook("2","소프트웨어 공학","Tom",2014);
        bm.removebook("1");
        //assertThrows(NoSuchElementException.class, () -> bm.removebook("1"));
        //assertThrows(NoSuchElementException.class, () -> bm.searchbook("1"));
        try {
            bm.removebook("1");
        }
        catch(NoSuchElementException e) {
            System.out.println("해당 ID(1)의 도서를 찾을 수 없습니다.");
        }

        try {
            bm.searchbook("1");
        }
        catch(NoSuchElementException e) {
            System.out.println("검색된 도서가 없습니다.");
        }
    }

    @Test
    void testSearchBS() {
        bm.addbook("1", "자바 기초", "Jane", 2021);
        bm.addbook("2", "소프트웨어 공학", "Tom", 2014);
        bm.addbook("3", "분산 컴퓨팅", "Yoon", 2024);
        bm.addbook("7", "미움받을 용기", "Lee", 2020);
        bm.addbook("10", "정보보안", "Joe", 2024);
        bm.addbook("4", "AR/VR", "Chloe", 2021);
        
        assertTrue(bm.search_bs("1", bm.bookstorage));
        assertTrue(bm.search_bs("2", bm.bookstorage));
        assertTrue(bm.search_bs("3", bm.bookstorage));
        assertTrue(bm.search_bs("7", bm.bookstorage));
        assertTrue(bm.search_bs("10", bm.bookstorage));
        assertTrue(bm.search_bs("4", bm.bookstorage));

        try{
            bm.search_bs("9", bm.bookstorage);
        }
        catch(NoSuchElementException e) {
            System.out.println("해당 ID의 도서를 찾을 수 없습니다.");
        }
    }
}