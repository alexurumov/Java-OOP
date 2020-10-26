import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;
import static p04_BubbleSortTest.Bubble.*;

public class BubbleSortTests {

    @Test
    public void bubbleSortShouldReturnSortedCollection() {
        int[] arr = new int[]{2, 1, 5, 4, 3};
        sort(arr);

        int[] actual = new int[]{1, 2, 3, 4, 5};

        assertArrayEquals(actual, arr);
    }

    @Test
    public void bubbleSortShouldReturnSameArrWhenInputParamIsSortedArr() {
        int[] arr = new int[]{1, 2, 3, 4};
        sort(arr);

        int[] actual = new int[]{1, 2, 3, 4};

        assertArrayEquals(actual, arr);
    }

    @Test
    public void bubbleSortShouldReturnEmptyArrWhenInputParamIsEmptyArr() {
        int[] arr = new int[5];
        sort(arr);

        int[] actual = new int[5];

        assertArrayEquals(actual, arr);
    }
}
