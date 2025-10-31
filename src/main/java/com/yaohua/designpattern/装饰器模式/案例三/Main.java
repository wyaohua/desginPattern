package com.yaohua.designpattern.装饰器模式.案例三;

import com.yaohua.designpattern.装饰器模式.案例二.BufferFileInputStream;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.time.Instant;

/**
 * 文件名：Main
 * 作者：huahua
 * 时间：2025/10/31 22:00
 * 描述  在案例二的基础上，包装BufferFileInputStream ，增加功能 调用了read多少次
 */
public class Main {

    public static void main(String[] args) {

        File file = new File("test.rar");
        long l = Instant.now().toEpochMilli();
        try (BufferFileInputStreamForCount fileInputStream = new BufferFileInputStreamForCount(new BufferFileInputStream(new FileInputStream(file)))) {
            while (true) {
                //read()函数每次调用，都会进行一次IO操作；而且每次只读取一个字节；
                int read = fileInputStream.read();
                if (read == -1) {
                    break;
                }
            }
            System.out.println(fileInputStream.getCount());
            System.out.println("耗时：" + (Instant.now().toEpochMilli() - l));
        } catch (Exception e) {

        }

    }
}

