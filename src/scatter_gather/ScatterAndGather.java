package scatter_gather;

import java.io.RandomAccessFile;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;

/**
 * @ClassName ScatterAndGather
 * @Description TODO
 * @Author qulingxiao
 * @Date 2020/8/30 20:46
 * @Version 1.0
 */
public class ScatterAndGather {

    public static void main(String[] args) throws Exception {
        test();
    }

    public static void test() throws Exception{
        RandomAccessFile randomAccessFile1 = new RandomAccessFile("1.txt", "rw");

        //1、获取通道
        FileChannel channel = randomAccessFile1.getChannel();

        //2、分配指定大小的缓冲区
        ByteBuffer allocate = ByteBuffer.allocate(1024);
        ByteBuffer allocate1 = ByteBuffer.allocate(1024);

        //3、分散读取
        ByteBuffer[] byteBuffers = {allocate, allocate1};
        channel.read(byteBuffers);

        for (ByteBuffer byteBuffer : byteBuffers) {
            byteBuffer.flip();
        }

        System.out.println(new String(byteBuffers[0].array(), 0, byteBuffers[0].limit()));
        System.out.println("-----------------------------");
        new String(byteBuffers[1].array(), 0, byteBuffers[1].limit());

        //4、聚集写入

    }
}
