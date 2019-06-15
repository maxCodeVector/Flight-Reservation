package util;

import plane.DataAdapter;

public class Tool {
    public static DataAdapter getDataAdpater(){
        return FileDataLoader.getInstance();
    }
}
