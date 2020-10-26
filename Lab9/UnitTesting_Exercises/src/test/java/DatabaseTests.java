import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import p01_Database.Database;

import javax.naming.OperationNotSupportedException;
import java.lang.reflect.Field;

import static org.junit.Assert.*;

public class DatabaseTests {
    public static final Integer[] INITIAL_ELEMENTS = new Integer[]{1, 2, 3};

    private Database database;

    @Before
    public void initialyzeDefaultDb() throws OperationNotSupportedException {
        this.database = new Database(INITIAL_ELEMENTS);
    }

    @Test (expected = OperationNotSupportedException.class)
    public void shouldNotBeAbleToInitializeDbWithLessThanOneElements() throws OperationNotSupportedException {
        Database database = new Database();
    }

    @Test (expected = OperationNotSupportedException.class)
    public void shouldNotBeAbleToInitializeDbWithMoreThanSixteenElements() throws OperationNotSupportedException {
        Database database = new Database(new Integer[17]);
    }

    @Test
    public void shouldSetCorrectElementsCountWhenInitialyzingDb() throws OperationNotSupportedException, NoSuchFieldException, IllegalAccessException {
        Field elementsCount = Database.class.getDeclaredField("elementsCount");

        elementsCount.setAccessible(true);
        int actual = elementsCount.getInt(database);

        assertEquals("Wrong elementsCount, ", INITIAL_ELEMENTS.length, actual);
    }

    @Test (expected = OperationNotSupportedException.class)
    public void addingNullShouldThrowException() throws OperationNotSupportedException {
        database.add(null);
    }

    @Test
    public void addElementShouldIncreaseElementCount() throws OperationNotSupportedException {
        for (int i = 0; i < 3; i++) {
            database.add(i);
        }

        assertEquals(database.getElements().length, INITIAL_ELEMENTS.length + 3);
    }

    @Test
    public void addElementShouldAddCorrectElementInLastIndex() throws OperationNotSupportedException {

        database.add(12);

        assertEquals(database.getElements()[database.getElements().length - 1], Integer.valueOf(12));
    }

    @Test (expected = OperationNotSupportedException.class)
    public void removeShouldThrowExeptionWhenAttemptingToRemoveFromEmptyDb() throws OperationNotSupportedException {
        Database dababase = new Database();
        dababase.remove();
    }

    @Test
    public void removeShouldRemoveLastElementFromDb() throws OperationNotSupportedException {
        database.remove();
        assertEquals(database.getElements().length, INITIAL_ELEMENTS.length - 1);
    }

    @Test
    public void removeShouldRemoveCorrectElement() throws OperationNotSupportedException {
        database.remove();
        assertEquals(database.getElements()[database.getElements().length - 1], INITIAL_ELEMENTS[INITIAL_ELEMENTS.length - 2]);
    }

    @Test
    public void getElementsShouldReturnArrayOfElements() throws OperationNotSupportedException {
        database = new Database(1, 2, 3, 4, 5);
        Integer[] arrayOfElements = new Integer[]{1, 2, 3, 4, 5};

        assertArrayEquals(database.getElements(), arrayOfElements);
    }


}
