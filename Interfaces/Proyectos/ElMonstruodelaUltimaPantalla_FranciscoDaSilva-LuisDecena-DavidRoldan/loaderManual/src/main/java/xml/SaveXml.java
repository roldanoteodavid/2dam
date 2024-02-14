package xml;

import game.demiurge.Demiurge;
import game.demiurge.DungeonConfiguration;

import java.io.File;
import java.io.IOException;

public interface SaveXml {
    void save(Demiurge demiurge, DungeonConfiguration dungeonConfiguration, File file) throws IOException;
}
