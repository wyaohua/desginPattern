package com.yaohua.designpattern.组合模式.案例一;

import java.util.ArrayList;
import java.util.List;

/**
 * 文件名：City
 * 作者：huahua
 * 时间：2025/11/5 21:55
 * 描述  城市， 内部有多个区；
 */
public class City implements PopulationNode{

    private final String name;


    private List<PopulationNode> districts = new ArrayList<>();

    public City(String name) {
        this.name = name;
    }



    public void  addDistrict(District district) {
        districts.add(district);
    }


    //统计人口，就是统计它的所有区的人口总和
    @Override
    public int computePoulation() {
        return districts.stream().mapToInt(PopulationNode::computePoulation).sum();
    }
}
