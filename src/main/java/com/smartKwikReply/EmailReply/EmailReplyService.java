package com.smartKwikReply.EmailReply;

import com.smartKwikReply.EmailReply.entity.EmailRequest;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
public class EmailReplyService {
    @Value("${gemini.url}")
    private String geminiUrl;
    @Value("${gemini.key}")
    private String geminiKey;


    public String generateEmailReply(EmailRequest emailRequest){
        String prompt=buildPrompt(emailRequest);

        Map<String,Object> requestBody= Map.of("contents", new Object[]
                                        {
                                            Map.of("parts" ,new Object[]
                                                {
                                                Map.of("text", prompt)
                                                }
                                         )});

        return null;
    }

    private String buildPrompt(EmailRequest emailRequest) {
        StringBuilder promptBuilder = new StringBuilder();
        promptBuilder.append("You are a smart email reply assistant. ");
        if(null != emailRequest.getTone() && !emailRequest.getTone().isEmpty()) {
            promptBuilder.append("Please respond in a ").append(emailRequest.getTone()).append(" tone. ");
        }
        promptBuilder.append("Original email: ").append(emailRequest.getContent());
        return promptBuilder.toString();
    }
}
