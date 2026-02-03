package com.navneet.mcp_client.controller;

import com.navneet.mcp_client.models.ChatResponse;
import com.navneet.mcp_client.service.ChatService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author navneet.prabhakar
 */
@RestController
@RequestMapping("v1/chat")
public class ChatController {

  @Autowired private ChatService chatService;


  /**
   * Perform a stateless chat with tool call.
   *
   * @param message The input message for the chat.
   * @return ChatResponse containing the chat response.
   */
  @PostMapping("/stateless" )
  public ChatResponse statelessChatWithToolCall(@RequestBody String message){
    return chatService.statelessChatWithToolCall(message);
  }


}
