package client;

import java.io.*;
import java.net.InetAddress;
import java.net.Socket;
import java.nio.charset.StandardCharsets;
import java.util.Scanner;

public class EchoClient {
    private static final String address = "127.0.0.1";
    private static final int port = 23456;

    public static void main(String[] args) {
        try (
                Socket socket = new Socket(InetAddress.getByName(address), port);
                DataInputStream input = new DataInputStream(socket.getInputStream());
                DataOutputStream output = new DataOutputStream(socket.getOutputStream());
        ) {
            Scanner scanner = new Scanner(System.in);

            System.out.println("Enter action (1 - get a file, " +
                    "2 - save a file, 3 - delete a file): ");

            String actionNum = scanner.nextLine();
            String stringAction = actionNum.equals("1")? "GET" : actionNum.equals("2")?
                    "PUT" :  actionNum.equals("3")? "DELETE" : "exit";

            switch (stringAction) {
                case "GET":

                    System.out.println("Do you want to get the file by " +
                            "name or by id (1 - name, 2 - id): ");
                    String nameOrId = scanner.nextLine();
                    if (nameOrId.equals("2")) {
                        System.out.println("Enter id: ");
                        int id = Integer.parseInt(scanner.nextLine());

                        write("GET," + id, output);
                    } else {
                        System.out.println("Enter filename: ");
                        String name = scanner.nextLine();

                        write("GET," + name, output);
                    }


                    String[] msg = read(input).split(","); // code + content
                    String code = msg[0];
                    String content = msg.length == 2? msg[1] : "";
                    if (code.equals("200")) {
                        System.out.println("The file was downloaded! Specify a name for it: ");
                        String nameOfFile = scanner.nextLine();
                        writeFile(nameOfFile, content);
                        System.out.println("File saved on the hard drive!");
                    } else if (code.equals("404")) {
                        System.out.println("The response says that this file is not found!");
                    }
                    break;
                case "PUT":
                    System.out.println("Enter name of the file: ");
                    String name = scanner.nextLine();

                    System.out.println("Enter name of the file to be saved on server: ");
                    String nameOnServer = scanner.nextLine();
                    nameOnServer = nameOnServer.equals("")? name : nameOnServer;


                    write("PUT," + nameOnServer + "," + readContents(name), output);

                    String[] ans = read(input).split(","); // code + id
                    if (ans[0].equals("200")) {
                        System.out.println("Response says that file is saved! ID = " + ans[1]);
                    } else if (ans[0].equals("403")) {
                        System.out.println("The response says that creating the file was forbidden!");
                    }

                    break;
                case "DELETE":
                    System.out.println("Do you want to get the file by " +
                            "name or by id (1 - name, 2 - id): ");
                    nameOrId = scanner.nextLine();
                    if (nameOrId.equals("2")) {
                        System.out.println("Enter id: ");
                        int id = scanner.nextInt();

                        write("DELETE," + id, output);
                    } else {
                        System.out.println("Enter filename: ");
                        name = scanner.nextLine();

                        write("DELETE," + name, output);
                    }

                    String answer = read(input);

                    if (answer.equals("200")) {
                        System.out.println("The response says that the file was deleted successfully!");
                    } else if (answer.equals("404")) {
                        System.out.println("The response says that the file was not found!");
                    }


                    break;
                case "exit":
                    write("exit", output);
                    System.exit(1);
                    break;
            }


        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    static void write(String msg, DataOutputStream output) throws IOException {
        byte[] message = (msg).getBytes(StandardCharsets.UTF_8);
        output.writeInt(message.length);
        output.write(message);
        System.out.println("The request was sent.");
    }
    static String read(DataInputStream input) throws IOException {
        int length = input.readInt();
        byte[] message = new byte[length];
        input.readFully(message, 0, message.length);
        return new String(message, StandardCharsets.UTF_8);


    }
    static String readContents(String name) {
        File file = new File("/home/yulia/IdeaProjects/File Server/" +
                "File Server/task/src/client/data/" + name);
        StringBuilder content = new StringBuilder();
        try (Scanner scanner1 = new Scanner(file)) {
            while (scanner1.hasNext()) {
                content.append(scanner1.nextLine());
            }
            return String.valueOf(content);

        } catch (FileNotFoundException e) {
            return ""; // the file was not found, but created!
        }

    }

    static void writeFile(String nameOfFile, String content) {
        File file = new File("/home/yulia/IdeaProjects/File Server/" +
                "File Server/task/src/client/data/" + nameOfFile);
        try (FileWriter fileWriter = new FileWriter(file, true)) {
            fileWriter.write(content);
        } catch (IOException ignored) {}
    }
}