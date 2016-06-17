package MediaCatalog.Utils;

import MediaCatalog.services.MediaRep;
import MediaCatalog.services.RepException;
import MediaCatalog.services.SQLRep;

public class RepFactory {
    private static MediaRep mediaRepository = null;

    public static MediaRep getInstance() throws RepException {
        if (mediaRepository == null)
            mediaRepository = new SQLRep();
        return  mediaRepository;
    }
}