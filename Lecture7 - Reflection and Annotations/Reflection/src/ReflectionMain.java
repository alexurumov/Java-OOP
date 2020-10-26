import java.lang.annotation.Annotation;
import java.lang.reflect.Constructor;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.util.*;

public class ReflectionMain {

    public static void main(String[] args) throws ClassNotFoundException, NoSuchMethodException {

        Class reflectionClazz = Class.forName("ReflectionTest");
        System.out.println(reflectionClazz);
        System.out.println(reflectionClazz.getDeclaredConstructors().length);

        Constructor[] constructors = reflectionClazz.getDeclaredConstructors();

        for (Constructor constructor : constructors) {
            Arrays.stream(constructor.getParameterTypes()).forEach(System.out::println);
        }

        System.out.println("-----------------------------------");

        Method[] declaredMethods = reflectionClazz.getDeclaredMethods();

        TreeSet<Method> getterMethods = new TreeSet<>(Comparator.comparing(Method::getName));
        TreeSet<Method> setterMethods = new TreeSet<>(Comparator.comparing(Method::getName));

        for (Method method : declaredMethods) {
            if (method.getName().startsWith("get")) {
                if (method.getParameterCount() == 0) {
                    getterMethods.add(method);
                }
            } else if (method.getName().startsWith("set")) {
                if (method.getParameterCount() == 1) {
                    if (void.class.equals(method.getReturnType())) {
                        setterMethods.add(method);
                    }
                }
            }
        }

        getterMethods.forEach(m -> System.out.println(m.getModifiers() + " " + m));
        setterMethods.forEach(System.out::println);

        System.out.println("-----------------------------------");

        getterMethods.stream()
                .filter(m -> !Modifier.isPublic(m.getModifiers()))
                .forEach(method -> System.out.println(method.getName() + " have to be public!"));

        setterMethods.stream()
                .filter(m -> !Modifier.isPrivate(m.getModifiers()))
                .forEach(method -> System.out.println(method.getName() + " have to be private!"));

        Arrays.stream(reflectionClazz.getDeclaredFields())
                .filter(f -> !Modifier.isPrivate(f.getModifiers()))
                .forEach(f -> System.out.println(f.getName() + " have to be private!"));

        @SuppressWarnings("unchecked")
        Method getName = reflectionClazz.getDeclaredMethod("getName");

        Annotation[] declaredAnnotations = getName.getDeclaredAnnotations();

        for (Annotation annotation : declaredAnnotations) {
            System.out.println(annotation);
        }

        Author author = getName.getAnnotation(Author.class);

        System.out.println(author.name());
    }
}
