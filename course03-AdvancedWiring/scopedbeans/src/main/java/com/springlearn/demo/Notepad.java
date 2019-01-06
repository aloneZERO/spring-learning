package com.springlearn.demo;

import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

/**
 * Bean四种作用域
 * 1. singleton 整个应用中，创建唯一实例；
 * 2. prototype 每次注入或通过context获取，都会创建新实例；
 * 3. session Web应用中，每个会话创建一个实例；
 * 4. request Web应用中，每个请求创建一个实例；
 *
 * @author justZero
 * @since 2018/12/30
 */
@Component
@Scope(ConfigurableBeanFactory.SCOPE_PROTOTYPE)
public class Notepad {

    public void open() {
        System.out.println("打开记事本~");
    }

}
