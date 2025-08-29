module com.mcp.javagadgets {
    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.base;
    requires javafx.graphics;

    // 添加Jackson模块依赖（关键！）
    requires com.fasterxml.jackson.databind;
    requires com.fasterxml.jackson.core;
    requires com.fasterxml.jackson.annotation;

    // 2. 关键修正：开放 model 包给 Jackson，允许反射访问（解析 JSON 需要）
    opens com.mcp.javagadgets.model to com.fasterxml.jackson.databind;

    exports com.mcp.javagadgets.controller to javafx.fxml;
    opens com.mcp.javagadgets.controller to javafx.fxml;
    exports com.mcp.javagadgets;
    opens com.mcp.javagadgets.view to javafx.fxml;
}