/**
 * Copyright (C), 2020-2021
 * FileName: HelloW
 * Date:     2021/4/8 16:44
 * Description:
 */
package com.adamin.appserver.util;

/**
 * @author adamin
 * @site https://www.lixiaopeng.top
 * @create 2021/4/8 16:44
 */
public class HelloW {
    public static void main(String[] args) {
        if(args!=null&&args.length>0){
            System.out.println("----arg0----"+args[0]);

        }else{
            System.out.println("args null = " );
        }
    }
}
