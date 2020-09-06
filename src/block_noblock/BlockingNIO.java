package block_noblock;

import org.junit.Test;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;

/**
 * @ClassName BlockingNIO
 * @Description TODO
 * @Author qulingxiao
 * @Date 2020/9/1 21:55
 * @Version 1.0
 */
public class BlockingNIO {

    /**
     * 一、使用NIO完成网络通信的三个核心
     *
     * 1、通道
     * 2、缓冲区
     * 3、选择器 （Selector）：是SelectableChannel 的多路复用器，用于监控SelectableChannel的 IO 状况
     *
     * @param args
     */
    public static void main(String[] args) throws IOException, InterruptedException {
//        server();
//        Thread.sleep(1000);
//        client();
//        return;
    }

    @Test
    public void client() throws IOException {
        //1、 获取通道
        SocketChannel channel = SocketChannel.open(new InetSocketAddress("127.0.0.1", 9898));

        FileChannel inChannle = FileChannel.open(Paths.get("1.txt"), StandardOpenOption.READ);

        //2、 分配指定大小的缓冲区
        ByteBuffer buffer = ByteBuffer.allocate(1024);

        //3、 从本地读取文件，并发送到远程服务器
        while (inChannle.read(buffer) != -1){
            buffer.flip();
            channel.write(buffer);
            buffer.clear();
        }

        //4、关闭通道
        channel.close();
        inChannle.close();
    }

    @Test
    public void server() throws IOException {
        //1、 获取通道
        ServerSocketChannel socketChannel = ServerSocketChannel.open();

        FileChannel outChannel = FileChannel.open(Paths.get("2.txt"), StandardOpenOption.WRITE, StandardOpenOption.CREATE);

        //2、 绑定连接端口号
        socketChannel.bind(new InetSocketAddress(9898));

        //3、 获取客户端连接通道
        SocketChannel sChannel = socketChannel.accept();

        //4、 分配指定大小的缓冲区
        ByteBuffer buffer = ByteBuffer.allocate(1024);

        //5、 接收客户端数据, 并保存到本地
        while (sChannel.read(buffer) != -1) {
            buffer.flip();
            outChannel.write(buffer);
            buffer.clear();
        }

        //6、关闭通道
        sChannel.close();
        outChannel.close();
        socketChannel.close();
    }
}
