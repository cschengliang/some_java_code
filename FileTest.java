import java.io.File;

public class FileTest {

    public static void main(String[] args) {
        File file1 = new File("/Users/chengliang/IdeaProjects/some_java_code/abc");
        File file2 = new File("file","people");
        System.out.println(file1.getAbsolutePath());
    }
}
