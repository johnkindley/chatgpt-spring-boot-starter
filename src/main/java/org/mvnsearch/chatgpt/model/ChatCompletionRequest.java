package org.mvnsearch.chatgpt.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonRawValue;
import jakarta.annotation.Nonnull;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class ChatCompletionRequest {
    private String model = "gpt-3.5-turbo";
    private List<ChatMessage> messages;
    private Double temperature;
    private Integer n;
    @JsonProperty("top_p")
    private Integer topP;
    private Boolean stream;
    private String stop;
    @JsonProperty("max_tokens")
    private Integer maxTokens;
    @JsonProperty("presence_penalty")
    private Double presencePenalty;
    @JsonProperty("frequency_penalty")
    private Double frequencyPenalty;
    @JsonProperty("logit_bias")
    private Map<String, Object> logitBias;
    private String user;

    /**
     * functions with json array
     */
    @JsonRawValue
    private String functions;
    @JsonIgnore
    private List<String> functionNames;
    @JsonProperty("function_call")
    private Object functionCall;

    public ChatCompletionRequest() {
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public Double getTemperature() {
        return temperature;
    }

    public void setTemperature(Double temperature) {
        this.temperature = temperature;
    }

    public Integer getN() {
        return n;
    }

    public void setN(Integer n) {
        this.n = n;
    }

    public List<ChatMessage> getMessages() {
        return messages;
    }

    public void setMessages(List<ChatMessage> messages) {
        this.messages = messages;
    }

    public Integer getTopP() {
        return topP;
    }

    public void setTopP(Integer topP) {
        this.topP = topP;
    }

    public Boolean getStream() {
        return stream;
    }

    public void setStream(Boolean stream) {
        this.stream = stream;
    }

    public String getStop() {
        return stop;
    }

    public void setStop(String stop) {
        this.stop = stop;
    }

    public Integer getMaxTokens() {
        return maxTokens;
    }

    public void setMaxTokens(Integer maxTokens) {
        this.maxTokens = maxTokens;
    }

    public Double getPresencePenalty() {
        return presencePenalty;
    }

    public void setPresencePenalty(Double presencePenalty) {
        this.presencePenalty = presencePenalty;
    }

    public Double getFrequencyPenalty() {
        return frequencyPenalty;
    }

    public void setFrequencyPenalty(Double frequencyPenalty) {
        this.frequencyPenalty = frequencyPenalty;
    }

    public Map<String, Object> getLogitBias() {
        return logitBias;
    }

    public void setLogitBias(Map<String, Object> logitBias) {
        this.logitBias = logitBias;
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public String getFunctions() {
        return functions;
    }

    public void setFunctions(String functions) {
        this.functions = functions;
    }

    public Object getFunctionCall() {
        return functionCall;
    }

    public void setFunctionCall(Object functionCall) {
        if (functionCall.equals("auto") || functionCall.equals("none")) {
            this.functionCall = functionCall;
        } else if (functionCall instanceof String) {
            this.functionCall = Map.of("name", functionCall);
        } else {
            this.functionCall = functionCall;
        }
    }

    public List<String> getFunctionNames() {
        return functionNames;
    }

    public void setFunctionNames(List<String> functionNames) {
        this.functionNames = functionNames;
    }

    public void addFunction(String functionName) {
        if (this.functionNames == null) {
            this.functionNames = new ArrayList<>();
        }
        this.functionNames.add(functionName);
    }

    public static ChatCompletionRequest of(@Nonnull String userMessage) {
        return of(null, userMessage, null);
    }

    public static ChatCompletionRequest functions(@Nonnull String userMessage, List<String> functionNames) {
        final ChatCompletionRequest request = new ChatCompletionRequest();
        request.setModel("gpt-3.5-turbo-0613");
        request.setFunctionNames(functionNames);
        request.addMessage(ChatMessage.userMessage(userMessage));
        return request;
    }

    public static ChatCompletionRequest of(String systemMessage, @Nonnull String userMessage, String assistantMessage) {
        ChatCompletionRequest request = new ChatCompletionRequest();
        if (systemMessage != null && !systemMessage.isEmpty()) {
            request.addMessage(ChatMessage.systemMessage(systemMessage));
        }
        request.addMessage(ChatMessage.userMessage(userMessage));
        if (assistantMessage != null && !assistantMessage.isEmpty()) {
            request.addMessage(ChatMessage.assistantMessage(assistantMessage));
        }
        return request;
    }

    public void addMessage(ChatMessage message) {
        if (this.messages == null) {
            this.messages = new ArrayList<>();
        }
        this.messages.add(message);
    }
}
