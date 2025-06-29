package com.example.sendEmail.mailMapper;

import com.example.sendEmail.dtos.MailRequest;
import com.example.sendEmail.dtos.MailResponse;
import com.example.sendEmail.models.MailModel;

public class MailMapper {

    public static MailModel toEntity(MailRequest mailRequest) {
        return MailModel.builder()
                .name(mailRequest.getName())
                .date(mailRequest.getDate())
                .to(mailRequest.getTo())
                .subject(mailRequest.getSubject())
                .build();
    }

    public static MailResponse toResponse(MailModel mailModel) {
        return new MailResponse(
               mailModel.getName(),
                mailModel.getTo()

        );
    }
}
