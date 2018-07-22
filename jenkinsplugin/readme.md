running:
mvn clean hpi:run

browser:http://127.0.0.1:8080/jenkins/
create a proejct , and in build drop-down menu you will find :HelloWorldBuilder
 and SleepBuilder

 start a new plugin project:
 mvn hpi:create
 idea pom.xml


Jenkins插件原理：
---------------
1.它的插件是分两个部分，显示部分和运行控制部分，显示部分继承RootAction 定义菜单名称和url；控制部分继承Builder，在perfor方法里定义运行内容；在控制部分也有定义JSP参数输入的。

2.插件打包成jar包，后缀名要改成.hpi或jpi，或通过web浏览器上传，或者直接放到它的plug-in目录下，或者启动Jenkins时指定一下。


3.jenkins首先要分析每个jar包和它的依赖是否全部都在，检查了完整了，才会发送到专门运行任务的地方去运行这些jar包。


 具体工作有：
 1.加载 Manifest文件
 2.根据 Manifest文件中库包寻找库包和类
 3.寻找这个jar包依赖的插件
 4,创建类加载器 class-loader
 5. 为这个Jar包依赖的插件创建专门的类加载器

经过以上工作，这个插件属于待命激活
 6.检查重复
 7.使用插件策略PluginStrategy加载可以激活的插件.调用start()方法。
 8.检查循环依赖
 9.调用插件的Plugin.postInitailize()

每个插件都有自己的类加载器，一个插件一个。

基础class-loader是一个URL class-loader，加载jar包中定义库包和类，包括在classpath中定义的类和WEB-INF/classes下定义的。

基础的class-loader父加载器是依赖加载器，是加载插件的依赖库包和类的列表。

基础类加载器之上是一个Masking Class Loader, 它的父加载器则是核心类加载器. 这是用于在Manifest文件中属性 “MaskedClasses”的定义，目标是让插件加载插件自身类的库包。
