package saveXML;

import game.demiurge.Demiurge;
import game.demiurge.DungeonConfiguration;

import java.io.File;

public interface SaveXML {
    void saveDemiurge(Demiurge demiurge,String filePath);
}
