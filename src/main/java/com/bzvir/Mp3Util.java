package com.bzvir;

import com.mpatric.mp3agic.ID3v1;
import com.mpatric.mp3agic.ID3v2;
import com.mpatric.mp3agic.Mp3File;
import org.apache.commons.codec.binary.StringUtils;

import java.io.UnsupportedEncodingException;

/**
 * Created by bohdan on 05.03.16.
 */
public class Mp3Util {

    public static final String UTF_8 = "UTF-8";
    public static final String ISO_8859_1 = "ISO-8859-1";
    public static final String WINDOWS_1251 = "WINDOWS-1251";
    private final String path;

    public Mp3Util(String path) {
        this.path = path;
    }

    public void fix() throws Exception {
        Mp3File mp3file = null;
        try {
            mp3file = new Mp3File(path);
        } catch (Exception e) {
            e.printStackTrace();
        }
        if (mp3file.hasId3v1Tag()) {
            printInfoId3v1(mp3file);
        } else if (mp3file.hasId3v2Tag()) {
            printInfoId3v2(mp3file);
        } else if (mp3file.hasCustomTag()) {
            printCustomTag(mp3file);
        } else if (mp3file.hasXingFrame()) {
            printXingFrame(mp3file);
        }
    }

    private void printXingFrame(Mp3File mp3file) {
        System.out.println(":: XingTag");
        byte[] xingTag = mp3file.getCustomTag();
        System.out.println(new String(xingTag));
    }

    private void printCustomTag(Mp3File mp3file) {
        System.out.println(":: customTag");
        byte[] customTag = mp3file.getCustomTag();
        System.out.println(new String(customTag));
    }

    private void printInfoId3v1(Mp3File mp3file) throws Exception {
        System.out.println(":: id3v1Tag");
        ID3v1 id3v1Tag = mp3file.getId3v1Tag();
        System.out.println("Year: " + id3v1Tag.getYear());
        System.out.println("Track: " + id3v1Tag.getTrack());
        String artist = id3v1Tag.getArtist();
        System.out.println("Artist: " + artist);
        System.out.println(artist.length());
        artist = StringUtils.newString(artist.getBytes(ISO_8859_1), WINDOWS_1251);

        System.out.println(artist.length());
        System.out.println("Artist: after: " + artist);
    }

    private String getString(String artist) {
        byte[] iso88591Data = new byte[0];
        try {
            iso88591Data = artist.getBytes("ISO-8859-9");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        try {
            artist = new String(iso88591Data, "UTF-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return artist;
    }

    public static String convertFromUTF8(String s) {
        String out;
        try {
            out = new String(s.getBytes("ISO-8859-1"), "UTF-8");
        } catch (UnsupportedEncodingException e) {
            return null;
        }
        return out;
    }

    private void printInfoId3v2(Mp3File mp3file) {
        System.out.println(":: id3v2Tag");
        ID3v2 id3v2Tag = mp3file.getId3v2Tag();
        System.out.println("Track: " + id3v2Tag.getTrack());
        System.out.println("Artist: " + id3v2Tag.getArtist());
        System.out.println("Title: " + id3v2Tag.getTitle());
        System.out.println("Album: " + id3v2Tag.getAlbum());
        System.out.println("Year: " + id3v2Tag.getYear());
        System.out.println("Genre: " + id3v2Tag.getGenre() + " (" + id3v2Tag.getGenreDescription() + ")");
        System.out.println("Comment: " + id3v2Tag.getComment());
        System.out.println("Composer: " + id3v2Tag.getComposer());
        System.out.println("Publisher: " + id3v2Tag.getPublisher());
        System.out.println("Original artist: " + id3v2Tag.getOriginalArtist());
        System.out.println("Album artist: " + id3v2Tag.getAlbumArtist());
        System.out.println("Copyright: " + id3v2Tag.getCopyright());
        System.out.println("URL: " + id3v2Tag.getUrl());
        System.out.println("Encoder: " + id3v2Tag.getEncoder());
        byte[] albumImageData = id3v2Tag.getAlbumImage();
        if (albumImageData != null) {
            System.out.println("Have album image data, length: " + albumImageData.length + " bytes");
            System.out.println("Album image mime type: " + id3v2Tag.getAlbumImageMimeType());
        }
    }
}
