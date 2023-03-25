package server;

import java.io.*;
import java.net.*;
import java.nio.charset.StandardCharsets;

import static server.Controller.*;

public class EchoServer {
    private static final int port = 23456;
    private static final String address = "127.0.0.1";

    public static void main(String[] args) {
        System.out.println("Server started!");
        try (ServerSocket server = new ServerSocket(port, 50, InetAddress.getByName(address));) {
            while (true) {
                try (Socket socket = server.accept();
                    DataInputStream input = new DataInputStream(socket.getInputStream());
                    DataOutputStream output  = new DataOutputStream(socket.getOutputStream());
                ) {

                    int length = input.readInt();
                    byte[] message = new byte[length];
                    input.readFully(message, 0, message.length);
                    String[] msg =  new String(message, StandardCharsets.UTF_8).split(",");
                    Controller controller = SerializationUtils.deserialize("/home/yulia/IdeaProjects/File Server" +
                            "/File Server/task/src/server/filename.data");
                    //Controller controller = Controller.getInstance();

                    String action = msg[0];
                    String nameOrId = msg.length >= 2? msg[1] : "";
                    String content = msg.length == 3? msg[2] : "";

                    String name = nameOrId.matches("\\d")? controller.getName(Integer.parseInt(nameOrId)) : nameOrId;
                    switch (action) {
                        case "GET":
                            write(controller.getFile(name) + "," + controller.getContent(name),
                                        output);
                            break;
                        case "PUT":
                            String answer = controller.addFile(name, content);
                            write(answer, output);
                            SerializationUtils.serialize(controller,
                                    "/home/yulia/IdeaProjects/File Server/File Server/task/src/server/filename.data");
                            break;
                        case "DELETE":
                            if (controller.deleteFile(name)) {
                                write("200", output);
                            } else {
                                write("404", output);
                            }
                            SerializationUtils.serialize(controller,
                                    "/home/yulia/IdeaProjects/File Server/File Server/task/src/server/filename.data");
                            break;
                        case "exit":
                            SerializationUtils.serialize(controller,
                                    "/home/yulia/IdeaProjects/File Server/File Server/task/src/server/filename.data");
                            System.exit(1);
                    }
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    static void write(String answer, DataOutputStream output) throws IOException {
        output.writeInt(answer.length());
        output.write(answer.getBytes());
    }

}