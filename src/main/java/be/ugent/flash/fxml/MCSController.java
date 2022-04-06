package be.ugent.flash.fxml;

import be.ugent.flash.Part;
import be.ugent.flash.Question;
import be.ugent.flash.db.DataAccessException;
import be.ugent.flash.db.DataAccessProvider;
import javafx.event.ActionEvent;
import javafx.scene.control.Button;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.scene.text.TextFlow;

import java.util.ArrayList;

public class MCSController extends AbstractController {
    public GridPane partBox;

    private ArrayList<Part> parts;

    public MCSController(Question question, DataAccessProvider dataAccessProvider, boolean wasCorrect) {
        super(question, dataAccessProvider, wasCorrect);
        try {
            parts = dataAccessProvider.getDataAccessContext().getPartsDAO().getParts(question.questionId());
        } catch (DataAccessException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void initialize() {
        super.initialize();
        for(int i = 0; i < parts.size(); i++) {
            String letter = ""+(char) (65 + i);
            Button button = new Button(letter);
            button.setOnAction(this::answer);
            button.setUserData(i);
            partBox.getChildren().add(button);
            partBox.add(button, 0, i);
            partBox.add(new TextFlow(new Text(parts.get(i).part())), 1, i);
        }
    }

    @Override
    public String getFXML() {
        return "MCS.fxml";
    }

    @Override
    public void answer(ActionEvent event) {
        Button button = (Button)event.getSource();
        correct = question.correctAnswer().equals(button.getUserData() + "");
    }
}
