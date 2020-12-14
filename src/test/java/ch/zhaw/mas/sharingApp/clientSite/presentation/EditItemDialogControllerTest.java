package ch.zhaw.mas.sharingApp.clientSite.presentation;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.TextField;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.loadui.testfx.GuiTest;
import static org.loadui.testfx.Assertions.*;
import java.io.IOException;
import static org.loadui.testfx.controls.Commons.hasText;

class EditItemDialogControllerTest extends GuiTest {

    @Override
    protected Parent getRootNode() {
        Parent parent = null;
        try {
            parent = FXMLLoader.load(getClass().getResource("FXML/EditItemDialog.fxml"));
            return parent;
        } catch (IOException exp) {
            exp.printStackTrace();
        }
        return parent;
    }

    @BeforeEach
    void setUp() {
    }

    @Test
    public void testSetItemName() {
        TextField itemNameField = find("#itemNameField");
        itemNameField.setText("testItem");
        verifyThat("#itemNameField", hasText("testItem"));
    }
}