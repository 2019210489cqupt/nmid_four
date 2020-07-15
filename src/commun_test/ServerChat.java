package commun_test;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class ServerChat {

    public static void main (String args[]) throws IOException {
        //启动服务器
        ServerSocket serverSocket = new ServerSocket(8888);
        //进入等待状态
       while (true)
       {
           Socket socket=serverSocket.accept();
           new Thread(new ServerThread(socket)).start();


       }
   }
}
