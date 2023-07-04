package es.ufv.dis.final2022.back;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.IOException;
import java.io.Reader;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;

public class LectorJson {
    public ArrayList<Pokemon> leeFicheroJson(String fichero) throws IOException {
        try {
            //lee el fichero y lo carga en un reader
            Reader reader = Files.newBufferedReader(Paths.get(fichero));
            //convierte el array en un arraylist de users
            ArrayList<Pokemon> listaPokemon =
                    new Gson().fromJson(reader, new TypeToken<ArrayList<Pokemon>>() {
                    }.getType());
            reader.close();
            return listaPokemon;
        } catch (Exception ex) {
            ex.printStackTrace();
            return new ArrayList<>();
        }
    }
}
