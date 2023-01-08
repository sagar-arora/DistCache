package com.arorasagar.cache;

import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Builder
@Getter
@EqualsAndHashCode
public class Token {
    private String token;
    private String rowkey;
}
