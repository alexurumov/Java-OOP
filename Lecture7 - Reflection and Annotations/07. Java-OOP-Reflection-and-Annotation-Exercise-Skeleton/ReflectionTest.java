import pr0304Barracks.models.units.Archer;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.Arrays;

public class ReflectionTest {

    public static void main(String[] args) throws ClassNotFoundException, IllegalAccessException, InvocationTargetException, InstantiationException {

        Archer archer = new Archer();
        String name = archer.getClass().getName();
        String simpleName = archer.getClass().getSimpleName();

        Class clazz = Class.forName(name);
        System.out.println(clazz);

        System.out.println(clazz.getSuperclass());
        Arrays.stream(clazz.getInterfaces()).forEach(System.out::println);

        Constructor constructor = clazz.getConstructors()[0];

        Archer archer1 = (Archer) constructor.newInstance();

        System.out.println(archer1.getAttackDamage());
    }
}
