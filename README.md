# JSwu

JSwu是一个易扩展,极简单的java框架.你可以使用它快速构建关于西南大学的校园应用.框架内置了缓存和连接池,方便开发者开发应用程序的同时,坚决不给教务等系统造成丝毫压力.

## 使用
[Release](https://github.com/xndxcsd/JSwu/releases)  

## 一个简单的示例

要使用JSwu构建一个校园应用非常简单快速:
```java
public class demo {

    public static void main(String[] args) {
        SwuConfig swuConfig = new SwuConfig.Builder("your_swuid", "password")
                .build();

        SwuManager swuManager = new SwuManager(swuConfig);

        Grader grader = swuManager.getGrader();
        System.out.println(grader.getJsonGrade());

    }
}
```


## 贡献

使用和编译JSwu需要满足一下要求
- oracle jdk 1.8 +
- apache maven 3 +

JSWu的详细设计参见[开发者文档](DEVELOPER-GUIDE.md)

上述的开发者文档会指引您非常轻松地扩展JSwu以及让它变得很好.彼时,希望您不吝提交代码,我们欢迎每一位开发者作出任何贡献.

## 西南大学开源协会
我们始终相信改变源自于热爱和坚持

> 凡心所向，素履所往，生如逆旅，一苇以航  

感谢所有支持我们的人