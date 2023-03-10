package nacos;

import com.alibaba.nacos.common.utils.IoUtils;

import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;

public class NacosServerUDP {
    private static final Charset UTF_8 = StandardCharsets.UTF_8;
    private static final int UDP_MSS = 64 * 1024;

    public static void main(String[] args) throws Exception {
        try (DatagramSocket udpSocket = new DatagramSocket()) {

            while (true) {
                byte[] buffer = new byte[UDP_MSS];
                DatagramPacket packet = new DatagramPacket(buffer, buffer.length);

                udpSocket.receive(packet);

                String json = new String(IoUtils.tryDecompress(packet.getData()), UTF_8).trim();
                System.out.println("received push data: " + json + " from " + packet.getAddress().toString());
            }
        }
    }
}
