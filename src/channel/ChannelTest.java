package channel;

import java.io.IOException;
import java.nio.MappedByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;

/**
 * @ClassName ChannelTest
 * @Description TODO
 * @Author qulingxiao
 * @Date 2020/8/30 10:50
 * @Version 1.0
 */
public class ChannelTest {
    public static void main(String[] args) throws IOException {
        dirTest();
    }

    /**
     * channel,用于源节点和目标节点的连接，在java Nio中负责缓冲区数据的传输，channel本身不存储数据
     * 所以，需要配合缓冲区进行数据传输。
     *
     * java.nio.channles.Channel
     *
     * getChannel()
     */

    public static void dirTest() throws IOException {
        FileChannel inChannel = FileChannel.open(Paths.get("a.jpg"), StandardOpenOption.READ);
        FileChannel outChannel = FileChannel.open(Paths.get("b.jpg"), StandardOpenOption.WRITE, StandardOpenOption.READ, StandardOpenOption.CREATE_NEW);

        MappedByteBuffer inMap = inChannel.map(FileChannel.MapMode.READ_ONLY, 0, inChannel.size());
        MappedByteBuffer outMap = outChannel.map(FileChannel.MapMode.READ_WRITE, 0, inChannel.size());

        byte[] bytes = new byte[1024];
        inMap.get(bytes);
        outMap.put(bytes);

        inChannel.close();
        outChannel.close();


    }
}
