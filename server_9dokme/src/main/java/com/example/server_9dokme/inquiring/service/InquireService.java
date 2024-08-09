package com.example.server_9dokme.inquiring.service;


import com.example.server_9dokme.inquiring.dto.request.InquireRequestDto;
import com.example.server_9dokme.inquiring.entity.Inquire;
import com.example.server_9dokme.inquiring.repository.InquireRepository;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Slf4j
@Data
@NoArgsConstructor
@Service
public class InquireService {
    @Autowired
    private InquireRepository inquireRepository;

    public Inquire createInquire(InquireRequestDto inquireRequest) {
        Inquire inquire = new Inquire();
        inquire.setTitle(inquireRequest.getTitle());
        inquire.setContent(inquireRequest.getContent());
        inquire.setUserId(inquireRequest.getUserId());
        return inquireRepository.save(inquire);
    }
}
