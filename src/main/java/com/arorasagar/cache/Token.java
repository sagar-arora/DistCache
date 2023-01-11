package com.arorasagar.cache;

import lombok.*;

@NoArgsConstructor
@Builder
@Getter
@EqualsAndHashCode
@AllArgsConstructor
public class Token {
    private String token;
    private String rowkey;
}
