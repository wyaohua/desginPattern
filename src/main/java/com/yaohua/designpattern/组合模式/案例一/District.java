package com.yaohua.designpattern.组合模式.案例一;

/**
 * 文件名：District
 * 作者：huahua
 * 时间：2025/11/5 21:53
 * 描述  区 ，最小的节点划分
 */
public class District implements PopulationNode{

    //区的名字和人口数量
    private final String name;
    private final int number;

    public District(String name, int number) {
        this.name = name;
        this.number = number;
    }


    @Override
    public int computePoulation() {
        return number;
    }
}
