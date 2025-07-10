package com.smartKwikReply.EmailReply;

import com.smartKwikReply.EmailReply.entity.EmailRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/api/email-reply")
public class EmailReplyController {

    @GetMapping("/reply")
    public ResponseEntity<?> getEmailReply(@RequestBody EmailRequest emailRequest) {
        return ResponseEntity.ok("Email reply functionality is not implemented yet.");
    }
}
