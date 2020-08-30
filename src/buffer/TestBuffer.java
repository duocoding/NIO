package buffer;

import java.nio.ByteBuffer;

/**
 * @ClassName buffer.TestBuffer
 * @Description TODO
 * @Author qulingxiao
 * @Date 2020/8/30 9:36
 * @Version 1.0
 */
public class TestBuffer {

    /**
     * ByteBuffer
     * CharBuffer
     * ShortBuffer
     * IntBuffer
     * LongBuffer
     * FloatBuffer
     * DoubleBuffer
     *
     * @param args
     */
    public static void main(String[] args) {

        new TestBuffer().byteBuffer();
    }

    public void byteBuffer(){

        // 分配指定大小的缓冲区
        ByteBuffer buf = ByteBuffer.allocate(1024);
        System.out.println("--------------allocate-------------");
        System.out.println(buf.position());
        System.out.println(buf.capacity());
        System.out.println(buf.limit());

        // 利用put（）存入数据到缓冲区中
        buf.put("abcde".getBytes());
        System.out.println("----------------put----------------");
        System.out.println(buf.position());
        System.out.println(buf.capacity());
        System.out.println(buf.limit());

        // 切换到读取数据的模式
        buf.flip();
        System.out.println("----------------put----------------");
        System.out.println(buf.position());
        System.out.println(buf.capacity());
        System.out.println(buf.limit());

        // 利用get（）读取数据
        System.out.println("----------------get----------------");
        byte[] bytes = new byte[buf.limit()];
        buf.get(bytes);
        System.out.println(new String(bytes, 0, bytes.length));
        System.out.println(buf.position());
        System.out.println(buf.capacity());
        System.out.println(buf.limit());

        // 可重复读数据
        buf.rewind();
        System.out.println("----------------rewind----------------");
        System.out.println(buf.position());
        System.out.println(buf.capacity());
        System.out.println(buf.limit());

        // 清除数据
        buf.clear();
        System.out.println("----------------clear----------------");
        System.out.println(buf.position());
        System.out.println(buf.capacity());
        System.out.println(buf.limit());
    }

}
