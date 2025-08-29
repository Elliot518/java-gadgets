package com.mcp.javagadgets.util;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.mcp.javagadgets.model.DesensitizationConfig;

import java.io.InputStream;

/**
 * @author: Elliot
 * @description:
 * @date: Created in 13:11 2025/8/27
 * @modified by:
 */
public class ConfigReader {
  private static DesensitizationConfig config;
  private static final String CONFIG_PATH = "desensitization-config.json";

  static {
    try {
      ObjectMapper objectMapper = new ObjectMapper();
      InputStream inputStream = ConfigReader.class.getClassLoader().getResourceAsStream(CONFIG_PATH);

      if (inputStream == null) {
        throw new RuntimeException("脱敏配置文件未找到！路径：" + CONFIG_PATH);
      }

      config = objectMapper.readValue(inputStream, DesensitizationConfig.class);
      System.out.println("脱敏配置加载成功，共加载" + config.getDesensitizeFields().size() + "个字段规则");

    } catch (Exception e) {
      throw new RuntimeException("加载脱敏配置文件失败！", e);
    }
  }

  public static DesensitizationConfig getDesensitizationConfig() {
    return config;
  }
}
