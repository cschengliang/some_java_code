package refactoring_guru.singleton.example.com.non_thread_safe;

//相同的类在多线程环境中会出错。 多线程可能会同时调用构建方法并获取多个单例类的实例。多线程可能会同时调用构建方法并获取多个单例类的实例
public class DemoMultiThread {
    public static void main(String[] args) {
        System.out.println("-");
        Thread threadFoo = new Thread(new ThreadFoo());
        Thread threadBar = new Thread(new ThreadBar());
        threadFoo.start();
        threadBar.start();

    }

    static class ThreadFoo implements Runnable {
        @Override
        public void run() {
            Singleton singleton = Singleton.getInstance("FOO");
            System.out.println(singleton.value);
        }
    }

    static class ThreadBar implements Runnable {
        @Override
        public void run() {
            Singleton singleton = Singleton.getInstance("BAR");
            System.out.println(singleton.value);
        }
    }
}
