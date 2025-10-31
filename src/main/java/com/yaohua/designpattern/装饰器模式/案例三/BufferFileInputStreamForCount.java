package com.yaohua.designpattern.装饰器模式.案例三;

import java.io.IOException;
import java.io.InputStream;

/**
 * 文件名：BufferFileInputStreamForCount
 * 作者：huahua
 * 时间：2025/10/31 22:00
 * 描述
 */

public class BufferFileInputStreamForCount extends InputStream {



    private int count = 0;

    private final InputStream inputStream;

    public BufferFileInputStreamForCount(InputStream inputStream) {
       this.inputStream = inputStream;
    }

    @Override
    public int read() throws IOException {
        count ++;
        return inputStream.read();
    }

    public int getCount(){
        return count;
    }

}
