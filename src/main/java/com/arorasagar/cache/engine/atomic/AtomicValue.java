package com.arorasagar.cache.engine.atomic;

public abstract class AtomicValue {
    protected long time;

    public abstract byte[] externalize();

    public long getTime() {
        return time;
    }

    public void setTime(long time) {
        this.time = time;
    }
}
