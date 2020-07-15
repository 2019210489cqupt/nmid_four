package commun_test;

import java.io.*;
import java.net.Socket;
import java.util.Scanner;

/*
  客户端类实现功能有私聊和群发
  由于题目要求，分析得id应该是客户端的属性
 */
public class chatClient {
    private int id;
    //为方便服务器修改，所以id为public属性

    public static void main(String args[]) throws IOException {
        //1.新建一个Socket对象，明确IP地址和端口号
        Socket client=new Socket("127.0.0.1",8888);

        while(true) {
            System.out.println("请问是1.群发，2.私发");
            //2.选择功能
            Scanner scanner1 = new Scanner(System.in);
            int input1 = scanner1.nextInt();
            if (input1 == 1) {
                /*群发功能
            思路：直接从键盘读取到字符串转化为字节数组后传给服务端，服务端在公屏打出即可
             */
                //输出流功能
                OutputStream aw = client.getOutputStream();
                System.out.println("请输入你想要群发的内容：");
                BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
                String str = null;
                str = br.readLine();
                byte[] bytes = str.getBytes();//bytes是从键盘读取到的字符串转为字节数组
                int len = bytes.length;
                aw.write(bytes, 0, len);//信息以字节形式传出
                client.shutdownOutput();//预防阻塞现象，结束输出流

            } else {
                /*
              私发功能
              1：明确发给谁 2：剩余操作与群发类似
                 */
                InputStream ir = client.getInputStream();
                byte[] bytes1 = new byte[1024];
                int len1 = 0;
                while ((len1 = ir.read(bytes1)) != -1) {
                    System.out.println(new String(bytes1, 0, len1));
                }
            }
            System.out.println("是否退出 1.Yes 2.No");
            Scanner scanner2=new Scanner(System.in);
            int input2=scanner2.nextInt();
            if(input2==1)
            {
                break;
            }
        }


    }

    public void setId(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }
}
