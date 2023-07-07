import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class Methods {
    public static String readJavaFileContent(String filepath) throws IOException {
        Path path = Paths.get(filepath);
        byte[] bytes = Files.readAllBytes(path);
        return new String(bytes);
    }
}
