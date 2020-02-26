package IOStream;

import java.io.*;
import java.util.InvalidPropertiesFormatException;
import java.util.Scanner;
import java.util.logging.Logger;

public class MyInput {
    public Logger logger = Logger.getLogger("MyInput");

    /**
     * 父类为Reader的输入流，分为两类
     * 节点流
     * 处理流
     */
    private FileReader fileReader;
    private PipedReader pipedReader;
    private CharArrayReader charArrayReader;

    private BufferedReader bufferedReader;
    private InputStreamReader inputStreamReader;

    /**
     * 父类为InputStream的输入流，分为两类
     * 节点流
     * 处理流
     */
    private FileInputStream inputStream;
    private PipedInputStream pipedInputStream;
    private ByteArrayInputStream byteArrayInputStream;

    private BufferedInputStream bufferedInputStream;
    private DataInputStream dataInputStream;
    private ObjectInputStream objectInputStream;
    private SequenceInputStream sequenceInputStream;

    /**
     * InputStream：字节流
     *
     * @return
     */
    public static String inputFirstWay() {
        Scanner input = new Scanner(System.in);
        return input.nextLine();
    }

    /**
     * Reader：字符流
     *
     * @return
     */
    public static String inputSecondWay() throws IOException {
        BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
        return input.readLine();
    }

    /**
     * 文件输出：注意文件的编码格式要为UTF-8，不然会出现乱码
     */
    public String fileReader(String fileName) {
        String fileContent = " ";
        try {
            String strRead = null;
            fileReader = new FileReader(fileName);
            int ch = fileReader.read();
            StringBuilder str = new StringBuilder();
            while (ch != -1) {
                str.append((char) ch);
                ch = fileReader.read();
            }
            fileContent = str.toString();
            System.out.println(fileContent);
        } catch (FileNotFoundException e) {
            logger.warning(e.getMessage());
        } catch (IOException e) {
            logger.warning(e.getMessage());
        } finally {
            if (fileReader != null) {
                try {
                    fileReader.close();

                } catch (IOException e) {
                    throw new RuntimeException("关闭失败");
                }
            }
        }
        return fileContent;
    }

    public String fileInput(String fileName) {
        StringBuilder fileContent = new StringBuilder();
        try {
            inputStream = new FileInputStream(fileName);
            BufferedReader br = new BufferedReader(new InputStreamReader(inputStream, "UTF-8"));
            String str = null;
            while(( str= br.readLine())!=null){
                System.out.println(str);
                fileContent.append(str);
            }        } catch (FileNotFoundException e) {
            logger.warning(e.getMessage());
        } catch (IOException e) {
            logger.warning(e.getMessage());
        } finally {
            try {
                inputStream.close();
            } catch (IOException e) {
                logger.warning(e.getMessage());
            }
        }

        return fileContent.toString();
    }

}
