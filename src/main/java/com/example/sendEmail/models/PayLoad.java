package com.example.sendEmail.models;

import com.fasterxml.jackson.annotation.JsonProperty;
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
    private String event;
    private String time;
    @JsonProperty("questions_and_answers")
    private List<QuestionAnswer> questionAnswerList;

}
