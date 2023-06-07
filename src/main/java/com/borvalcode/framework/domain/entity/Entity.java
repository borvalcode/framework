package com.borvalcode.framework.domain.entity;

import java.util.List;

public interface Entity<K extends Entity.Key> {

    K getPrimaryKey();

    List<K> getKeys();

    interface Key {}
}
