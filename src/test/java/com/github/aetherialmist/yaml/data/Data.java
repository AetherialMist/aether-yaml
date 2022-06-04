package com.github.aetherialmist.yaml.data;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@lombok.Data
@NoArgsConstructor
@AllArgsConstructor
public class Data {

    private String stringKey;
    private NestedData nestedData;

}
