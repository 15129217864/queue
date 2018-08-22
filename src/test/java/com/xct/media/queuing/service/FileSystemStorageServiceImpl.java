package com.xct.media.queuing.service;

import com.xct.media.queuing.storage.StorageService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.nio.file.Path;
import java.util.stream.Stream;

/**
 * Created by Chris on 2018/5/27.
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class FileSystemStorageServiceImpl {

    @Autowired
    private StorageService storageService;


    @Test
    public void loadAllTest() {

        Stream<Path> pathStream = storageService.loadAll("2");

        pathStream.forEach(System.out::println);
    }
}
