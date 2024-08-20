package com.example.server_9dokme.inquiring.service;


import com.example.server_9dokme.inquiring.dto.request.InquireRequestDto;
import com.example.server_9dokme.inquiring.dto.response.InquireDto;
import com.example.server_9dokme.inquiring.dto.response.InquireListDto;
import com.example.server_9dokme.inquiring.entity.Inquire;
import com.example.server_9dokme.inquiring.repository.InquireRepository;
import com.example.server_9dokme.member.dto.response.BookDto;
import com.example.server_9dokme.member.entity.Member;
import com.example.server_9dokme.member.repository.MemberRepository;
import jakarta.servlet.http.HttpSession;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

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

    public Page<InquireDto> getInquireList(int pageNo)
    {
        Pageable pageable = PageRequest.of(pageNo,10);
        Page<Inquire> inquireList = inquireRepository.findAll(pageable);

        Page<InquireDto> InquireDtoPage = inquireList.map(inquire -> new InquireDto(
                inquire.getInquireId(),
                inquire.getUserId(),
                inquire.getTitle(),
                inquire.getContent()));

        return InquireDtoPage;
    }

    public void deleteInquire(Long inquireId){
        if (inquireRepository.existsById(inquireId)) {
            inquireRepository.deleteById(inquireId);
        } else {
            throw new RuntimeException("Inquire with ID " + inquireId + " not found");
        }
    }
}
