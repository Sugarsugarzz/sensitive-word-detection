package casia.isiteam.sensitivedetection.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Sensitivity implements Serializable {

    public String content;
    public String replace;
    public List<String> words;
    public Double sensitivity;

}
