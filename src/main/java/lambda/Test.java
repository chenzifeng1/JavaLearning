package lambda;

import java.util.UUID;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.function.Supplier;

public class Test {
    public static void main(String args[]) {
        MyService service = str -> System.out.println(str);
        MyService service1 = System.out::println;

        service.print("hello world");
        service1.print("hello new world");

        //随机的唯一ID
        String str2 = get(() -> UUID.randomUUID().toString());
        System.out.println(str2);

        boolean result = startWith("hello world",str ->str.startsWith("h"));
        System.out.println(result);
    }

    public static String apply(String str, Function<String, String> function) {
        return function.apply(str);
    }

    public static String get(Supplier<String> supplier) {
        return supplier.get();
    }

    public static boolean startWith(String str, Predicate<String> stringPredicate){
        return stringPredicate.test(str);
    }
}
