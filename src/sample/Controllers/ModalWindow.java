/**
 * Package which has all controllers of application.
 * */
package sample.Controllers;

import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Button;
/**
 * The class controls modal window and describe its logic.
 * */
public class ModalWindow {
/**
 * This variable helps to delete task.
 * */
    static boolean isDeleted;
/**
 * Button that describe program logic if user click it.
 * */
    @FXML
    private Button noButton;

/**
 * Method hide modal window scene
 * */
    @FXML
    void close(){
            noButton.getScene().getWindow().hide();
    }
/**
 * THis method makes isDeleted = true. This action enable to delete task and shows the scene.
 * @param event event that is given by user action on the page.
 *
 * */
    @FXML
    private void delete(Event event) {

        isDeleted = true;
        ((Node)(event.getSource())).getScene().getWindow().hide();
    }

}
