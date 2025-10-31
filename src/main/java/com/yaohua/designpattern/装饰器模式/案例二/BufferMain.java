package com.yaohua.designpattern.装饰器模式.案例二;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.time.Instant;

/**
 * 文件名：BufferMain
 * 作者：huahua
 * 时间：2025/10/31 21:51
 * 描述 改进后的：
 */
public class BufferMain {

    public static void main(String[] args) {
        File file = new File("test.rar");
        long l = Instant.now().toEpochMilli();
        try (InputStream fileInputStream = new BufferFileInputStream(new FileInputStream(file))) {
            while (true) {
                //read()函数每次调用，都会进行一次IO操作；而且每次只读取一个字节；
                int read = fileInputStream.read();
                if (read == -1){
                    break;
                }
            }
            System.out.println("耗时：" + (Instant.now().toEpochMilli() - l));
        } catch (Exception e) {

        }
    }
}
