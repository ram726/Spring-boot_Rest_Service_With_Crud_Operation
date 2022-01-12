package com.springboot.demo.DemoRestApi.beans;

import lombok.*;
import org.springframework.stereotype.Service;

@AllArgsConstructor
@NoArgsConstructor
@ToString
@Data
@Getter
@Service
public class StudentWrapper {
    private Long id;
    private String name;
}
