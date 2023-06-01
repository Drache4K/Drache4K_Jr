package main.plugin;

import main.MySQL.mysql;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.UnknownHostException;

public class StringServer {
    /*private final ServerSocket server;
    public StringServer(int port) throws IOException {
        server = new ServerSocket(port);
    }

    private void verbinde(){

        while (true) {
            Socket socket = null;
            try {
                socket = server.accept();
                reinRaus(socket);
            }

            catch (IOException e) {
                e.printStackTrace();
            } finally {
                if (socket != null)
                    try {
                        socket.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
            }
            try {
                Thread.sleep(100);
            }catch (InterruptedException e){

            }
        }
    }

    private void reinRaus(Socket socket) throws IOException {
        BufferedReader rein = new BufferedReader(new InputStreamReader(socket
                .getInputStream()));
        PrintStream raus = new PrintStream(socket.getOutputStream());
        String s = null;

        while(rein.ready()) {
            s = rein.readLine();
        }
            raus.println(mysql.ExecuteMySql(s));


    }

    public static void Start() throws IOException {
        StringServer server = new StringServer(25566);
        server.verbinde();
    }*/
}

