package com.example.server_9dokme.inquiring.controller;

import com.example.server_9dokme.common.dto.SuccessResponse;
import com.example.server_9dokme.inquiring.dto.request.InquireRequestDto;
import com.example.server_9dokme.inquiring.dto.response.InquireDto;
import com.example.server_9dokme.inquiring.dto.response.InquireListDto;
import com.example.server_9dokme.inquiring.entity.Inquire;
import com.example.server_9dokme.inquiring.service.InquireService;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@Slf4j
@CrossOrigin(origins = "http://localhost:3000")
@RequestMapping("/api")
public class InquireController {
    @Autowired
    private InquireService inquireService;



    @PostMapping("/inquire")
    public ResponseEntity<Inquire> createInquire(@RequestBody InquireRequestDto inquireRequest, @RequestParam Long memberId) {

        Inquire createdInquire = inquireService.createInquire(inquireRequest, memberId);
        return new ResponseEntity<>(createdInquire, HttpStatus.CREATED);
    }

    @GetMapping("/admin/inquiries/{pageNo}")
    public Page<InquireDto> getInquireList(@PathVariable int pageNo){
        return inquireService.getInquireList(pageNo);
    }

    @DeleteMapping("/admin/inquiries/delete/{inquireId}")
    public ResponseEntity<Void> deleteInquire(@PathVariable Long inquireId) {
        try {
            inquireService.deleteInquire(inquireId);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (RuntimeException e) {
            log.error("Error deleting inquire with ID " + inquireId, e);
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}
