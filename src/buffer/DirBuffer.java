package buffer;

import java.nio.ByteBuffer;

/**
 * @ClassName buffer.DirBuffer
 * @Description TODO
 * @Author qulingxiao
 * @Date 2020/8/30 10:21
 * @Version 1.0
 */
public class DirBuffer {
    public static void main(String[] args) {
        bufferTest();
    }

    /**
     * 非直接缓冲区：通过allocate（）方法分配内存，将缓冲区建立在jvm内存中
     * 直接缓冲区：  通过allocateDirect（）方法分配内存，将缓冲区建立在物理内存中，可以提高效率
     */
    public static void bufferTest(){
        ByteBuffer byteBuffer = ByteBuffer.allocateDirect(1024);
        System.out.println(byteBuffer.isDirect());
    }
}
