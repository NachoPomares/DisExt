package es.ufv.dis.final2022.back;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.util.ArrayList;

@RestController
public class PokemonController {
    @GetMapping("/pokemons")
    public ArrayList<Pokemon> pokemons() throws IOException {
        ArrayList<Pokemon> listaPokemons = new LectorJson().leeFicheroJson("./back/src/main/resources/pokemonConId.json");
        return listaPokemons;
    }

    @GetMapping("/pokemon/porNombre/{nombre}")
    public ResponseEntity<Pokemon> getPorNombre(@PathVariable String nombre) throws IOException {
        ArrayList<Pokemon> listaPokemons = new LectorJson().leeFicheroJson("./back/src/main/resources/pokemonConId.json");
        Pokemon encontrado = null;
        for (Pokemon pokemon : listaPokemons){
            if (pokemon.getName().equalsIgnoreCase(nombre)){
                encontrado = pokemon;
            }
        }
        return new ResponseEntity<>(encontrado, HttpStatus.OK);
    }

    @GetMapping("/pokemon/porTipo/{tipo}")
    public ArrayList<Pokemon> getPorTipo(@PathVariable String tipo) throws IOException {
        ArrayList<Pokemon> listaPokemons = new LectorJson().leeFicheroJson("./back/src/main/resources/pokemonConId.json");
        ArrayList<Pokemon> listaEncontrados = new ArrayList<>();
        for (Pokemon pokemon : listaPokemons){
            if (pokemon.getTipo1().equalsIgnoreCase(tipo) || pokemon.getTipo2().equalsIgnoreCase(tipo)){
                listaEncontrados.add(pokemon);
            }
        }
        return listaEncontrados;
    }

}
