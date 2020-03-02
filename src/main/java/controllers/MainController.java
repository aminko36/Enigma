package controllers;

import applications.Main;
import ciphers.Cipher;
import ciphers.impl.VigenereCipher;
import factories.impl.CipherFactory;
import file.utils.FileTool;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextInputDialog;
import javafx.scene.layout.AnchorPane;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.Arrays;
import java.util.Optional;
import java.util.ResourceBundle;

import static factories.impl.CipherFactory.*;


public class MainController implements Initializable {
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        cipherChoiceBox.setItems(possibleCipherMethods);
        cipherChoiceBox.setValue(CESAR);
        mainStage = Main.getMainStage();
    }


    private CipherFactory cipherFactory = new CipherFactory();
    private ObservableList<String> possibleCipherMethods = FXCollections.observableArrayList(Arrays.asList(CESAR, ROOT13, VIGENERE));
    private Stage mainStage;


    @FXML
    ChoiceBox<String> cipherChoiceBox;

    @FXML
    TextArea inputTextArea;

    @FXML
    public void triggerEncoding() {   //metoda dla Buttona

        String userText = inputTextArea.getText();
        if (!userText.isEmpty()) {
            String cipherType = cipherChoiceBox.getValue();
            Cipher cipher = cipherFactory.create(cipherType);
            if (cipher instanceof VigenereCipher) {
                TextInputDialog inputDialog = new TextInputDialog("Key");
                inputDialog.setHeaderText("Please choose key and remember it.");
                inputDialog.setContentText("Key: ");
                Optional<String> userInputOptional = inputDialog.showAndWait();
                if (userInputOptional.isPresent() && !userInputOptional.get().equals("")) {
                    ((VigenereCipher) (cipher)).setKey(userInputOptional.get());
                } else {
                    return;
                }
            }
            String encode = cipher.encode(userText);
            inputTextArea.setText(encode);
        }
    }

    @FXML
    public void triggerDecoding() {
        String userText = inputTextArea.getText();
        if (!userText.isEmpty()) {
            String cipherType = cipherChoiceBox.getValue();
            Cipher cipher = cipherFactory.create(cipherType);
            if (cipher instanceof VigenereCipher) {
                TextInputDialog inputDialog = new TextInputDialog("Key");
                inputDialog.setContentText("Key: ");
                inputDialog.setHeaderText("If you know the key, write it here.");
                Optional<String> userInputOptional = inputDialog.showAndWait();
                if (userInputOptional.isPresent() && !userInputOptional.get().equals("")) {
                    ((VigenereCipher) (cipher)).setKey(userInputOptional.get());
                } else {
                    return;
                }
            }
            String decode = cipher.decode(userText);
            inputTextArea.setText(decode);
        }
    }

    @FXML
    public void saveToFile() {
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Save to file");
        fileChooser.setInitialFileName("message.crpt");
        fileChooser.getExtensionFilters().addAll(
                new FileChooser.ExtensionFilter("crypt file", "*.crpt"),
                new FileChooser.ExtensionFilter("text file", "*.txt")
        );
        File fileToSave = fileChooser.showSaveDialog(null);
        if (fileToSave != null) {
            String absolutePath = fileToSave.getAbsolutePath();

            FileTool.writeFileContent(absolutePath, inputTextArea.getText());

        }
    }

    @FXML
    public void loadFile() {
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Load file");
        fileChooser.setInitialFileName("message.crpt");
        fileChooser.getExtensionFilters().addAll(
                new FileChooser.ExtensionFilter("crypt file", "*.crpt"),
                new FileChooser.ExtensionFilter("text file", "*.txt")
        );
        File fileToLoad = fileChooser.showOpenDialog(null);
        if (null != fileToLoad) {
            String fileContent = FileTool.getFileContent(fileToLoad.getAbsolutePath());
            inputTextArea.setText(fileContent);
        }
    }

    @FXML
    public void registerUser() {
        FXMLLoader loader = new FXMLLoader(this.getClass().getResource("/fxml/RegisterUser.fxml"));
        Alert addUserAlert = new Alert(Alert.AlertType.INFORMATION);
        try {
            AnchorPane userLayout = loader.load();
            addUserAlert.getDialogPane().setContent(userLayout);
            addUserAlert.showAndWait();

        } catch (IOException e) {
            System.out.println("Could not load userLayout because of " + e);
        }
    }

    @FXML
    public void closeApp() {
        Platform.exit();
        System.exit(0);

    }
}



