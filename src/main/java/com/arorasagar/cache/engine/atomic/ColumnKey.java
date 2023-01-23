package com.arorasagar.cache.engine.atomic;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Builder
@Getter
@Setter
@ToString
public class ColumnKey extends AtomicKey {
    public static final char SERIALIZE_CHAR = 'C';
    private String column;


    @Override
    public byte[] externalize() {
        return (SERIALIZE_CHAR + getColumn()).getBytes();
    }

    @Override
    public int compareTo(AtomicKey o) {
        return 0;
    }
}
