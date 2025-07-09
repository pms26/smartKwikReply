package com.smartKwikReply.Controller;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/api/email-reply")
public class EmailReplyController {

    @GetMapping("/reply")
    public ResponseEntity<?> getEmailReply(){
        return ResponseEntity.ok("Email reply functionality is not implemented yet.");
    }
}
