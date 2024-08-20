package com.example.server_9dokme.inquiring.dto.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.data.domain.Page;

import java.util.List;

@Data
@AllArgsConstructor
public class InquireListDto {
    private Page<InquireDto> inquireList;
}
