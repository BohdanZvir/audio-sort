package com.bzvir;

import com.mpatric.mp3agic.InvalidDataException;
import com.mpatric.mp3agic.UnsupportedTagException;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.powermock.modules.junit4.PowerMockRunner;

import java.io.IOException;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

/**
 * Unit test for simple App.
 */
@RunWith(PowerMockRunner.class)
//@PrepareForTest(FileFixer.class)
public class FileFixerTest {

    private String testFile = "test_data/1Руслана - Світанок1.mp3";

    @Test
    public void renameFileByArtistDashSongNameDotMp3() throws InvalidDataException, IOException, UnsupportedTagException {
        String fileName = FileFixer.fixFileName(testFile);
        assertThat(fileName, is("Руслана - Світанок.mp3"));
    }

}
