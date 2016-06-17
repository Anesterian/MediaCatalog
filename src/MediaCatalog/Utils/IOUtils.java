package MediaCatalog.Utils;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

import MediaCatalog.MediaFiles;

public class IOUtils {
    public static MediaFiles inputMediaFiles(String filePath) throws IOException {
        MediaFiles media;
        File file = new File(filePath);
        if (file.exists()&&file.isFile()&& file.canRead()){
            FileInputStream  fin = new FileInputStream(file);
            byte data[] = new byte[fin.available()];
            fin.read(data, 0, fin.available());
            fin.close();
            media = new MediaFiles();
            media.setName(file.getName());
            media.setData(data);

        } else {
            throw new FileNotFoundException(file.getPath()+" file not found");
        }
        return media;
    }

    public static void outputMediaFiles(MediaFiles media, String filePath) throws IOException {
        File file = new File(filePath.concat("\\").concat(media.getPath()));
        file.getParentFile().mkdirs();
        FileOutputStream fout = new FileOutputStream(file);
        fout.write(media.getData());
        fout.flush();
        fout.close();
    }
}
