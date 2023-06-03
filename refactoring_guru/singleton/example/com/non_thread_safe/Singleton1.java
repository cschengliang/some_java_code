package refactoring_guru.singleton.example.com.non_thread_safe;

//线程安全
public final class Singleton1 {
    private static volatile Singleton1 instance;
    public String value;

    private Singleton1(String value) {
        this.value = value;
    }

    public static Singleton1 getInstance(String value) {
        Singleton1 result = instance;
        if (result != null)
            return result;
        synchronized (Singleton.class) {
            if (instance == null) {
                instance = new Singleton1(value);
            }
            return instance;
        }
    }

}
