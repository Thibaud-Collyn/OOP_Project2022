package be.ugent.flash.db;

import be.ugent.flash.Question;

import java.util.ArrayList;

public interface QuestionsDAO {

//    Maakt een nieuwe vraag(question) aan met een optionele foto(image) en een onveranderlijk type
    Question createQuestion(String title, String textPart, byte[] imagePart, String questionType, String correctAnswer) throws DataAccessException;

//    Update een bestaande vraag met bepaalde question_id
    void updateGeneralQuestion(int id, String title, String textPart, byte[] imagePart) throws DataAccessException;

//    Üpdate het juiste antwoord van vraag met bepaalde question_id
    void updateCorrectAnswer(int qId, String correctAnswer) throws DataAccessException;

//    Verwijdert een bestaande vraag met bepaalde question_id
    void removeQuestion(int id) throws DataAccessException;

//   Return een vraag op basis van zijn question_id
    Question getQuestion(int id) throws DataAccessException;

//   Return een array list met alle vragen in de databank(gesorteerd op question_id)
    ArrayList<Question> getAllQuestions() throws DataAccessException;

}
