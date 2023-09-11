package com.crio.jukebox.entities;

import java.util.List;

public class Song extends BaseEntity {

    private String name;
    private String genre;
    private String albumName;
    private String artist;
    private List<String> featuredArtist;
    private SongStatus status;

    public Song(String name, String genre, String albumName, String artist,
            List<String> featuredArtist, SongStatus status) {
        this.name = name;
        this.genre = genre;
        this.albumName = albumName;
        this.artist = artist;
        this.featuredArtist = featuredArtist;
        this.status = status;
    }


    public Song(String id, String name, String genre, String albumName, String artist,
            List<String> featuredArtist, SongStatus status) {
        this.id = id;
        this.name = name;
        this.genre = genre;
        this.albumName = albumName;
        this.artist = artist;
        this.featuredArtist = featuredArtist;
        this.status = status;
    }


    public void setName(String name) {
        this.name = name;
    }


    public String getGenre() {
        return genre;
    }


    public void setGenre(String genre) {
        this.genre = genre;
    }


    public String getAlbumName() {
        return albumName;
    }


    public void setAlbumName(String albumName) {
        this.albumName = albumName;
    }


    public void setArtist(String artist) {
        this.artist = artist;
    }


    public List<String> getFeaturedArtist() {
        return featuredArtist;
    }


    public void setFeaturedArtist(List<String> featuredArtist) {
        this.featuredArtist = featuredArtist;
    }


    public void setStatus(SongStatus status) {
        this.status = status;
    }


    public String getName() {
        return name;
    }

    public String getArtist() {
        return artist;
    }

    public SongStatus getStatus() {
        return status;
    }


    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((artist == null) ? 0 : artist.hashCode());
        result = prime * result + ((name == null) ? 0 : name.hashCode());
        result = prime * result + ((status == null) ? 0 : status.hashCode());
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        Song other = (Song) obj;
        if (artist == null) {
            if (other.artist != null)
                return false;
        } else if (!artist.equals(other.artist))
            return false;
        if (name == null) {
            if (other.name != null)
                return false;
        } else if (!name.equals(other.name))
            return false;
        if (status != other.status)
            return false;
        return true;
    }

    @Override
    public String toString() {
        return "Song [albumName=" + albumName + ", artist=" + artist + ", featuredArtist="
                + featuredArtist + ", genre=" + genre + ", name=" + name + ", status=" + status
                + "]";
    }
    
}

