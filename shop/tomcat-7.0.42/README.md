将conf和webapps复制到catalina-home文件夹下

VM options参数：
-Dcatalina.home=catalina-home 
-Dcatalina.base=catalina-home
-Djava.endorsed.dirs=catalina-home/endorsed 
-Djava.io.tmpdir=catalina-home/temp
-Djava.util.logging.manager=org.apache.juli.ClassLoaderLogManager
-Djava.util.logging.config.file=catalina-home/conf/logging.properties


