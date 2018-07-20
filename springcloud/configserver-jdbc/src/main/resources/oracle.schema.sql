CREATE TABLE  PROPERTIES (
  KEY         VARCHAR(2048),
  VALUE       VARCHAR(2048),
  APPLICATION VARCHAR(128),
  PROFILE     VARCHAR(128),
  LABEL       VARCHAR(128),
  PRIMARY KEY (KEY, APPLICATION, PROFILE, LABEL)
);

INSERT INTO PROPERTIES (APPLICATION, PROFILE, LABEL, KEY, VALUE)
VALUES ('PengProducerService', 'default', 'master', 'eureka.client.serviceUrl.defaultZone', 'http://localhost:1111/eureka/');

#安装oracle在本地仓库
mvn install:install-file -Dfile=ojdbc8.jar -DgroupId=com.oracle -DartifactId=ojdbc8 -Dversion=12.2.0.1 -Dpackaging=jar

 <!-- 添加oracle jdbc driver -->
    <dependency>
        <groupId>com.oracle</groupId>
        <artifactId>ojdbc8</artifactId>
        <version>12.2.0.1</version>
    </dependency>