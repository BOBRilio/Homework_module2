import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
public @interface TestId {
    String value();   // номер тест-кейса, например "TC-101"
    int order();      // порядок выполнения в прогоне
}
