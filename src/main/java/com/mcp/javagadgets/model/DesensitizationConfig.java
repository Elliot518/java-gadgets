package com.mcp.javagadgets.model;

import java.util.List;

public class DesensitizationConfig {
  private List<FieldRule> desensitizeFields;
  private FieldRule defaultRule;

  public List<FieldRule> getDesensitizeFields() {
    return desensitizeFields;
  }

  public void setDesensitizeFields(List<FieldRule> desensitizeFields) {
    this.desensitizeFields = desensitizeFields;
  }

  public FieldRule getDefaultRule() {
    return defaultRule;
  }

  public void setDefaultRule(FieldRule defaultRule) {
    this.defaultRule = defaultRule;
  }

  public static class FieldRule {
    private String fieldName;
    private int keepPrefix;
    private int keepSuffix;
    private String replaceChar;

    public String getFieldName() {
      return fieldName;
    }

    public void setFieldName(String fieldName) {
      this.fieldName = fieldName;
    }

    public int getKeepPrefix() {
      return keepPrefix;
    }

    public void setKeepPrefix(int keepPrefix) {
      this.keepPrefix = keepPrefix;
    }

    public int getKeepSuffix() {
      return keepSuffix;
    }

    public void setKeepSuffix(int keepSuffix) {
      this.keepSuffix = keepSuffix;
    }

    public String getReplaceChar() {
      return replaceChar;
    }

    public void setReplaceChar(String replaceChar) {
      this.replaceChar = replaceChar;
    }
  }
}