package com.github.aetherialmist.yaml.data;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class NestedData {

    private int intKey;
    private List<String> stringListKey;

}
