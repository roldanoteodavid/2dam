package LoadTXT.loadTXTImpl;

import game.demiurge.Demiurge;
import LoadTXT.LoadTXT;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;

public class LoadTXTImpl implements LoadTXT {
    public Demiurge loadDemiurge(String filename){
        ObjectInputStream ois = null;
        try {
            ois = new ObjectInputStream(new FileInputStream(filename));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        try {
            return (Demiurge) ois.readObject();
        } catch (IOException e) {
            throw new RuntimeException(e);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }
}
