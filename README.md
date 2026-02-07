# MCP Client with Spring AI

A Spring Boot application that integrates OpenAI's GPT models with the Model Context Protocol (MCP) to provide AI-powered chat functionality with tool calling capabilities.

## 🚀 Features

- **OpenAI Integration**: Leverages GPT-4.1-nano for intelligent chat responses
- **MCP Client Support**: Connects to MCP servers to extend AI capabilities with custom tools
- **Tool Callbacks**: Automatic tool discovery and execution via MCP protocol
- **RESTful API**: Simple HTTP endpoints for stateless chat interactions
- **Observability**: Built-in logging for prompts, completions, and errors
- **Modern Stack**: Spring Boot 3.5.10, Spring AI 1.1.2, Java 21

## 📋 Prerequisites

- Java 21 or higher
- Maven 3.9+
- OpenAI API key
- MCP server running on `http://localhost:8081/` (or configure your own)

## 🛠️ Installation

1. **Clone the repository**
   ```bash
   git clone <repository-url>
   cd mcp-client
   ```

2. **Set up environment variables**
   ```bash
   export OPENAI_API_KEY=your_openai_api_key_here
   ```

3. **Build the project**
   ```bash
   ./mvnw clean install
   ```

## ⚙️ Configuration

The application is configured via `src/main/resources/application.yaml`:

```yaml
spring:
  ai:
    openai:
      api-key: ${OPENAI_API_KEY}
      chat:
        options:
          model: gpt-4.1-nano
          temperature: 0.7
    mcp:
      client:
        streamable-http:
          connections:
            server1:
              url: http://localhost:8081/
```

### Key Configuration Options

- **OpenAI Model**: Default is `gpt-4.1-nano`, can be changed to other GPT models
- **Temperature**: Set to `0.7` for balanced creativity/determinism
- **MCP Server URL**: Configure the endpoint where your MCP server is running

## 🚦 Running the Application

### Start the MCP Server First

**Important**: Ensure your MCP server is running on `http://localhost:8081/` before starting this application.

If you encounter the error:
```
Factory method 'mcpSyncClients' threw exception with message: 
Client failed to initialize by explicit API call
```

This means the MCP server is not reachable. Verify:
1. The MCP server is running
2. The URL in `application.yaml` is correct
3. There are no network/firewall issues

### Start the Application

```bash
./mvnw spring-boot:run
```

The application will start on `http://localhost:8080`

## 📡 API Endpoints

### Chat - Stateless with Tool Call

**Endpoint**: `POST /v1/chat/stateless`

**Request**:
```bash
curl -X POST http://localhost:8080/v1/chat/stateless \
  -H "Content-Type: text/plain" \
  -d "What is the weather like today?"
```

**Response**:
```json
{
  "conversationId": null,
  "response": "The current weather is...",
  "timeTaken": 1250
}
```

**Fields**:
- `response`: The AI-generated response
- `timeTaken`: Time taken to process the request in milliseconds
- `conversationId`: Currently unused (reserved for future stateful conversations)

## 🏗️ Project Structure

```
mcp-client/
├── src/
│   ├── main/
│   │   ├── java/com/navneet/mcp_client/
│   │   │   ├── McpClientApplication.java       # Main Spring Boot application
│   │   │   ├── constants/
│   │   │   │   └── AgentConstants.java         # System prompts and constants
│   │   │   ├── controller/
│   │   │   │   └── ChatController.java         # REST API endpoints
│   │   │   ├── models/
│   │   │   │   ├── ChatRequest.java            # Request DTOs
│   │   │   │   └── ChatResponse.java           # Response DTOs
│   │   │   └── service/
│   │   │       ├── ChatService.java            # Service interface
│   │   │       └── impl/
│   │   │           └── ChatServiceImpl.java    # Chat service implementation
│   │   └── resources/
│   │       └── application.yaml                # Application configuration
│   └── test/
│       └── java/com/navneet/mcp_client/
│           └── McpClientApplicationTests.java  # Integration tests
├── pom.xml                                      # Maven dependencies
└── README.md                                    # This file
```

## 🔧 Key Dependencies

- **Spring Boot 3.5.10**: Core framework
- **Spring AI 1.1.2**: AI integration layer
  - `spring-ai-starter-mcp-client`: MCP protocol support
  - `spring-ai-starter-model-openai`: OpenAI integration
  - `@Tool` annotation support: Enables automatic tool discovery and invocation by AI models
- **Lombok**: Reduces boilerplate code
- **Spring Web**: RESTful API support

## 📚 Service Components

### ChatService
Handles AI-powered chat interactions with MCP tool callbacks support.

### TimeService
Provides time and date utility functions exposed as tools to the AI model:
- Get current time/date in various formats
- Convert between different time representations (milliseconds, seconds, formatted strings)
- All methods are annotated with `@Tool` for automatic Spring AI discovery

## 🐛 Troubleshooting

### MCP Client Initialization Failed

**Error**: `Factory method 'mcpSyncClients' threw exception`

**Solutions**:
1. Ensure MCP server is running and accessible
2. Check the URL in `application.yaml`
3. Verify network connectivity to the MCP server
4. Review MCP server logs for errors

### OpenAI API Errors

**Error**: Authentication or rate limit issues

**Solutions**:
1. Verify `OPENAI_API_KEY` environment variable is set correctly
2. Check your OpenAI account for API quota/credits
3. Ensure the model name is correct and accessible with your API key

### Tool Callbacks Not Working

**Solutions**:
1. Check MCP server is exposing tools correctly
2. Review application logs for tool discovery messages
3. Ensure `toolcallback.enabled` is set to `true` in configuration

## 📝 Development

### Running Tests

```bash
./mvnw test
```

### Building for Production

```bash
./mvnw clean package
java -jar target/mcp-client-0.0.1-SNAPSHOT.jar
```

## 🤝 Contributing

1. Fork the repository
2. Create a feature branch (`git checkout -b feature/amazing-feature`)
3. Commit your changes (`git commit -m 'Add amazing feature'`)
4. Push to the branch (`git push origin feature/amazing-feature`)
5. Open a Pull Request

## 📄 License

This project is licensed under the terms specified in the [LICENSE](LICENSE) file.

## 🔗 References

- [Spring AI Documentation](https://docs.spring.io/spring-ai/reference/)
- [Model Context Protocol (MCP)](https://docs.spring.io/spring-ai/reference/api/mcp/mcp-client-boot-starter-docs.html)
- [OpenAI API Documentation](https://platform.openai.com/docs)
- [Spring Boot Documentation](https://docs.spring.io/spring-boot/docs/current/reference/html/)

## 👤 Author

**navneet.prabhakar**

---

*Built with ❤️ using Spring AI and MCP*
