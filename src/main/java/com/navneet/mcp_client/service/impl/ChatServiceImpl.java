package com.navneet.mcp_client.service.impl;

import com.navneet.mcp_client.constants.AgentConstants;
import com.navneet.mcp_client.models.ChatResponse;
import com.navneet.mcp_client.service.ChatService;
import java.util.Arrays;
import lombok.extern.slf4j.Slf4j;
import org.springframework.ai.chat.client.ChatClient;
import org.springframework.ai.openai.OpenAiChatModel;
import org.springframework.ai.tool.ToolCallbackProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author navneet.prabhakar
 */
@Slf4j
@Service
public class ChatServiceImpl implements ChatService {

  private final ChatClient chatClient;

  @Autowired
  public ChatServiceImpl(OpenAiChatModel openAiChatModel, ToolCallbackProvider toolCallbackProvider,
                         TimeServiceImpl timeService){
    // Log all registered MCP tools
    Arrays.stream(toolCallbackProvider.getToolCallbacks())
        .forEach(
            t -> {
              log.info("MCP Server Tool found: {}", t.getToolDefinition());
            });
    this.chatClient=ChatClient.builder(openAiChatModel)
        .defaultToolCallbacks(toolCallbackProvider)
        .defaultTools(timeService)
        .build();
  }

  @Override
  public ChatResponse statelessChatWithToolCall(String message) {
    Long startTime=System.currentTimeMillis();
    log.info("Received message: {}", message);
    // LLM will invoke tool calls as needed
    String response=chatClient.prompt().system(AgentConstants.SYSTEM_PROMPT)
        .user(message).call().content();
    Long endTime=System.currentTimeMillis();
    log.info("Response received in {} ms: response: {}",(endTime-startTime), response);
    return ChatResponse.builder()
        .response(response)
        .timeTaken(endTime-startTime)
        .build();
  }


}
