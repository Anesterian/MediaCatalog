package MediaCatalog.services;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import MediaCatalog.MediaFiles;

public class SQLRep  implements MediaRep {

    private Connection conn = null;
    PreparedStatement stmt;
    private ResultSet rs;

    public SQLRep() throws RepException {
        try {
            Class.forName("org.sqlite.JDBC");
            conn = DriverManager.getConnection("jdbc:sqlite:media.db");
            Statement statmt = conn.createStatement();
            statmt.execute("CREATE TABLE if not exists 'media' ('path' VARCHAR(255) NOT NULL PRIMARY KEY, 'data' BLOB NOT NULL, 'favorite' Boolean NOT NULL);");
        } catch (ClassNotFoundException e) {
            throw new RepException(RepException.CONNECTON_ERROR,e.getMessage());
        } catch (SQLException e) {
            throw new RepException(RepException.CONNECTON_ERROR,e.getMessage());
        }

    }
    @Override
    public void add(MediaFiles media) throws RepException {
        String sql = "INSERT INTO media (path, data, favorite) VALUES (?,?,?)";
        try {
            stmt = conn.prepareStatement(sql);
            stmt.setString(1,media.getPath());
            stmt.setBytes(2, media.getData());
            stmt.setBoolean(3, media.IsFavorite());
            stmt.executeUpdate();
            stmt.close();
        } catch (SQLException e) {
            throw new RepException(RepException.ERROR_GET_DATA,e.getMessage());
        }


    }

    private boolean mediaFilesExist(String path) throws RepException {
        String sql = "SELECT path FROM media WHERE path=?";
        try {
            stmt = conn.prepareStatement(sql);
            stmt.setString(1, path);
            rs = stmt.executeQuery();
            if (rs.next()){
                rs.close();
                stmt.close();
                return true;
            }
        } catch (SQLException e) {
            throw new RepException(RepException.ERROR_GET_DATA,e.getMessage());
        }
        return false;
    }
    @Override
    public void delete(MediaFiles media) throws RepException {
        String sql2 = "DELETE FROM media WHERE path=?";
        if (mediaFilesExist(media.getPath())) {
            try {
                stmt = conn.prepareStatement(sql2);
                stmt.setString(1, media.getPath());
                stmt.executeUpdate();
                stmt.close();
            } catch (SQLException e) {
                throw new RepException(RepException.ERROR_DELETE_DATA, e.getMessage());
            }
        }
    }

    @Override
    public void move(MediaFiles media, String newPath) throws RepException {
        String sql = "UPDATE media SET path=?, data=?, favorite=? WHERE path=?";
        try {
            stmt = conn.prepareStatement(sql);
            stmt.setString(1,newPath);
            stmt.setBytes(2, media.getData());
            stmt.setBoolean(3, media.IsFavorite());
            stmt.setString(4,media.getPath());
            stmt.executeUpdate();
            stmt.close();
        } catch (SQLException e) {
            throw new RepException(RepException.ERROR_SET_DATA,e.getMessage());
        }
    }

    @Override
    public MediaFiles getMediaFiles(String path) throws RepException {
        MediaFiles media;
        String sql = "SELECT * FROM media WHERE path=?";
        try {
            stmt = conn.prepareStatement(sql);
            stmt.setString(1, path);
            rs = stmt.executeQuery();
            if (rs.next()) {
                media = new MediaFiles();
                media.setPath(rs.getString("path"));
                media.setData(rs.getBytes("data"));
                media.setFavorite(rs.getBoolean("favorite"));
            }else{
                throw new RepException(RepException.ERROR_DATA_NOT_FOUND,"media not found");
            }
            rs.close();
            stmt.close();
        } catch (SQLException e) {
            throw new RepException(RepException.ERROR_GET_DATA,e.getMessage());
        }
        return media;
    }



    @Override
    public List<String> getInfoOfAllMedia() throws RepException {
        List<String> info = new ArrayList<String>();
        String sql = "SELECT path FROM media ORDER BY path";
        try {
            stmt = conn.prepareStatement(sql);
            rs = stmt.executeQuery();
            while (rs.next()){
                info.add(rs.getString("path"));
            }
            rs.close();;
            stmt.close();
        } catch (SQLException e) {
            throw new RepException(RepException.ERROR_GET_DATA,e.getMessage());
        }
        return info;
    }



    private void setFavorite(String path,boolean bool) throws RepException {
        String sql="UPDATE media SET  favorite=? WHERE path=?";
        try {
            stmt = conn.prepareStatement(sql);
            stmt.setBoolean(1, bool);
            stmt.setString(2,path);
            stmt.executeUpdate();
            stmt.close();
        } catch (SQLException e) {
            throw  new RepException(RepException.ERROR_SET_DATA," error to add favorite");
        }

    }
    @Override
    public void addToFavorite(MediaFiles media) throws RepException {
        if (mediaFilesExist(media.getPath())){
            setFavorite(media.getPath(),true);
        } else {
            throw  new RepException(RepException.ERROR_DATA_NOT_FOUND,"not found media");
        }

    }

    @Override
    public void removeFromFavorite(MediaFiles media) throws RepException {
        if (mediaFilesExist(media.getPath())){
            setFavorite(media.getPath(), false);
        } else {
            throw  new RepException(RepException.ERROR_DATA_NOT_FOUND,"not found media");
        }
    }

    @Override
    public List<String> getInfoOfFavoriteMedia() throws RepException {
        List<String> info = new ArrayList<String>();
        String sql = "SELECT path FROM media WHERE media.favorite!=0 ORDER BY path";
        try {
            stmt = conn.prepareStatement(sql);
            rs = stmt.executeQuery();
            while (rs.next()){
                info.add(rs.getString("path"));
            }
            rs.close();
            stmt.close();
        } catch (SQLException e) {
            throw new RepException(RepException.ERROR_GET_DATA,e.getMessage());
        }
        return info;
    }

    @Override
    public List<String> search(String text) throws RepException {
        List<String> info = new ArrayList<String>();
        String sql = "SELECT path FROM media WHERE media.path LIKE ? ORDER BY path";
        try {
            stmt = conn.prepareStatement(sql);
            stmt.setString(1,"%"+text+"%");
            rs = stmt.executeQuery();
            while (rs.next()){
                info.add(rs.getString("path"));
            }
            rs.close();
            stmt.close();
        } catch (SQLException e) {
            throw new RepException(RepException.ERROR_GET_DATA,e.getMessage());
        }
        return info;
    }
}
