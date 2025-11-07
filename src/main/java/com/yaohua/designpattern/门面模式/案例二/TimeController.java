package com.yaohua.designpattern.门面模式.案例二;

import com.yaohua.plugin.Myplugin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.io.File;
import java.io.InputStream;
import java.lang.reflect.Constructor;
import java.net.URL;
import java.net.URLClassLoader;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * 文件名：TimeController
 * 作者：huahua
 * 时间：2025/11/6 21:41
 * 描述  一个加载插件的案例
 */
@RestController
public class TimeController {

    private final DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

    private Myplugin myplugin;


    @GetMapping("getTime")
    public String getTime(){
        if (myplugin != null){
            myplugin.beforeGetTime();
        }
        return LocalDateTime.now().format(dateTimeFormatter);
    }


    /**
     * 加载插件的方法，  这个一般都是加载类去实现；
     * @param path
     * @return
     */
    //规定实现了我们插件的jar包，必须有一个文件叫做，wuyaohua.plugin ;文件内容就是实现MyPlugin接口的全类型
    @GetMapping("/loadPlugin/{path}")
    public String loadPlugin(@PathVariable String path){
        try {
            File file = new File(path);
            URLClassLoader urlClassLoader = new URLClassLoader(new URL[]{file.toPath().toUri().toURL()});
            InputStream resourceAsStream = urlClassLoader.getResourceAsStream("wuyaohua.plugin");
            String classFullName = new String(resourceAsStream.readAllBytes());
            Class<?> aClass = urlClassLoader.loadClass(classFullName.trim());//加载什么？传入类的全类名
            Constructor<?> constructor = aClass.getConstructor(); //获取无参构造函数
            myplugin= (Myplugin)constructor.newInstance();

            return "加载成功";
        }catch (Exception e){
            return "加载失败";
        }

    }
}
