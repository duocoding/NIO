package block_noblock;

import org.junit.Test;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.Iterator;

/**
 * @ClassName NonBlockingNIO
 * @Description TODO
 * @Author qulingxiao
 * @Date 2020/9/5 9:22
 * @Version 1.0
 */
public class NonBlockingNIO {



    /**
     * 一、使用NIO完成网络通信的三个核心
     *
     * 1、通道
     * 2、缓冲区
     * 3、选择器 （Selector）：是SelectableChannel 的多路复用器，用于监控SelectableChannel的 IO 状况
     */

    @Test
    public void client() throws IOException {
        //1、 获取通道
        SocketChannel socketChannel = SocketChannel.open(new InetSocketAddress("127.0.0.1", 9898));

        //2、 切换成非阻塞模式
        socketChannel.configureBlocking(false);

        //3、 分配指定大小的缓冲区
        ByteBuffer buffer = ByteBuffer.allocate(1024);

        //4、 发送数据到服务端
        buffer.put(new Date().toString().getBytes());
        buffer.flip();
        socketChannel.write(buffer);
        buffer.clear();
        socketChannel.close();
    }

    @Test
    public void server() throws IOException {
        //1、 获取通道
        ServerSocketChannel serverSocketChannel = ServerSocketChannel.open();

        //2、 切换成非阻塞模式
        serverSocketChannel.configureBlocking(false);

        //3、 绑定连接端口号
        serverSocketChannel.bind(new InetSocketAddress(9898));

        //4、 获取选择器
        Selector selector = Selector.open();

        //5、 将通道注册到选择器上面, 并且指定 监听接收事件
        serverSocketChannel.register(selector, SelectionKey.OP_ACCEPT);

        //6、 轮询式的获取选择器上已经 准备就绪的事件
        while (selector.select() > 0){

            //7、 获取当前选择器中左右已经注册的 已经就绪的监听事件
            Iterator<SelectionKey> iterator = selector.selectedKeys().iterator();

            while (iterator.hasNext()){
                //8、 获取准备就绪的事件
                SelectionKey key = iterator.next();

                //9、 判断具体是什么事件准备就绪
                if (key.isAcceptable()){
                    //10、 若接收就绪，获取客户端连接
                    SocketChannel socketChannel = serverSocketChannel.accept();

                    //11、 切换非阻塞模式
                    socketChannel.configureBlocking(false);

                    //12、 将该通道注册到选择器上
                    socketChannel.register(selector, SelectionKey.OP_READ);
                }else if (key.isReadable()){
                    //13、 获取当前选择器上“读就绪”状态的通道
                    SocketChannel socketChannel = (SocketChannel) key.channel();

                    //14、 读取数据
                    ByteBuffer buffer = ByteBuffer.allocate(1024);

                    int len = 0;
                    while ((len = socketChannel.read(buffer))> 0) {
                        buffer.flip();
                        System.out.println(new String(buffer.array(), 0, len));
                    }
                }
            }
        }
    }
}
