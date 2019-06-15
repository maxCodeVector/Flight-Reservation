package util;

import plane.DataAdapter;

public class FileDataLoader implements DataAdapter{
    private String sourcePath;
    private static DataAdapter inst;
    private FileDataLoader(){
        try {
//            InputStream in = DataAdapter.class.getResourceAsStream("../resource/configuration.properties");
            sourcePath = DataAdapter.class.getResource("../resource").getPath();
//                new BufferedInputStream(new FileInputStream(filePath));

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static DataAdapter getInstance(){
        if(inst==null)
            inst = new FileDataLoader();
        return inst;
    }


    @Override
    public String getAdminPath() {
        return sourcePath+"/Admin.dat";
    }

    @Override
    public String getFlightPath() {
        return sourcePath+"/Flight.dat";
    }

    @Override
    public String getOrderPath() {
        return sourcePath+"/Order.dat";
    }

    @Override
    public String getPackagerPath() {
        return sourcePath+"/Passenger.dat";
    }

    @Override
    public String getPlaneImgPath() {
        return sourcePath+"/plane.jpg";
    }

    @Override
    public String getSkyImgPath() {
        return sourcePath+"/sky.jpg";
    }
}
