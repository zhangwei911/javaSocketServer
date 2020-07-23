package viz.commonlib.socket;

import org.springframework.stereotype.Component;

import java.io.IOException;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;

@Component
public class SocketServer {
    public static final int PORT = 2222;// 要监听的端口

    public void run() throws IOException {
        ServerSocket ss = new ServerSocket(PORT);
        InetAddress ia = InetAddress.getByName(null);
        System.out.println("服务端@" + ia + " 已启动!");

        try {
            while (true) {
                Socket s = ss.accept();// listen PORT;
                System.out.println("已开始监听:" + ss.getLocalPort() + "端口");
                try {
                    new ServerOne(s);
                } catch (IOException e) {
                    s.close();
                }
            }
        }finally {
            ss.close();
            System.out.println("服务端已停止!");
        }
    }
}
