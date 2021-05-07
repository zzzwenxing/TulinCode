package com.tuling.netty.splitpacket;


/**
 * 自定义协议包
 */
public class MyMessageProtocol {

    //定义一次发送包体长度
    private int len;
    //一次发送包体内容
    private byte[] content;

    public int getLen() {
        return len;
    }

    public void setLen(int len) {
        this.len = len;
    }

    public byte[] getContent() {
        return content;
    }

    public void setContent(byte[] content) {
        this.content = content;
    }
}
