package example.java.io;


import java.io.*;

public class FileServlet {

    /**
     * 以字节为单位读取文件，常用于读二进制文件，如图片、声音、影像等文件
     * @param filePath
     * @return
     * @throws IOException
     */
    public static byte[] readFileByBytes(String filePath) {
        File file = new File(filePath);
        if (file.isFile() && file.exists()) {
            try (FileInputStream fileInputStream = new FileInputStream(file);
                 ByteArrayOutputStream outputStream = new ByteArrayOutputStream();) {
                int index;
                byte[] byteBlock = new byte[BYTE_SIZE];
                while ((index = fileInputStream.read(byteBlock)) != -1) {
                    outputStream.write(byteBlock, 0, index);
                }
                return outputStream.toByteArray();
            } catch (IOException e) {
                return null;
            }
        }
        return null;
    }

    /**
     * 以行为单位读取文件，常用于读文本，数字等类型的文件
     * @param filePath
     * @return
     */
    public static String readFileByLine(String filePath) {
        File file = new File(filePath);
        if (file.isFile() && file.exists()) {
            try (FileInputStream fileInputStream = new FileInputStream(file);
                 InputStreamReader inputStreamReader = new InputStreamReader(fileInputStream);
                 BufferedReader bufferedReader = new BufferedReader(inputStreamReader);) {

                StringBuilder stringBuilder = new StringBuilder();
                String text = null;
                while ((text = bufferedReader.readLine()) != null) {
                    stringBuilder.append(text).append("\n");
                }
                return stringBuilder.toString();
            } catch (IOException e) {
                return null;
            }
        }
        return null;
    }

    /**
     * 以字节为单位读取文件，常用于读文本，数字等类型的文件
     * @param filePath
     * @return
     */
    public static String readFileByChar(String filePath) {
        File file = new File(filePath);
        if (file.isFile() && file.exists()) {
            try (FileInputStream fileInputStream = new FileInputStream(file);
                 InputStreamReader inputStreamReader = new InputStreamReader(fileInputStream);) {

                StringBuilder stringBuilder = new StringBuilder();
                int index = 0;
                while (-1 != (index = inputStreamReader.read())) {
                    stringBuilder.append((char) index);
                }
                return stringBuilder.toString();
            } catch (IOException e) {
                return null;
            }
        }
        return null;
    }


    /**
     * 写入字节流
     *
     * @param path
     * @param fileName
     * @param content
     * @return
     * @throws IOException
     */
    public static boolean writeFileByFileOutputStream(String path, String fileName, byte[] content) throws IOException {
        File file = new File(String.format("%s/%s", path, fileName));
        //判断文件是否存在，如果不存在就新建一个txt
        if (!file.exists()) {
            file = createFile(path, fileName);
            if (file == null) {
                return false;
            }
        }

        try (FileOutputStream fileOutputStream = new FileOutputStream(file);
             BufferedOutputStream bufferedOutputStream = new BufferedOutputStream(fileOutputStream)) {
            bufferedOutputStream.write(content);
            bufferedOutputStream.flush();
            return true;
        } catch (Exception e) {
            throw e;
        }
    }

    /**
     * 写入字符流
     *
     * @param path
     * @param fileName
     * @param content
     * @return
     * @throws IOException
     */
    public static boolean writeFileByOutputStreamWriter(String path, String fileName, String content) throws IOException {
        return writeFileByOutputStreamWriter(path, fileName, content, CHAESET_NAME);
    }

    /**
     * 写入字符流
     *
     * @param path
     * @param fileName
     * @param content
     * @param charsetName
     * @return
     * @throws IOException
     */
    public static boolean writeFileByOutputStreamWriter(String path, String fileName, String content, String charsetName) throws IOException {
        File file = new File(String.format("%s/%s", path, fileName));
        //判断文件是否存在，如果不存在就新建一个txt
        if (!file.exists()) {
            file = createFile(path, fileName);
            if (file == null) {
                return false;
            }
        }

        try (FileOutputStream fileOutputStream = new FileOutputStream(file);
             OutputStreamWriter outputStreamWriter = new OutputStreamWriter(fileOutputStream, charsetName)) {
            outputStreamWriter.write(content);
            outputStreamWriter.flush();
            return true;
        } catch (Exception e) {
            throw e;
        }
    }

    /**
     * 写入字符流（带缓存），推荐用法
     *
     * @param path
     * @param fileName
     * @param content
     * @return
     * @throws IOException
     */
    public static boolean writeFileByBufferedWriter(String path, String fileName, String content) throws IOException {
        return writeFileByBufferedWriter(path, fileName, content, CHAESET_NAME);
    }

    /**
     * 写入字符流（带缓存），推荐用法
     *
     * @param path
     * @param fileName
     * @param content
     * @param charsetName
     * @return
     * @throws IOException
     */
    public static boolean writeFileByBufferedWriter(String path, String fileName, String content, String charsetName) throws IOException {
        File file = new File(String.format("%s/%s", path, fileName));
        //判断文件是否存在，如果不存在就新建一个txt
        if (!file.exists()) {
            file = createFile(path, fileName);
            if (file == null) {
                return false;
            }
        }

        try (FileOutputStream fileOutputStream = new FileOutputStream(file);
             OutputStreamWriter outputStreamWriter = new OutputStreamWriter(fileOutputStream, charsetName);
             BufferedWriter bufferedWriter = new BufferedWriter(outputStreamWriter)) {
            bufferedWriter.write(content);
            bufferedWriter.flush();
            return true;
        } catch (Exception e) {
            throw e;
        }
    }

    /**
     * 写入字符流（带缓存），不能指定编码集
     *
     * @param path
     * @param fileName
     * @param content
     * @return
     * @throws IOException
     */
    public static boolean writeFileByFileWriter(String path, String fileName, String content) throws IOException {
        File file = new File(String.format("%s/%s", path, fileName));
        //判断文件是否存在，如果不存在就新建一个txt
        if (!file.exists()) {
            file = createFile(path, fileName);
            if (file == null) {
                return false;
            }
        }

        try (FileWriter fileWriter = new FileWriter(file)) {
            fileWriter.write(content);
            fileWriter.flush();
            return true;
        } catch (Exception e) {
            throw e;
        }
    }

    private static File createFile(String path, String fileName) throws IOException {
        if (path == null || path.isEmpty()) {
            return null;
        }
        if (fileName == null || fileName.isEmpty()) {
            return null;
        }
        //目录不存在则创建目录
        File dir = new File(path);
        if (!dir.isDirectory()) {
            if (!dir.mkdirs()) {
                return null;
            }
        }
        //文件不存在则创建文件
        File file = new File(String.format("%s/%s", path, fileName));
        if (!file.exists()) {
            if (!file.createNewFile()) {
                return null;
            }
        }
        return file;
    }

    private static final int BYTE_SIZE = 1024;

    private static final String CHAESET_NAME = "UTF-8";


}
