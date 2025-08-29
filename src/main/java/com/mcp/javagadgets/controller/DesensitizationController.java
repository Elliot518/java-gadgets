package com.mcp.javagadgets.controller;

import com.mcp.javagadgets.MainApplication;
import com.mcp.javagadgets.util.DesensitizationUtil;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TextArea;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;

import java.io.IOException;

public class DesensitizationController {
    @FXML
    private TextArea contentTextArea;

    @FXML
    private void handleDesensitize(ActionEvent event) {
        try {
            String originalText = contentTextArea.getText();

            if (originalText == null || originalText.trim().isEmpty()) {
                showAlert(AlertType.WARNING, "提示", "请先粘贴需要脱敏的文本！");
                return;
            }

            String desensitizedText = DesensitizationUtil.desensitizeText(originalText);
            contentTextArea.setText(desensitizedText);
            showAlert(AlertType.INFORMATION, "脱敏完成", "文本已成功脱敏！");

        } catch (Exception e) {
            showAlert(AlertType.ERROR, "脱敏失败", "处理过程中出现错误：" + e.getMessage());
            e.printStackTrace();
        }
    }

    @FXML
    private void handleClearText(ActionEvent event) {
        contentTextArea.clear();
        contentTextArea.setPromptText("请粘贴需要脱敏的文本（格式如：phone: 13812345678, idCard: 110101199001011234）");
    }

    @FXML
    private void handleBackToMain(ActionEvent event) throws IOException {
        MainApplication.loadMainView();
    }

    private void showAlert(AlertType alertType, String title, String content) {
        Alert alert = new Alert(alertType);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(content);
        alert.setWidth(300);
        alert.showAndWait();
    }
}