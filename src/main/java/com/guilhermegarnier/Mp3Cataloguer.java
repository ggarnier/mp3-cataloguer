package com.guilhermegarnier;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.jaudiotagger.audio.AudioFileIO;
import org.jaudiotagger.audio.exceptions.CannotReadException;
import org.jaudiotagger.audio.exceptions.InvalidAudioFrameException;
import org.jaudiotagger.audio.exceptions.ReadOnlyFileException;
import org.jaudiotagger.audio.mp3.MP3File;
import org.jaudiotagger.tag.FieldKey;
import org.jaudiotagger.tag.TagException;
import org.jaudiotagger.tag.id3.AbstractID3v2Tag;
import org.jaudiotagger.tag.id3.ID3v1Tag;


public class Mp3Cataloguer {
    private static String basePath = ".";
    private static String discLabel = "";
    private static List<Mp3Dto> resultados = new ArrayList<Mp3Dto>();

    public static void main(String[] args) {
        if (args.length > 0) {
            basePath = args[0];

            if (args.length > 1) {
                discLabel = args[1];
            }
        }
        File diretorio = new File(basePath);
        if (diretorio.exists() && diretorio.isDirectory()) {
            showFiles(diretorio);
            showResults();
        } else {
            System.out.println("Diretorio " + diretorio.getAbsolutePath() + " invalido");
        }
    }

    private static void showFiles(File diretorio) {
        if (!diretorio.exists()) {
            return;
        }
        if (diretorio.isDirectory()) {
            for (String nomeArquivo : diretorio.list()) {
                showFiles(new File(diretorio.getAbsolutePath() + File.separator + nomeArquivo));
            }
        } else if (diretorio.isFile() && diretorio.getName().toLowerCase().endsWith(".mp3")) {
            processFile(diretorio);
        }
    }

    private static void processFile(File arquivo) {
        try {
            MP3File mp3 = (MP3File)AudioFileIO.read(arquivo);

            String filename = arquivo.getName();
            String path = arquivo.getPath().substring(0, arquivo.getPath().length() - filename.length() - 1);
            if (path.length() <= basePath.length()) {
                path = "";
            } else {
                path = path.substring(basePath.length() + 1);
            }

            Mp3Dto dto = new Mp3Dto();
            dto.setDiscLabel(discLabel);
            dto.setId(resultados.size()+1L);
            dto.setFilename(filename);
            dto.setPath(path);
            dto.setSize(arquivo.length());
            getTags(mp3, dto);
            resultados.add(dto);
        } catch (IOException e) {
        } catch (TagException e) {
        } catch (CannotReadException e) {
        } catch (ReadOnlyFileException e) {
        } catch (InvalidAudioFrameException e) {
        }
    }

    private static void getTags(MP3File mp3, Mp3Dto dto) {
        if (mp3.hasID3v2Tag()) {
            getID3v2Tag(mp3, dto);
        } else if (mp3.hasID3v1Tag()) {
            getID3v1Tag(mp3, dto);
        }
    }

    private static void getID3v1Tag(MP3File mp3, Mp3Dto dto) {
        ID3v1Tag tag = mp3.getID3v1Tag();
        dto.setArtist(tag.getFirst(FieldKey.ARTIST));
        dto.setAlbum(tag.getFirst(FieldKey.ALBUM));
        dto.setGenre(tag.getFirst(FieldKey.GENRE));
        dto.setSongTitle(tag.getFirst(FieldKey.TITLE));
        dto.setYear(tag.getFirst(FieldKey.YEAR));
        dto.setTrackNumber(tag.getFirst(FieldKey.TRACK));
    }

    private static void getID3v2Tag(MP3File mp3, Mp3Dto dto) {
        AbstractID3v2Tag tag = mp3.getID3v2Tag();
        dto.setArtist(tag.getFirst(FieldKey.ARTIST));
        dto.setAlbum(tag.getFirst(FieldKey.ALBUM));
        dto.setGenre(tag.getFirst(FieldKey.GENRE));
        dto.setSongTitle(tag.getFirst(FieldKey.TITLE));
        dto.setYear(tag.getFirst(FieldKey.YEAR));
        dto.setTrackNumber(tag.getFirst(FieldKey.TRACK));
    }

    private static void showResults() {
        System.out.println(Mp3Dto.showHeader());
        for (Mp3Dto dto : resultados) {
            System.out.println(dto);
        }
    }
}