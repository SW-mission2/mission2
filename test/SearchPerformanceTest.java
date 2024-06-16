import java.util.ArrayList;
import java.util.List;
import java.util.Arrays;
import java.util.NoSuchElementException;

public class SearchPerformanceTest {
    public static void main(String[] args) {
        BookManager bm = new BookManager();
        bm.bookstorage = new ArrayList<>();
        
        // Add test instance
        for (int i = 0; i < 10000; i++) {
            bm.addbook(String.valueOf(i), "Test_Title", "Test_Author ", 2024);
        }

        // Test search method
        long startTime = System.nanoTime();
        for (int i = 0; i < 10000; i++) {
            try {
                bm.searchbook(String.valueOf(i));
            } catch (NoSuchElementException e) {	
            	//do not occur in this test case
            }
        }
        long searchEndTime = System.nanoTime();
        long searchDuration = (searchEndTime - startTime) / 1_000_000;
        

        // Convert and sort data for binary search
        int[] sortedArray = bm.convert_to_int_ordered_list(bm.bookstorage);
        
        // Test search_bs method
        startTime = System.nanoTime();
        for (int i = 0; i < 10000; i++) {
            try {
                bm.search_bs(String.valueOf(i), sortedArray);
            } catch (NoSuchElementException e) {
                //do not occur in this test case
            }
        }
        long bsEndTime = System.nanoTime();
        long bsDuration = (bsEndTime - startTime) / 1_000_000;
        
        System.out.println("Execution time of the search method: " + searchDuration  + " ms");
        System.out.println("Execution time of the search_bs method: " + bsDuration + " ms");
    }
}
