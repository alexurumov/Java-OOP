import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import p05_CustomLinkedList.CustomLinkedList;

import static org.junit.Assert.*;

public class CustomLinkedListTests {

    private CustomLinkedList<Integer> list;

    @Before
    public void initialise(){
        this.list = new CustomLinkedList<>();
    }

    // GET

    @Test (expected = IllegalArgumentException.class)
    public void getShouldReturnExeptionWhenListIsEmpty() {
        this.list.get(0);
    }

    @Test (expected = IllegalArgumentException.class)
    public void getShouldReturnExeptionWhenIndexIsNegative() {
        this.list.get(-1);
    }

    @Test (expected = IllegalArgumentException.class)
    public void getShouldReturnExeptionWhenIndexIsOutOfRange() {
        this.list.add(1);
        this.list.add(2);
        this.list.get(10);
    }

    @Test
    public void getShouldCorrectElement() {
        this.list.add(1);
        this.list.add(3);
        this.list.add(5);

        Integer actual = 5;

        assertEquals(actual, list.get(2));
    }

    // ADD

    @Test
    public void addShouldInsertElementAtLastIndex() {
        list.add(5);
        list.add(12);

        Integer actual = 12;

        assertEquals(actual, list.get(1));
    }

    //SET

    @Test (expected = IllegalArgumentException.class)
    public void setShouldThrowExeptionWhenInvalidIndexIsPassed() {
        list.set(12, 5);
    }

    @Test
    public void setShouldSetGivenElementAtCorrectPosition() {
        list.add(1);
        list.add(3);
        list.add(5);
        list.set(2, 7);

        Integer actual = 7;

        assertEquals(actual, list.get(2));
    }

    //REMOVE AT

    @Test (expected = IllegalArgumentException.class)
    public void removeAtShouldThrowExeptionIfIndexIsInvalid(){
        list.removeAt(3);
    }

    @Test
    public void removeAtShouldReturnElementAtGivenIndex(){
        list.add(1);
        list.add(2);
        list.add(3);
        list.add(4);

        Integer actual = 4;

        assertEquals(actual, list.removeAt(3));
    }

    //REMOVE

    @Test
    public void removeShouldReturnNegativeIfNoSuchElement() {
        int actual = -1;

        assertEquals(actual, list.remove(5));
    }

    @Test
    public void removeShouldCorrectElement() {
        list.add(5);
        list.add(10);
        list.add(15);
        list.add(20);

        int actual = 3;

        assertEquals(actual, list.remove(20));
    }

    //INDEX OF

    @Test
    public void indexOfShouldReturnNegativeIfNoSuchElement() {
        list.add(5);
        list.add(10);
        list.add(15);
        list.add(20);

        int actual = -1;

        assertEquals(actual, list.indexOf(1));
    }

    @Test
    public void indexOfShouldReturnCorrectElementIndex() {
        list.add(5);
        list.add(10);
        list.add(15);
        list.add(20);

        int actual = 2;

        assertEquals(actual, list.indexOf(15));
    }

    //CONTAINS

    @Test
    public void containsShouldReturnFalseIfNoSuchElement() {
        assertFalse(list.contains(20));
    }

    @Test
    public void containsShouldReturnTrueIfElementIsContained() {
        list.add(5);
        list.add(10);
        list.add(15);
        list.add(20);

        assertTrue(list.contains(20));
    }



}
