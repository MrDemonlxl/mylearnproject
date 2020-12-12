package pers.lxl.mylearnproject.javase.io.io;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;

/**Java 中的网络支持：

 InetAddress：用于表示网络上的硬件资源，即 IP 地址；
 URL：统一资源定位符；
 Sockets：使用 TCP 协议实现网络通信；
 Datagram：使用 UDP 协议实现网络通信。
 InetAddress
 没有公有的构造函数，只能通过静态方法来创建实例。

 InetAddress.getByName(String host);
 InetAddress.getByAddress(byte[] address);

 Sockets
 ServerSocket：服务器端类
 Socket：客户端类
 服务器和客户端通过 InputStream 和 OutputStream 进行输入输出。

 Datagram
 DatagramSocket：通信类
 DatagramPacket：数据包类*/
public class NetIO {
    public static void main(String[] args) throws IOException {

        URL url = new URL("http://www.baidu.com");

        /* 字节流 */
        InputStream is = url.openStream();

        /* 字符流 */
        InputStreamReader isr = new InputStreamReader(is, "utf-8");

        /* 提供缓存功能 */
        BufferedReader br = new BufferedReader(isr);

        String line;
        while ((line = br.readLine()) != null) {
            System.out.println(line);
        }

        br.close();
    }
}
