package com.xct.media.queuing;

import com.xct.media.queuing.conf.StorageProperties;
import com.xct.media.queuing.storage.StorageService;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.jms.annotation.EnableJms;
import org.springframework.scheduling.annotation.AsyncConfigurerSupport;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

import java.util.concurrent.Executor;

@SpringBootApplication
@EnableScheduling
@EnableAsync
@MapperScan("com.xct.media.queuing.mapper")
@EnableConfigurationProperties(StorageProperties.class)
@EnableJms
public class QueuingApplication extends AsyncConfigurerSupport {

    // 启动入口
    public static void main(String[] args) {
        SpringApplication.run(QueuingApplication.class, args);
    }

    @Override
    public Executor getAsyncExecutor() {
        ThreadPoolTaskExecutor executor = new ThreadPoolTaskExecutor();
        executor.setCorePoolSize(2);
        executor.setMaxPoolSize(6);
        executor.setQueueCapacity(500);
        executor.setThreadNamePrefix("queuing-async-");
        executor.initialize();
        return executor;
    }

    @Bean
    CommandLineRunner init(StorageService storageService) {
        return (args) -> {
            // 清空目录下所有文件；生产务必注释掉remove方法；
            //storageService.removeAll();
            // 初始化存放文件路径
            storageService.init();
        };
    }
}
