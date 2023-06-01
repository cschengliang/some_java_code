package refactoring_guru.singleton.example.com.non_thread_safe;

public class DemoSingleThread {
    public static void main(String[] args) {
        System.out.println("==");
        Singleton singleton = Singleton.getInstance("FOO");
        Singleton anotherSingleton = Singleton.getInstance("BAR");
        System.out.println(singleton.value);
        System.out.println(anotherSingleton.value);
    }
}
