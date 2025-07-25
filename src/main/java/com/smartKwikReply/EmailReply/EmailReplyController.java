package com.smartKwikReply.EmailReply;

import com.smartKwikReply.EmailReply.entity.EmailRequest;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

// http://localhost:9000/api/email-reply/reply
@RestController
@AllArgsConstructor
@RequestMapping("/api/email-reply")

public class EmailReplyController {

    private final EmailReplyService emailReplyService;

    @PostMapping("/reply")
    public ResponseEntity<?> getEmailReply(@RequestBody EmailRequest emailRequest) {
        String response= emailReplyService.generateEmailReply(emailRequest);
        return ResponseEntity.ok(response);
    }
}
