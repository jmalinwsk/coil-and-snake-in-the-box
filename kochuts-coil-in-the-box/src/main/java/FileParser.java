import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

public class FileParser {
    private final File file;
    private FileWriter fileWriter;
    private BufferedWriter bufferedWriter;

    public FileParser(String path) throws IOException {
        this.file = new File(path);
        createFile();
    }

    public void cleanFile() throws IOException {
        this.fileWriter = new FileWriter(this.file);
        this.bufferedWriter = new BufferedWriter(this.fileWriter);
        bufferedWriter.write("length, details" + System.lineSeparator());
        this.file.delete();
        this.file.createNewFile();
    }

    public void createFile() throws IOException {
        if (this.file.exists()) {
            cleanFile();
        } else {
            File outputCatalog = new File("output");
            if (!outputCatalog.exists())
                outputCatalog.mkdir();
            this.file.createNewFile();
        }

        this.fileWriter = new FileWriter(this.file);
        this.bufferedWriter = new BufferedWriter(this.fileWriter);
        bufferedWriter.write("length, details" + System.lineSeparator());
    }

    public void addToFile(ArrayList<Integer> graph, String type) throws IOException {
        if (type.equals("snake"))
            bufferedWriter.write(graph.size() - 1 + ", ");
        else if (type.equals("coil"))
            bufferedWriter.write(graph.size() + ", ");
        for (Integer c : graph) {
            bufferedWriter.write(c + " ");
        }
        bufferedWriter.write(System.lineSeparator());
    }

    public void closeFile() throws IOException {
        bufferedWriter.close();
    }
}
