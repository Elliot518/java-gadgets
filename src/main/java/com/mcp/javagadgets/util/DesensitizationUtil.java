package com.mcp.javagadgets.util;

import com.mcp.javagadgets.model.DesensitizationConfig;

import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class DesensitizationUtil {
  private static final DesensitizationConfig config = ConfigReader.getDesensitizationConfig();
  private static final List<DesensitizationConfig.FieldRule> FIELD_RULES = config.getDesensitizeFields();
  private static final DesensitizationConfig.FieldRule DEFAULT_RULE = config.getDefaultRule();

  public static String desensitizeText(String inputText) {
    if (inputText == null || inputText.trim().isEmpty()) {
      return inputText;
    }

    String result = inputText;
    for (DesensitizationConfig.FieldRule rule : FIELD_RULES) {
      result = desensitizeField(result, rule);
    }
    return result;
  }

  private static String desensitizeField(String text, DesensitizationConfig.FieldRule rule) {
    // 支持JSON格式（带引号）和普通格式（无引号）的字段匹配
    String regex = "(?i)(?:\"|')?(\\b" + rule.getFieldName() + ")(?:\"|')?:\\s*([^,\\n}]+)";
    Pattern pattern = Pattern.compile(regex);
    Matcher matcher = pattern.matcher(text);

    StringBuffer result = new StringBuffer();
    while (matcher.find()) {
      String fieldName = matcher.group(1);
      String originalValue = matcher.group(2).trim();
      String fieldPrefix = matcher.group(0).replace(originalValue, "").trim();
      String desensitizedValue = desensitizeValue(originalValue, rule);

      matcher.appendReplacement(result, fieldPrefix + " " + desensitizedValue);
    }
    matcher.appendTail(result);
    return result.toString();
  }

  private static String desensitizeValue(String originalValue, DesensitizationConfig.FieldRule rule) {
    int length = originalValue.length();
    int keepPrefix = rule.getKeepPrefix();
    int keepSuffix = rule.getKeepSuffix();
    String replaceChar = rule.getReplaceChar();

    if (length <= keepPrefix + keepSuffix) {
      return generateReplaceChars(replaceChar, length);
    }

    String prefix = originalValue.substring(0, keepPrefix);
    String suffix = originalValue.substring(length - keepSuffix);
    String middle = generateReplaceChars(replaceChar, length - keepPrefix - keepSuffix);

    return prefix + middle + suffix;
  }

  private static String generateReplaceChars(String replaceChar, int length) {
    if (replaceChar == null || replaceChar.isEmpty()) {
      replaceChar = "*";
    }
    StringBuilder sb = new StringBuilder();
    for (int i = 0; i < length; i++) {
      sb.append(replaceChar);
    }
    return sb.toString();
  }

  public static String desensitizeValueWithDefaultRule(String originalValue) {
    return desensitizeValue(originalValue, DEFAULT_RULE);
  }
}
