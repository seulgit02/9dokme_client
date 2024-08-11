package com.example.server_9dokme.inquiring.service;


import com.example.server_9dokme.inquiring.dto.request.InquireRequestDto;
import com.example.server_9dokme.inquiring.entity.Inquire;
import com.example.server_9dokme.inquiring.repository.InquireRepository;
import com.example.server_9dokme.member.entity.Member;
import com.example.server_9dokme.member.repository.MemberRepository;
import jakarta.servlet.http.HttpSession;
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


    public Inquire createInquire(InquireRequestDto inquireRequest, long userId) {
        Inquire inquire = new Inquire();

        inquire.setTitle(inquireRequest.getTitle());
        inquire.setContent(inquireRequest.getContent());
        inquire.setUserId(userId);
        return inquireRepository.save(inquire);
    }
}
