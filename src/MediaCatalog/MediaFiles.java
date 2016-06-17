package MediaCatalog;

public class MediaFiles {
	
	private byte[] data;              //initializing variables
	private String name;
	private String catalog = "";
	private boolean favorite;
	//methods needed for work
	public boolean IsFavorite(){
		return favorite;
	}
    public void setFavorite(boolean favorite) {
        this.favorite = favorite;
    }

    public String getCatalog() {
        return catalog;
    }

    public void setCatalog(String catalog) {
        this.catalog = catalog;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public byte[] getData() {
        return data;
    }

    public void setData(byte[] data) {
        this.data = data;
    }

    public String getPath() {
        if (catalog=="") return name;
        return catalog.concat("\\").concat(name);
    }

    public void setPath(String path) {

        if(path.lastIndexOf('\\')==-1)
            this.name=path;
        else {
        this.name = path.substring(path.lastIndexOf('\\')+1);
            this.catalog = path.substring(0,path.lastIndexOf('\\'));
        }
    }
}
