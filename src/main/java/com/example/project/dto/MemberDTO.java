package com.example.project.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class MemberDTO {
    private Long memberId;
    private String memberEmail;
    private String memberPassword;
    private String memberNickname;
    private String memberPhone;
    private Long memberLevel;
}
