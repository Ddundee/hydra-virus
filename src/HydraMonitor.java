import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.lang.management.ManagementFactory;
import java.lang.management.RuntimeMXBean;

public class HydraMonitor {
    public static void main(String[] args) throws IOException {
        System.out.println(isProgramRunning());
    }
    public static boolean isProgramRunning() throws IOException {
        String processName = "process_name";

        ProcessBuilder processBuilder;
        if(System.getProperty("os.name").toLowerCase().contains("win")) processBuilder = new ProcessBuilder("tasklist");
        else processBuilder = new ProcessBuilder("ps", "-e");

        Process process = processBuilder.start();

        try (BufferedReader reader = new BufferedReader(new InputStreamReader(process.getInputStream()))) {
            String line;
            while ((line = reader.readLine()) != null) {
                if (line.contains(processName)) {
                    return true;
                }
            }
        }
        return false;
    }
}
