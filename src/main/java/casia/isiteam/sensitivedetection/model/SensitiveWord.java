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
    private int aid;
    private String aspect;
    private double val;
    private LocalDateTime insert_time;

    public SensitiveWord(String keyword, int aid) {
        this.keyword = keyword;
        this.aid = aid;
    }
}
