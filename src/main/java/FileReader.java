import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

public class FileReader {
    static Path file = Paths.get("classes.csv");

    public void init() {
        if (Files.exists(file)) {
            System.out.println("Classes file init-ed!");
        } else {
            System.out.println("Classes file didn't init-ed.\n" +
                    "creating empty file...");
            try {
                Files.createFile(file);
            } catch (IOException e) {
                e.printStackTrace();
            }
            System.out.println("Please, fill this file, or this servlet\n" +
                    "will not have a sence. Please.");
        }
    }

    public List<String> getLines() {
        try {
            return Files.readAllLines(file);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
}