package ro.siit.java10.evp.Serialization;

import ro.siit.java10.evp.Dealership;

import java.io.*;
import java.util.List;

public interface DealershipLoader {

    List<Dealership> loadDealerships() throws IOException;

    public static BufferedReader getBufferedReader(File file) throws Exception {

        return new BufferedReader(new InputStreamReader(new FileInputStream(file), "UTF-8"));
    }
}
