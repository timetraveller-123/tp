package seedu.pharmhub.ui;

import javafx.fxml.FXML;
import javafx.scene.control.TextArea;
import javafx.scene.layout.Region;
import javafx.scene.layout.StackPane;

import static java.util.Objects.requireNonNull;

/**
 * A ui for the status bar that is displayed at the header of the application.
 */
public class InfoDisplay extends UiPart<Region> {
    private static final String FXML = "InfoDisplay.fxml";

    @FXML
    private StackPane infoContent;

    public InfoDisplay() {
        super(FXML);
    }

    public void attach(UiPart<Region> component) {
        infoContent.getChildren().clear();
        infoContent.getChildren().add(component.getRoot());
    }

}
