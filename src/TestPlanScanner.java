import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class TestPlanScanner {

    public record  TestStep(String id, String methodName, int order){}

    public static List<TestStep> buildPlan(Class<?> clazz) {
         List<TestStep> plan = new ArrayList<>();
        Method[] methods = clazz.getDeclaredMethods();// getDeclaratedMethods возвращает массив Method'ов

        for (Method method : methods) {

            if (method.isAnnotationPresent(TestId.class)) {// проверка на наличие аннотации
                TestId annotation = method.getAnnotation(TestId.class);// получаем аннотацию

                TestStep step = new TestStep(//новый объект с параметрами
                        annotation.value(),
                        method.getName(),
                        annotation.order()
                );
                plan.add(step);// собираем
            }
        }
        plan.sort(Comparator.comparing(TestStep::order));// сортировка по ордеру
        return plan;
    }
    private static void printPlan(Class<?> clazz) {
        List<TestStep> plan = buildPlan(clazz);
        System.out.println("План прогона для " + clazz.getSimpleName() + ":");

        if (plan.isEmpty()) {
            System.out.println("  Кейсов нет");
            return;
        }

        for (TestStep step : plan) {
            System.out.println("    " + step.order() + ". " + step.id() + " → " + step.methodName());
        }
    }
    public static void main(String[] args) {
        printPlan(Checklist.class);

    }
}


