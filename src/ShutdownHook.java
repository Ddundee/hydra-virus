import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Objects;
import java.util.StringJoiner;

public class ShutdownHook {
    public void start(String fileDir) {
        File dir = new File(fileDir);

        Runtime.getRuntime().addShutdownHook(new Thread(() -> {
            int numOfFiles = Objects.requireNonNull(dir.listFiles()).length;
            String fileContent;
            try {
                StringJoiner joiner = new StringJoiner("Hydra" + numOfFiles);
                for(String str : Methods.readJavaFileContent(System.getProperty("user.dir") + "\\src\\Main.java").split("Main")) {
                    joiner.add(str);
                }
                fileContent = joiner.toString();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
            try {
                FileWriter file = new FileWriter(fileDir + "\\Hydra" + numOfFiles + ".java");
                file.write(fileContent);
                file.close();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
            try {
                ProcessBuilder compilerProcessBuilder = new ProcessBuilder("javac", fileDir + "\\Hydra" + numOfFiles + ".java");
                Process compilerProcess = compilerProcessBuilder.start();
                int exitCode = compilerProcess.waitFor();

                if (exitCode == 0) {
                    ProcessBuilder javaProcessBuilder = new ProcessBuilder("java", "-cp", fileDir, "Hydra" + numOfFiles);
                    Process javaProcess = javaProcessBuilder.start();
                } else {
                }
            } catch (IOException | InterruptedException e) {
                throw new RuntimeException(e);
            }
        }));
    }
}
