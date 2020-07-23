package viz.commonlib.socket;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;

public class ServerOne extends Thread {
    private Socket s;
    private InputStream in;
    private OutputStream out;
    byte[] buf = new byte[1024 * 2];// 缓冲区大小

    public ServerOne(Socket s) throws IOException {
        this.s = s;
        in = s.getInputStream();
        out = s.getOutputStream();
        start();
    }

    @Override
    public void run() {
        try {
            while (true) {

                in.read(buf);// 读取数据

                String hexStr = HexTool.bytesToHexString(buf);// 将字节数组转成十六进制字符串

                String commandStr = hexStr.substring(0, 8);// 截取到命令

                String sizeHex = hexStr.substring(8, 12);
                int size = Integer.parseInt((sizeHex.substring(2) + sizeHex.substring(0, 2)), 16);
                String bodyData = hexStr.substring(12, 12 + size * 2 + 4);// 截取到报文中的数据部分
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                s.close();
            } catch (IOException e) {
            }
        }
    }
}
