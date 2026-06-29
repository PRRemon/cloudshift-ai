package com.parthraval.cloudshift.ai.prompt;

public interface PromptBuilder<T> {
    String build(T input);
}