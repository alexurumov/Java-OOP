import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import p02_ExtendedDatabase.Database;
import p02_ExtendedDatabase.Person;

import javax.naming.OperationNotSupportedException;
import java.lang.reflect.Field;

import static org.junit.Assert.*;

public class DatabaseExtendedTests {
    public static final int DEFAULT_PEOPLE_ADDED = 3;
    private Database db;
    private Person[] people;
    private Person[] duplicatePeople;

    @Before
    public void initializeDefaultObjects() throws OperationNotSupportedException {
        Person person1 = new Person(1, "Alex");
        Person person2 = new Person(2, "George");
        Person person3 = new Person(3, "Nick");
        people = new Person[]{person1, person2, person3};

        duplicatePeople = new Person[]{
                new Person(1, "Alex"),
                new Person(1, "Alex"),
                new Person(2, "George")};

        db = new Database(people);

    }

    //CONSTRUCTORS TEST

    @Test (expected = OperationNotSupportedException.class)
    public void extendedDbShouldThrowExceptionWhenAttemptingToInitialyseWithLessThanOnePeople() throws OperationNotSupportedException {
        db = new Database();
    }

    @Test (expected = OperationNotSupportedException.class)
    public void extendedDbShouldThrowExceptionWhenAttemptingToInitialyseWithMoreThanSixteenPeople() throws OperationNotSupportedException {
        Person[] extraPeople = new Person[17];

        db = new Database(extraPeople);
    }

    @Test
    public void extendedDbShouldSetCorrectPeopleCountWhenInitialyzing() throws NoSuchFieldException, IllegalAccessException {
        Field elementsCount = Database.class.getDeclaredField("elementsCount");
        elementsCount.setAccessible(true);

        int actual = elementsCount.getInt(db);

        assertEquals(DEFAULT_PEOPLE_ADDED, actual);
    }

    //ADD TEST

    @Test (expected = OperationNotSupportedException.class)
    public void addShouldThrowExeptionWhenAttemptingToAddNullPerson() throws OperationNotSupportedException {
        Person person = null;
        db.add(person);
    }

    @Test
    public void addShouldAddPersonInLastIndex() throws OperationNotSupportedException {
        db.add(new Person(4, "Pesho"));
        assertEquals(db.getElements().length, DEFAULT_PEOPLE_ADDED + 1);
    }

    //REMOVE TEST

    @Test
    public void removeShouldRemovePersonAtLastIndex() throws OperationNotSupportedException {
        db.remove();
        assertEquals(db.getElements().length, DEFAULT_PEOPLE_ADDED - 1);
    }

    @Test (expected = OperationNotSupportedException.class)
    public void removeShouldThrowExeptionIfCalledOnEmptyDb() throws OperationNotSupportedException {
        db.remove();
        db.remove();
        db.remove();
        db.remove();
    }

    //FIND BY USERNAME TEST

    @Test (expected = OperationNotSupportedException.class)
    public void findByUsernameShouldThrowExceptionIfNoSuchUserInDb() throws OperationNotSupportedException {
        db.findByUsername("Pesho");
    }

    @Test (expected = OperationNotSupportedException.class)
    public void findByUsernameShouldThrowExceptionIfUsernameParamIsNull() throws OperationNotSupportedException {
        db.findByUsername(null);
    }

    @Test (expected = OperationNotSupportedException.class)
    public void findByUsernameShouldThrowExceptionIfUsernameParamIsCaseDifferent() throws OperationNotSupportedException {
        db.findByUsername("ALEX");
    }

    @Test (expected = OperationNotSupportedException.class)
    public void findByUsernameShouldThrowExeptionIfThereAreMultipleUsernamesAdded() throws OperationNotSupportedException {
        db = new Database(duplicatePeople);
        db.findByUsername("Alex");
    }

    @Test
    public void findByUsernameShouldRetrieveCorrectPerson() throws OperationNotSupportedException {
        Person actual = people[0];
        Person personToFind = db.findByUsername("Alex");
        assertEquals(personToFind, actual);
    }

    //FIND BY ID TEST

    @Test (expected = OperationNotSupportedException.class)
    public void findByIdShouldThrowExeptionIfThereAreMultipleIdsAdded() throws OperationNotSupportedException {
        db = new Database(duplicatePeople);
        db.findById(1);
    }

    @Test (expected = OperationNotSupportedException.class)
    public void findByIdShouldThrowExeptionIfNoSuchId() throws OperationNotSupportedException {
        db.findById(4);
    }

}
