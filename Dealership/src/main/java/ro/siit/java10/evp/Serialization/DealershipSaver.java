package ro.siit.java10.evp.Serialization;

import ro.siit.java10.evp.Dealership;

import java.io.*;
import java.util.List;

public interface DealershipSaver {

    void saveDealerships(List<Dealership> deals);
}
