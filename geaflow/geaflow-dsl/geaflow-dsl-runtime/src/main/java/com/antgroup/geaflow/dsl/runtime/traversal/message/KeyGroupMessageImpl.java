/*
 * Copyright 2023 AntGroup CO., Ltd.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 */

package com.antgroup.geaflow.dsl.runtime.traversal.message;

import com.antgroup.geaflow.dsl.common.data.Row;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class KeyGroupMessageImpl implements KeyGroupMessage {

    private final List<Row> groupRows;

    public KeyGroupMessageImpl(List<Row> groupRows) {
        this.groupRows = Objects.requireNonNull(groupRows);
    }

    @Override
    public MessageType getType() {
        return MessageType.KEY_GROUP;
    }

    @Override
    public IMessage combine(IMessage other) {
        List<Row> combineRows = new ArrayList<>(groupRows);
        KeyGroupMessage groupMessage = (KeyGroupMessage) other;
        combineRows.addAll(groupMessage.getGroupRows());
        return new KeyGroupMessageImpl(combineRows);
    }

    @Override
    public KeyGroupMessage copy() {
        return new KeyGroupMessageImpl(new ArrayList<>(groupRows));
    }

    @Override
    public List<Row> getGroupRows() {
        return groupRows;
    }
}
