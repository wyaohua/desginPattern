package com.yaohua.designpattern.组合模式.案例一;

import java.util.ArrayList;
import java.util.List;

/**
 * 文件名：Province
 * 作者：huahua
 * 时间：2025/11/5 21:57
 * 描述  省份，多个城市组成省份；
  */
public class Province implements PopulationNode{

    private final String name;

    private List<PopulationNode> cities =new ArrayList<>();


    public Province(String name) {
        this.name = name;

    }


    public void addCity(City city) {
        this.cities.add(city);
    }

    @Override
    public int computePoulation() {
        return cities.stream().mapToInt(PopulationNode::computePoulation).sum();
    }
}
