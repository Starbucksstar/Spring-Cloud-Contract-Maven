# Spring-Cloud-Contract-
# 基于Spring-Cloud-Contract 的微服务调用之契约测试
###1.契约测试定义
> 消费者驱动的契约测试（Consumer-Driven Contracts，简称CDC），是指从消费者业务实现的角度出发，驱动出契约，再基于契约，对提供者验证的一种测试方式。

###2.契约测试步骤
###### 	Spring Cloud Contract契约测试大概分三个步骤
1. producer提供服务的定好服务接口（即契约）
2. 	生成stub，并共享给消费方，可通过mvn install到maven库中
3. 	consumer消费方引用契约服务，进行集成测试

###3.服务提供方(提供契约)
   	<dependency>
            <groupId>org.springframework.cloud</groupId>
            <artifactId>spring-cloud-starter-contract-verifier</artifactId>
            <scope>test</scope>
       </dependency>

#####一、配置工程：
###### 在服务provider中，pom文件包含spring-cloud-contract-maven-plugin的插件，命令和作用：<ps: mvn s-c-c:convert>
- spring-cloud-contract:convert：在target/stubs下根据将契约生成mapping文件，用于打包jar文件，提供http服务，供consumer使用
- spring-cloud-contract:generateStubs：生成stubs的jar包，用于分享给consumer使用
- spring-cloud-contract:generateTests：基于contract生成服务契约的测试案例，服务实现了契约后，保证实现与契约一致
- spring-cloud-contract:run：启动契约服务，将契约暴露为http server服务

#####二、编写契约文件：
- provider工程：src/test/resources/contracts/HelloController.groovy 中增加契约文件

#####三、安装stubs.jar到maven本地库中（或远程库）
- 执行命令：mvn clean install

#####四、接口实现并检查是否符合契约
- 执行上述命令后：target/generated-test-sources/contracts下面会生成契约的单元测试，可以执行查看运行结果

#####五、原理：
- 通过verifier依赖引入contract相关文件，编写契约文件，生成stubs的jar包和契约单元测试，并将stubs的jar包安装到maven仓库，这样在其他服务就可以引用此契约。

###4.消费方（契约引用）
		<dependency>
                <groupId>org.springframework.cloud</groupId>
                <artifactId>spring-cloud-starter-contract-stub-runner</artifactId>
                <scope>test</scope>
        </dependency>

#####一、配置工程：
**在服务mocktest中，pom需要包含feign、hystrix、ribbon的依赖**

查看test/MocktestApplicationTests.java文件：实现两种rest接口调用：分别是RestTemplate和FeignClient。

#####二、单元测试：
    @AutoConfigureStubRunner(ids = {"com.star:provider:+:stubs:8080"},stubsMode = StubRunnerProperties.StubsMode.LOCAL)

#####三、上行注解含义：
######1.AutoConfigureStubRunner：启动契约服务，模拟produer提供服务
######2.stubsMode = StubRunnerProperties.StubsMode.LOCAL（CLASSPATH/REMOTE）：从哪里加载stubs.jar
######3.ids格式：
  ```java
	groupId:artifactId:version:classifier:port
```
######4.注解中ids解释：
```java
com.star:provider:+:stubs:8080：
```
> groupid=com.star artificatId=provider version=+(每次都取最新的jar) classifier=stubs port=8080(在8080端口运行stubs服务)





