package com.navneet.mcp_client.service;


import com.navneet.mcp_client.models.ChatResponse;

/**
 * @author navneet.prabhakar
 */
public interface ChatService {


  /**
   * Perform a stateless chat with tool call.
   *
   * @param message The input message for the chat.
   * @return ChatResponse containing the chat response.
   */
  ChatResponse statelessChatWithToolCall(String message);


}
