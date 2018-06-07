package org.nuaa.tomax.csreen;

/**
 * @Author: ToMax
 * @Description:
 * @Date: Created in 2018/6/7 22:45
 */
public class Application {
    public static void main(String[] args) {
        HotKey key = new HotKey();
        key.initHotkey();

        //下面模拟长时间执行的任务
        while (true) {
            try {
                Thread.sleep(10000);
            } catch (Exception ex) {
                break;
            }
        }
    }
}
