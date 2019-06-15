package util;

import controller.DataAdapter;

public class Tool {
    public static DataAdapter getDataAdpater(){
        return FileDataLoader.getInstance();
    }
}
