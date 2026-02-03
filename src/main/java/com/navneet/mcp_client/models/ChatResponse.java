package com.navneet.mcp_client.models;

import lombok.Builder;
import lombok.Data;

/**
 * @author navneet.prabhakar
 */
@Data
@Builder
public class ChatResponse {

  private String conversationId;
  private String response;
  private Long timeTaken;

}
