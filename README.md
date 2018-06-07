# CapScreen

一款截屏工具，支持给截屏图片打标签，打完标签后自动保存到相应的目录，目前仅支持`Windows`平台

# 目录结构

`res` 目录下为 `setting.properties`配置文件，配置标签分类和文件路径
`com.melloware.jintellitype`为实现快捷键功能采用的库
`org.nuaa.tomax.csreen`为本系统业务的代码

## setting.properties
+ savePath

图片保存的根路径

+ labels

标签名数组，打了该标签的图片会保存到`savePath/label`目录下，文件名为index.png(index自增)

## Application.java

在`org.nuaa.tomax.csreen`包下，是程序运行的入口，运行后，程序开始监听快捷键。

## HotKey.java

主要定义快捷键，包括两个快捷键，一个是唤醒截屏的快捷键，一个是结束程序的快捷键

`Ctrl+Alt+S` : 截屏快捷键
`Ctrl+Alt+Q` : 退出快捷键

如有需要，可以直接修改该文件中定义的快捷键
```
void initHotkey() {
    //参数KEY_1表示改组热键组合的标识，第二个参数表示组合键，如果没有则为0，该热键对应ctrl+alt+I  
    JIntellitype.getInstance().registerHotKey(KEY_1, JIntellitype.MOD_CONTROL + JIntellitype.MOD_ALT,
            (int) 'S');
    JIntellitype.getInstance().registerHotKey(KEY_2, JIntellitype.MOD_CONTROL + JIntellitype.MOD_ALT,
            (int) '2');
    JIntellitype.getInstance().registerHotKey(KEY_3, JIntellitype.MOD_CONTROL + JIntellitype.MOD_ALT,
            (int) 'Q');

    JIntellitype.getInstance().addHotKeyListener(this);
}
```

## 其他文件，基本为UI的文件，目前UI比较简陋且非常丑

# 运行说明

执行`Application.java`的`main`函数开始运行

使用`ctrl+alt+s`唤醒截屏

![]()

选择标签

![]()

使用`ctrl+alt+q`退出程序



