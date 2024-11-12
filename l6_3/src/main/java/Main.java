import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;

public class Main {
    public static void main(String[] args) {
        System.out.println(new Main().readFile("t.txt"));
    }

    public String readFile(String path) {

        try (InputStream resource = getClass().getClassLoader().getResourceAsStream(path)) {
            return new String(resource.readAllBytes());
        } catch (Exception e) {
            throw new RuntimeException();
        }
    }
}