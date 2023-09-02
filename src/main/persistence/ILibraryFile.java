package persistence;

import model.Library;

import java.io.IOException;

// an interface that allows saving/loading the entire library
public interface ILibraryFile {

    // EFFECTS: sets the path to write to /read from
    void setFile(String pathToDir, String name);

    // EFFECTS:
    void writeLibrary(Library library);

    void clearDisk();

    Library readLibrary() throws IOException;

}
