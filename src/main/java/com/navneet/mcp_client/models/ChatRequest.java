package com.navneet.mcp_client.models;

/**
 * @author navneet.prabhakar
 */

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ChatRequest {
  private String message;

}
