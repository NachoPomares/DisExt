package es.ufv.dis.final2022.front;

import com.vaadin.flow.component.Key;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.button.ButtonVariant;
import com.vaadin.flow.component.combobox.ComboBox;
import com.vaadin.flow.component.dependency.CssImport;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.html.Label;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.server.PWA;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * A sample Vaadin view class.
 * <p>
 * To implement a Vaadin view just extend any Vaadin component and
 * use @Route annotation to announce it in a URL as a Spring managed
 * bean.
 * Use the @PWA annotation make the application installable on phones,
 * tablets and some desktop browsers.
 * <p>
 * A new instance of this class is created for every new user and every
 * browser tab/window.
 */
@Route
@PWA(name = "Vaadin Application",
        shortName = "Vaadin App",
        description = "This is an example Vaadin application.",
        enableInstallPrompt = false)
@PageTitle("Examen Final DIS 04/07/2023")
@CssImport("./styles/shared-styles.css")
@CssImport(value = "./styles/vaadin-text-field-styles.css", themeFor = "vaadin-text-field")
public class MainView extends VerticalLayout {
    public MainView(@Autowired PokemonService service) {
        HorizontalLayout inputs = new HorizontalLayout();
        VerticalLayout results = new VerticalLayout();
        ComboBox<String> comboBox = new ComboBox<>("Selecciona uno...");
        comboBox.setAllowCustomValue(false);
        comboBox.setItems("Todos los pokemons", "Por nombre", "Por tipo");
        comboBox.setHelperText("Selecciona el tipo de petición");
        Grid<Pokemon> grid = new Grid<>(Pokemon.class, true);
        grid.addColumn(Pokemon::getName).setHeader("Nombre");
        grid.addColumn(Pokemon::getAttack).setHeader("Ataque");
        grid.addColumn(Pokemon::getDefense).setHeader("Defensa");
        grid.addColumn(Pokemon::getTipo1).setHeader("Tipo1");
        grid.addColumn(Pokemon::getSpeedDefense).setHeader("Tipo2");
        TextField datos = new TextField("Nombre/Tipo");
        datos.addThemeName("bordered");
        inputs.add(comboBox, datos);
        Button boton1 = new Button("Lee caracter",
                e -> {
                    String tipoPeticion = comboBox.getValue();
                    String dato = datos.getValue();
                    try {
                        results.removeAll();
                        if (tipoPeticion.equals("Por nombre")){
                            results.add(service.leePokemonPorNombre(dato));
                        }
                        else if (tipoPeticion.equals("Por tipo")){
                            results.add(service.leePokemonPorTipo(dato));
                        }
                        else if (tipoPeticion.equals("Todos los pokemons")){
                            grid.setItems(service.leePokemons());
                            results.add(grid);
                        }
                    } catch (Exception ex) {
                    }
                });
        boton1.addThemeVariants(ButtonVariant.LUMO_PRIMARY);
        boton1.addClickShortcut(Key.ENTER);
        // Use custom CSS classes to apply styling. This is defined in shared-styles.css.
        addClassName("centered-content");
        add(inputs, boton1, results);
    }

}