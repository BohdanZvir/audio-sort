package com.bzvir;

import com.bzvir.util.FileUtil;
import org.hamcrest.Matchers;
import org.junit.Test;

import java.io.File;
import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;

public class AppTest {

    @Test
    public void updateFileList() {
        String path = "/home/bohdan/Music/";
        List<File> files = FileUtil.listFiles(path);
        assertThat(files, Matchers.notNullValue());
        StringBuffer sb = new StringBuffer();

        long count = files.stream().peek(f -> FileUtil.printFileInfo(f, sb)).count();
        System.out.println(sb);
        System.out.println(count);
    }
}
