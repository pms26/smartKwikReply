package com.smartKwikReply.EmailReply;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.smartKwikReply.EmailReply.entity.EmailRequest;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.Map;

@Service
public class EmailReplyService {
    private final WebClient webClient;

    public EmailReplyService(WebClient.Builder webClientBuilder) {
        this.webClient = webClientBuilder.build();
    }

    @Value("${GEMINI_URL}")
    private String geminiUrl;
    @Value("${GEMINI_API_KEY}")
    private String geminiKey;


    public String generateEmailReply(EmailRequest emailRequest) {
        String prompt = buildPrompt(emailRequest);

        // Prepare the request body for the Gemini API

        Map<String, Object> requestBody = Map.of("contents", new Object[]
                {
                        Map.of("parts", new Object[]
                                {
                                        Map.of("text", prompt)
                                }
                        )});

        return responseBody(requestBody);
    }

    // Build the prompt for the Gemini API based on the email request

    private String buildPrompt(EmailRequest emailRequest) {
        StringBuilder promptBuilder = new StringBuilder();
        promptBuilder.append("You are a smart email reply assistant. ");
        if (null != emailRequest.getTone() && !emailRequest.getTone().isEmpty()) {
            promptBuilder.append("Please respond in a ").append(emailRequest.getTone()).append(" tone. ");
        }
        promptBuilder.append("Original email: ").append(emailRequest.getContent());
        return promptBuilder.toString();
    }

    //Response from Gemini API
    public String responseBody(Map<String, Object> requestBody) {
        String response = webClient.post().uri(geminiUrl + geminiKey)
                .header("Content-Type", "application/json")
                .bodyValue(requestBody)
                .retrieve()
                .bodyToMono(String.class)
                .block();
        return extractedResponse(response);
    }

    // Extract the response from the Gemini API response

    private String extractedResponse(String response) {
        try{
            ObjectMapper objectMapper = new ObjectMapper();
            JsonNode rootNode= objectMapper.readTree(response);
            return rootNode.path("candidates").get(0).path("content").path("parts").get(0).path("text").asText();

        } catch (Exception e) {
           throw new RuntimeException("Error processing Gemini API response: " + e.getMessage(), e);
        }
    }
}

