package be.ugent.flash.beheersinterface.parteditor_factories;

import be.ugent.flash.Question;
import be.ugent.flash.beheersinterface.EditorController;
import be.ugent.flash.beheersinterface.parteditors.PartEditor;
import be.ugent.flash.db.DataAccessProvider;
import javafx.scene.layout.VBox;

// algemene factory interface voor part specifieke editors
public interface PartEditorFactory {
    PartEditor getPartEditor(Question question, DataAccessProvider dap, VBox qEditorBox, EditorController editorController);
}
