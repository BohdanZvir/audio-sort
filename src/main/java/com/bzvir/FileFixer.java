package com.bzvir;

import com.mpatric.mp3agic.InvalidDataException;
import com.mpatric.mp3agic.Mp3File;
import com.mpatric.mp3agic.UnsupportedTagException;
import org.mozilla.universalchardet.UniversalDetector;

import java.io.IOException;
import java.io.UnsupportedEncodingException;

import static java.lang.Character.UnicodeBlock.*;

/**
 * Created by bohdan.
 */
public class FileFixer {

    public static final String ISO_8859_1 = "ISO-8859-1";
    public static final String WINDOWS_1251 = "WINDOWS-1251";

    public static String fixFileName(String fileName) throws InvalidDataException, IOException, UnsupportedTagException {
        Mp3File mp3file = new Mp3File(fileName);
        System.out.println("Length of this mp3 is: " + mp3file.getLengthInSeconds() + " seconds");
        System.out.println("Bitrate: " + mp3file.getBitrate() + " kbps " + (mp3file.isVbr() ? "(VBR)" : "(CBR)"));
        System.out.println("Sample rate: " + mp3file.getSampleRate() + " Hz");
        System.out.println("Has ID3v1 tag?: " + (mp3file.hasId3v1Tag() ? "YES" : "NO"));
        String artist = mp3file.getId3v1Tag().getArtist();

        artist = fixCyrillicEncoding(artist);
        String encoding = detectEncoding(artist);

        String title = mp3file.getId3v1Tag().getTitle();
        title = fixCyrillicEncoding(title);

        // WINDOWS-1251

        System.out.println("Has ID3v2 tag?: " + (mp3file.hasId3v2Tag() ? "YES" : "NO"));
        System.out.println("Has custom tag?: " + (mp3file.hasCustomTag() ? "YES" : "NO"));
        return artist + " - " + title + ".mp3";
    }

    private static String fixCyrillicEncoding(String text) throws UnsupportedEncodingException {
        if(of(text.charAt(0)).equals(BASIC_LATIN)) {
            return text;
        } else
        if(of(text.charAt(0)).equals(CYRILLIC)) {
            return text;
        }
        else {
            String newStr = new String(text.getBytes(ISO_8859_1), WINDOWS_1251);
            if(of(newStr.charAt(0)).equals(CYRILLIC)) {
                return newStr;
            }

            throw new IllegalArgumentException("not supported");
        }
    }

    private static String detectEncoding(String text) throws UnsupportedEncodingException {
        UniversalDetector detector = new UniversalDetector(null);
// (2)
        byte[] bytes = text.getBytes();
        detector.handleData(bytes, 0, bytes.length);
// (3)
        detector.dataEnd();

// (4)
        String encoding = detector.getDetectedCharset();
// (5)
        detector.reset();
        return encoding;
    }

}
