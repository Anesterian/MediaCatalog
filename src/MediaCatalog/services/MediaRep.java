package MediaCatalog.services;

import java.util.List;

import MediaCatalog.MediaFiles;

public interface MediaRep {
    void add(MediaFiles media) throws RepException;
    void delete(MediaFiles media) throws RepException;
    void move(MediaFiles media, String newPath) throws RepException;
    MediaFiles getMediaFiles(String path) throws RepException;
    List <String> getInfoOfAllMedia() throws RepException;
    void addToFavorite(MediaFiles media) throws RepException;
    void removeFromFavorite(MediaFiles media) throws RepException;
    List <String> getInfoOfFavoriteMedia() throws RepException;
    List <String> search(String text)throws RepException;

}