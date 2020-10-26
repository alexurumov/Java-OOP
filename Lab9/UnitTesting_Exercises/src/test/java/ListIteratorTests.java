import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import p03_IteratorTest.ListIterator;

import javax.naming.OperationNotSupportedException;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static org.junit.Assert.*;

public class ListIteratorTests {

    private ListIterator iterator;

    //INITIALISE DEFAULT OBJECTS

    @Before
    public void initialiseListIterator() throws OperationNotSupportedException {
        iterator = new ListIterator("1", "2", "3");
    }

    //CONSTRUCTOR TESTS

    @Test (expected = OperationNotSupportedException.class)
    public void listIteratorShouldThrowExeptionWhenCreatedWithNullElements() throws OperationNotSupportedException {
        iterator = new ListIterator(null);
    }

    @Test
    public void listIteratorShouldCreateListWithProperInputElements() throws NoSuchFieldException {
        iterator.move();
        String secondElement = iterator.print();

        assertEquals(secondElement, "2");
    }

    //MOVE TESTS

    @Test
    public void moveShouldReturnTrueIfThereIsNextElement() {
        assertTrue(iterator.move());
    }

    @Test
    public void moveShouldReturnFalseIfThereIsNoNextElement() {
        iterator.move();
        iterator.move();
        assertFalse(iterator.move());
    }

    //HAS NEXT TESTS

    @Test
    public void hasNextShouldReturnTrueIfThereIsNextElement() {
        assertTrue(iterator.hasNext());
    }

    @Test
    public void hasNextShouldReturnFalseIfThereIsNoNextElement() {
        iterator.move();
        iterator.move();
        assertFalse(iterator.hasNext());
    }

    //PRINT TESTS

    @Test (expected = IllegalStateException.class)
    public void printShoudThrowExeptionIfListIsEmpty() throws OperationNotSupportedException {
        iterator = new ListIterator();
        iterator.print();
    }

    @Test
    public void printShoudReturnElementAtCurrentIndex() {
        iterator.move();
        iterator.move();
        String thirdElement = iterator.print();
        assertEquals(thirdElement, "3");
    }

}
