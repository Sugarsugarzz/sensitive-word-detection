package casia.isiteam.sensitivedetection.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class SensitiveWord implements Serializable {

    private int id;
    private String keyword;
    private LocalDateTime insert_time;
}
