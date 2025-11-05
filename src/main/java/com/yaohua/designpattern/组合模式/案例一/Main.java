package com.yaohua.designpattern.组合模式.案例一;

/**
 * 文件名：Main
 * 作者：huahua
 * 时间：2025/11/5 21:50
 * 描述   省份包含城市，诚实包含区；
 */
public class Main {

    public static void main(String[] args) {
        //创建省
        Province province_henan = new Province("河南省");

        //创建城市
        City city_zhengzhou = new City("郑州市");
        City city_nanyang = new City("南阳市");


        //创建区
        District d1 = new District("郑州新区", 4000);
        District d2  = new District("郑州东新区", 1000);
        District d3  = new District("郑州西新区", 2000);
        District d11  = new District("邓州区", 3000);
        District d22  = new District("唐河区", 6666);

        //组合
        city_zhengzhou.addDistrict(d1);
        city_zhengzhou.addDistrict(d2);
        city_zhengzhou.addDistrict(d3);
        city_nanyang.addDistrict(d11);
        city_nanyang.addDistrict(d22);


        province_henan.addCity(city_zhengzhou);
        province_henan.addCity(city_nanyang);
        System.out.println(province_henan.computePoulation());

    }
}
