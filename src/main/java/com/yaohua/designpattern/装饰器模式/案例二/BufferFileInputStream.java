package com.yaohua.designpattern.装饰器模式.案例二;


import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

/**
 * 文件名：BufferFileInputStream
 * 作者：huahua
 * 时间：2025/10/31 21:21
 * 描述
 */
public class BufferFileInputStream extends InputStream {


    //1. 缓冲区（固定大小）
    private final byte[] buffer = new byte[8192];


    //2.我们就还用 FileInputStream 读，因为我们就是为了包装 FileInputStream
    private final FileInputStream fileInputStream;

    public BufferFileInputStream(FileInputStream fileInputStream) {
        this.fileInputStream = fileInputStream;
    }

    /**
     * 3. 两个指针用于控制缓冲区读取：
     * position: 下一个可读字节在 buffer 中的索引（0 .. capacity-1）
     * 当 position == -1 表示 buffer 尚未初始化或已经无数据（EOF）。
     * capacity: 缓冲区中当前有效字节数量（即底层 read 返回的字节数）
     * 当 capacity == -1 表示底层已到达 EOF（没有更多数据可以填充）。
     */

    private int position = -1;  //读到buffer的哪个位置了；
    private int capacity = -1;  //缓冲区中一共存了多少个字节；


    @Override
    public int read() throws IOException {
        // 如果缓冲区还有可读数据，直接从缓冲区返回字节
        if (canReadBuffer()) {
            return readBuffer();
        }


        // 否则尝试从底层流刷新缓冲区
        refreshBuffer();

        // 刷新后仍然没有数据（说明到底层 EOF），返回 -1
        if (!canReadBuffer()) {
            return -1;
        }

        // 有数据则返回缓冲区中的下一个字节
        return readBuffer();
    }

    /**
     * 从缓冲区读取一个字节并返回（0..255），使用 & 0xFF 确保返回的 int 为无符号值。
     * 同时将 position 前进到下一个位置。
     */
    private int readBuffer() {
        return buffer[position++] & 0xFF;
    }


    /**
     * 从底层流读取数据填充 buffer。
     * 如果底层返回 -1（EOF），我们把 capacity 置为 -1 并把 position 置为 -1 表示“无数据可读”。
     * 否则 position 从 0 开始，capacity 为实际读取到的字节数。
     */
    private void refreshBuffer() throws IOException {
        //读，就是往buffer里面写字节， 返回值就是写了多少个字节；  每次调用都会从buffer的0索引开始写;
        //读数据写到缓冲区内，就是初始化；
        capacity = fileInputStream.read(buffer);
        if (capacity == -1){
            // 到达 EOF，标记为未初始化 / 无数据
            position = -1;
        }else{
            // 初始化 position 指向 buffer 的第一个字节
            position = 0;
        }

    }

    /**
     * 判断当前缓冲区是否有可以读取的字节。
     * 只有在 position != -1 且 position < capacity 时才表示有数据。
     */
    private boolean canReadBuffer() {

        if (position == -1) {
            return false;
        }

        if (position == capacity) {
            return false;
        }
        return true;

    }

    /**
     * 覆盖 close，确保关闭被包装的底层流。
     */
    @Override
    public void close() throws IOException {
        // 先关闭底层流，释放资源
        fileInputStream.close();
        // 再调用父类（可选），InputStream.close() 的默认实现不强制要求，但保留以示规范。
        super.close();
    }
}
