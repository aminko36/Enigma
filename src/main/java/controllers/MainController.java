package controllers;

import ciphers.Cipher;
import ciphers.impl.CesarCipher;
import file.utils.FileTool;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TextArea;
import javafx.stage.FileChooser;

import java.io.File;
import java.net.URL;
import java.util.ResourceBundle;


public class MainController implements Initializable {
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
    }

    @FXML
    TextArea inputTextArea;

    @FXML
    public void triggerEncoding() {   //metoda dla Buttona

        String userText = inputTextArea.getText();
        if (!userText.isEmpty()) {
            Cipher cesarCipher = new CesarCipher();
            String encode = cesarCipher.encode(userText);
            inputTextArea.setText(encode);
        }
    }

    @FXML
    public void triggerDecoding() {
        String userText = inputTextArea.getText();
        if (!userText.isEmpty()) {
            Cipher cesarCipher = new CesarCipher();
            String decode = cesarCipher.decode(userText);
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
}



