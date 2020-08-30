package charset;

import java.nio.ByteBuffer;
import java.nio.CharBuffer;
import java.nio.charset.CharacterCodingException;
import java.nio.charset.Charset;
import java.nio.charset.CharsetDecoder;
import java.nio.charset.CharsetEncoder;
import java.util.Map;
import java.util.Set;
import java.util.SortedMap;

/**
 * @ClassName CodeAndDecode
 * @Description TODO
 * @Author qulingxiao
 * @Date 2020/8/30 21:52
 * @Version 1.0
 */
public class CodeAndDecode {
    public static void main(String[] args) throws CharacterCodingException {
//        test01();
        test02();
    }

    public static void test01(){
        SortedMap<String, Charset> stringCharsetSortedMap = Charset.availableCharsets();

        Set<Map.Entry<String, Charset>> entries = stringCharsetSortedMap.entrySet();

        for (Map.Entry<String, Charset> entry : entries) {
            System.out.println(entry.getKey() + "==" + entry.getValue());
        }

    }

    public static void test02() throws CharacterCodingException {
        Charset gbk = Charset.forName("GBK");

        CharsetDecoder charsetDecoder = gbk.newDecoder();
        CharsetEncoder charsetEncoder = gbk.newEncoder();

        CharBuffer allocate = CharBuffer.allocate(1024);

        allocate.put("你好，世界");
        allocate.flip();

        ByteBuffer encode = charsetEncoder.encode(allocate);
        for (int i = 0; i < 10; i++) {
            System.out.println(encode.get());
        }

        encode.flip();
        CharBuffer decode = charsetDecoder.decode(encode);
        System.out.println(decode.toString());


    }
}
