package commun_test;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;

public class ServerThread implements Runnable {
    public Socket socket;
    public int id;
    public ServerThread(Socket socket)
    {
        this.socket=socket;
    }
    public void run()
    {
        chatClient c=new chatClient();
        this.id=(int)(1+Math.random()*(50-1+1));
        c.setId(this.id);
        System.out.println("id为"+id+"的用户已经上线");
        try {
            InputStream is=socket.getInputStream();
            int len=0;
            byte[] bytes=new byte[1024];
            while ((len=is.read(bytes))!=-1) {
                System.out.println("id为" + id + "跟大家说：" + new String(bytes, 0, len));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public int getId() {
        return id;
    }
}
