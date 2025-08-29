package com.mcp.javagadgets.controller;

import com.mcp.javagadgets.MainApplication;
import javafx.event.ActionEvent;
import java.io.IOException;

public class MainController {
    public void handleDataDesensitization(ActionEvent event) throws IOException {
        MainApplication.loadDesensitizationView();
    }
}