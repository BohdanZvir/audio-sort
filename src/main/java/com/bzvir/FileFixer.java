package com.bzvir;

import com.mpatric.mp3agic.InvalidDataException;
import com.mpatric.mp3agic.Mp3File;
import com.mpatric.mp3agic.UnsupportedTagException;

import java.io.IOException;

/**
 * Created by bohdan.
 */
public class FileFixer {

    static final String ISO_8859_1 = "ISO-8859-1";
    static final String WINDOWS_1251 = "WINDOWS-1251";

    public static String fixFileName(String fileName) throws InvalidDataException, IOException, UnsupportedTagException {
        Mp3File mp3file = new Mp3File(fileName);
        System.out.println("Length of this mp3 is: " + mp3file.getLengthInSeconds() + " seconds");
        System.out.println("Bitrate: " + mp3file.getBitrate() + " kbps " + (mp3file.isVbr() ? "(VBR)" : "(CBR)"));
        System.out.println("Sample rate: " + mp3file.getSampleRate() + " Hz");
        System.out.println("Has ID3v1 tag?: " + (mp3file.hasId3v1Tag() ? "YES" : "NO"));
        String artist = mp3file.getId3v1Tag().getArtist();

        artist = EncodingUtil.fixCyrillicEncoding(artist);
        String encoding = EncodingUtil.detectEncoding(artist);

        String title = mp3file.getId3v1Tag().getTitle();
        title = EncodingUtil.fixCyrillicEncoding(title);
        System.out.println("Has ID3v2 tag?: " + (mp3file.hasId3v2Tag() ? "YES" : "NO"));
        System.out.println("Has custom tag?: " + (mp3file.hasCustomTag() ? "YES" : "NO"));
        return artist + " - " + title + ".mp3";
    }

}
