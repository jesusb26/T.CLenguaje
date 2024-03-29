/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package vista;

/**
 *
 * @author David
 */
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import tclenguaje.TCLenguaje;

public class VistaController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Button btnGenerarPalabras;

    @FXML
    private Button btnNoTerminal;

    @FXML
    private Button btnSimboloInicial;

    @FXML
    private Button btnTerminal;

    @FXML
    private ImageView imagen;

    @FXML
    private Label txtCodigos;

    @FXML
    private Label txtNoTerminalesTitulo;

    @FXML
    private TextField txtNoterminales;

    @FXML
    private TextArea txtPalabrasFormadas;

    @FXML
    private Label txtPalabrasFormaladas;

    @FXML
    private TextField txtReglas;

    @FXML
    private TextField txtSimboloInicial;

    @FXML
    private Label txtTerminalInicialTitulo;

    @FXML
    private TextField txtTerminales;

    @FXML
    private Label txtTerminalesTitulo;

    @FXML
    private Label txtTerminalesTitulo1;

    @FXML
    private Label txtTitulo;

    @FXML
    private TextArea txtNoTerminalesAgregados;

    @FXML
    private TextArea txtTerminalesAgregados;

    TCLenguaje gramatica;

    private void mostrarAlerta(String titulo, String mensaje) {
        Alert alerta = new Alert(Alert.AlertType.ERROR);
        alerta.setTitle(titulo);
        alerta.setContentText(mensaje);
        alerta.showAndWait();
    }

    @FXML
    void DefinirSimboloInicial(ActionEvent event) {
        try {
            gramatica.simboloInicial(txtSimboloInicial.getText());
        } catch (Exception e) {
            mostrarAlerta("Error al definir símbolo inicial", e.getMessage());
        }
    }

    @FXML
    void GenerarPalabras(ActionEvent event) {
        try {
            String palabras = gramatica.generarPalabras();
            txtPalabrasFormadas.setText(palabras);
        } catch (Exception e) {
            mostrarAlerta("Error al generar el lenguaje", e.getMessage());
        }

    }
    String noTerminal = "";

    @FXML
    void agregarNoTerminal(ActionEvent event) {
        try {
            noTerminal += gramatica.agregarNoTerminal(txtNoterminales.getText(), txtReglas.getText());
            noTerminal += "; ";
            txtNoTerminalesAgregados.setText(noTerminal);
            txtNoterminales.clear();
            txtReglas.clear();
        } catch (Exception e) {
            mostrarAlerta("Error al agregar símbolo no terminal", e.getMessage());
        }
    }
    String terminales = "";

    @FXML
    void agregarTerminal(ActionEvent event) {
        try {
            gramatica.agregarTerminales(txtTerminales.getText());
            terminales += txtTerminales.getText() + ", ";
            txtTerminalesAgregados.setText(terminales);
            txtTerminales.clear();
        } catch (Exception e) {
            mostrarAlerta("Error al agregar símbolo terminal", e.getMessage());
        }
    }

    @FXML
    void initialize() {
        gramatica = new TCLenguaje();
        assert btnGenerarPalabras != null : "fx:id=\"btnGenerarPalabras\" was not injected: check your FXML file 'DiseñoInterfazTClenguaje.fxml'.";
        assert btnNoTerminal != null : "fx:id=\"btnNoTerminal\" was not injected: check your FXML file 'DiseñoInterfazTClenguaje.fxml'.";
        assert btnSimboloInicial != null : "fx:id=\"btnSimboloInicial\" was not injected: check your FXML file 'DiseñoInterfazTClenguaje.fxml'.";
        assert btnTerminal != null : "fx:id=\"btnTerminal\" was not injected: check your FXML file 'DiseñoInterfazTClenguaje.fxml'.";
        assert imagen != null : "fx:id=\"imagen\" was not injected: check your FXML file 'DiseñoInterfazTClenguaje.fxml'.";
        assert txtCodigos != null : "fx:id=\"txtCodigos\" was not injected: check your FXML file 'DiseñoInterfazTClenguaje.fxml'.";
        assert txtNoTerminalesTitulo != null : "fx:id=\"txtNoTerminalesTitulo\" was not injected: check your FXML file 'DiseñoInterfazTClenguaje.fxml'.";
        assert txtNoterminales != null : "fx:id=\"txtNoterminales\" was not injected: check your FXML file 'DiseñoInterfazTClenguaje.fxml'.";
        assert txtPalabrasFormadas != null : "fx:id=\"txtPalabrasFormadas\" was not injected: check your FXML file 'DiseñoInterfazTClenguaje.fxml'.";
        assert txtPalabrasFormaladas != null : "fx:id=\"txtPalabrasFormaladas\" was not injected: check your FXML file 'DiseñoInterfazTClenguaje.fxml'.";
        assert txtReglas != null : "fx:id=\"txtReglas\" was not injected: check your FXML file 'DiseñoInterfazTClenguaje.fxml'.";
        assert txtSimboloInicial != null : "fx:id=\"txtSimboloInicial\" was not injected: check your FXML file 'DiseñoInterfazTClenguaje.fxml'.";
        assert txtTerminalInicialTitulo != null : "fx:id=\"txtTerminalInicialTitulo\" was not injected: check your FXML file 'DiseñoInterfazTClenguaje.fxml'.";
        assert txtTerminales != null : "fx:id=\"txtTerminales\" was not injected: check your FXML file 'DiseñoInterfazTClenguaje.fxml'.";
        assert txtTerminalesTitulo != null : "fx:id=\"txtTerminalesTitulo\" was not injected: check your FXML file 'DiseñoInterfazTClenguaje.fxml'.";
        assert txtTerminalesTitulo1 != null : "fx:id=\"txtTerminalesTitulo1\" was not injected: check your FXML file 'DiseñoInterfazTClenguaje.fxml'.";
        assert txtTitulo != null : "fx:id=\"txtTitulo\" was not injected: check your FXML file 'DiseñoInterfazTClenguaje.fxml'.";
    }

}
