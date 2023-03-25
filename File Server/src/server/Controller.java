package server;

import java.io.*;
import java.util.*;

public class Controller implements Serializable {
    private static final Controller controller = new Controller();
    private final Map<Integer, String> map = new HashMap<>();
    private int id;

    private Controller() {}

    public static Controller getInstance() {
        return controller;
    }

    public String addFile(String fileName, String content) throws IOException {
        File file = new File("/home/yulia/IdeaProjects/File Server/" +
                "File Server/task/src/server/data/" + fileName);

        if (!file.exists()) {
            try (FileWriter fileWriter = new FileWriter(file, true)) {
                fileWriter.write(content);
                map.put(id, fileName);

                System.out.println("The file " + fileName + " added successfully");
                return "200," + id++;
            }
        } else {
            return "403";
        }
    }
    public String getFile(String fileName) {
        File file = new File("/home/yulia/IdeaProjects/File Server/" +
                "File Server/task/src/server/data/" + fileName);
        return file.exists()? "200" : "404";
    }

    public String getName(int id) {
        return map.get(id);
    }

    public String getContent(String fileName) {
        File file = new File("/home/yulia/IdeaProjects/File Server/" +
                "File Server/task/src/server/data/" + fileName);
        StringBuilder content = new StringBuilder();
        try (Scanner scanner = new Scanner(file)) {
            if (file.exists()) {
                System.out.println("The file " + fileName + " was sent");
                while (scanner.hasNext()) {
                    content.append(scanner.nextLine());
                }
            } else {
                content = new StringBuilder("");
            }
        } catch (FileNotFoundException ignored) {
        }
        return String.valueOf(content);
    }
    public boolean deleteFile(String fileName) {
        File file = new File("/home/yulia/IdeaProjects/File Server/" +
                "File Server/task/src/server/data/" + fileName);
            if (file.exists()) {

                System.out.println("The file " + fileName + " was deleted");
            } else {
                System.out.println("The file " + fileName + " not found");
            }
            return file.delete();
    }
    private boolean checkFile(String fileName) {
        String regex = "file[1-9]0?$";
        return fileName.matches(regex);
    }
}
