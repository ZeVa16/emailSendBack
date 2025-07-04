package com.example.sendEmail.models;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class PayLoad{
    private String name;
    private String email;
    private String created_at;
    private List<QuestionAnswer> questionAnswerList;

}
