package com.guilhermegarnier;

public class Mp3Dto {
    private static String FIELD_SEPARATOR = ";";

    private Long id, size;
    private String path, filename, songTitle, artist, album, year, trackNumber, genre;
    private String discLabel;

    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }
    public String getPath() {
        return path;
    }
    public void setPath(String path) {
        this.path = path;
    }
    public String getFilename() {
        return filename;
    }
    public void setFilename(String filename) {
        this.filename = filename;
    }
    public String getSongTitle() {
        return songTitle;
    }
    public void setSongTitle(String songTitle) {
        this.songTitle = songTitle;
    }
    public String getArtist() {
        return artist;
    }
    public void setArtist(String artist) {
        this.artist = artist;
    }
    public String getAlbum() {
        return album;
    }
    public void setAlbum(String album) {
        this.album = album;
    }
    public String getYear() {
        return year;
    }
    public void setYear(String year) {
        this.year = year;
    }
    public String getTrackNumber() {
        return trackNumber;
    }
    public void setTrackNumber(String trackNumber) {
        this.trackNumber = trackNumber;
    }
    public String getGenre() {
        return genre;
    }
    public void setGenre(String genre) {
        this.genre = genre;
    }
    public Long getSize() {
        return size;
    }
    public void setSize(Long size) {
        this.size = size;
    }
    public String getDiscLabel() {
        return discLabel;
    }
    public void setDiscLabel(String discLabel) {
        this.discLabel = discLabel;
    }

    public static String showHeader() {
        StringBuilder sb = new StringBuilder();
        sb.append("Label");
        sb.append(FIELD_SEPARATOR);
        sb.append("Size");
        sb.append(FIELD_SEPARATOR);
        sb.append("Path");
        sb.append(FIELD_SEPARATOR);
        sb.append("Filename");
        sb.append(FIELD_SEPARATOR);
        sb.append("Song title");
        sb.append(FIELD_SEPARATOR);
        sb.append("Artist");
        sb.append(FIELD_SEPARATOR);
        sb.append("Album");
        sb.append(FIELD_SEPARATOR);
        sb.append("Track#");
        sb.append(FIELD_SEPARATOR);
        sb.append("Year");
        sb.append(FIELD_SEPARATOR);
        sb.append("Genre");

        return sb.toString();
    }

    @Override
    public String toString() {
        return toStringue(FIELD_SEPARATOR, false);
    }

    public String toStringue(String separator, boolean addLabel) {
        StringBuilder sb = new StringBuilder();
        if (discLabel != null) {
            if (addLabel) sb.append("Label: ");
            sb.append(discLabel);
            sb.append(separator);
        }
        if (size != null) {
            if (addLabel) sb.append("size: ");
            sb.append(size);
            sb.append(separator);
        }
        if (path != null) {
            if (addLabel) sb.append("path: ");
            sb.append(path);
            sb.append(separator);
        }
        if (filename != null) {
            if (addLabel) sb.append("filename: ");
            sb.append(filename);
            sb.append(separator);
        }
        if (songTitle != null) {
            if (addLabel) sb.append("songTitle: ");
            sb.append(songTitle);
            sb.append(separator);
        }
        if (artist != null) {
            if (addLabel) sb.append("artist: ");
            sb.append(artist);
            sb.append(separator);
        }
        if (album != null) {
            if (addLabel) sb.append("album: ");
            sb.append(album);
            sb.append(separator);
        }
        if (trackNumber != null) {
            if (addLabel) sb.append("trackNumber: ");
            sb.append(trackNumber);
            sb.append(separator);
        }
        if (year != null) {
            if (addLabel) sb.append("year: ");
            sb.append(year);
            sb.append(separator);
        }
        if (genre != null) {
            if (addLabel) sb.append("genre: ");
            sb.append(genre);
        }
        return sb.toString();
    }
}