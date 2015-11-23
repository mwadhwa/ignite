/*
 * Licensed to the Apache Software Foundation (ASF) under one or more
 * contributor license agreements.  See the NOTICE file distributed with
 * this work for additional information regarding copyright ownership.
 * The ASF licenses this file to You under the Apache License, Version 2.0
 * (the "License"); you may not use this file except in compliance with
 * the License.  You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package org.apache.ignite.internal.processors.cache.transactions;

import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import org.apache.ignite.internal.util.typedef.F;
import org.apache.ignite.internal.util.typedef.internal.S;
import org.jetbrains.annotations.Nullable;

/**
 *
 */
public class IgniteTxRemoteSingleStateImpl extends IgniteTxRemoteStateAdapter {
    /** */
    private IgniteTxEntry entry;

    /** {@inheritDoc} */
    @Override public void addWriteEntry(IgniteTxKey key, IgniteTxEntry e) {
        this.entry = e;
    }

    /** {@inheritDoc} */
    @Override public void clearEntry(IgniteTxKey key) {
        if (entry != null && entry.txKey().equals(key))
            entry = null;
    }

    /** {@inheritDoc} */
    @Override public IgniteTxEntry entry(IgniteTxKey key) {
        if (entry != null && entry.txKey().equals(key))
            return entry;

        return null;
    }

    /** {@inheritDoc} */
    @Override public boolean hasWriteKey(IgniteTxKey key) {
        return entry != null && entry.txKey().equals(key);
    }

    /** {@inheritDoc} */
    @Override public Set<IgniteTxKey> readSet() {
        return Collections.emptySet();
    }

    /** {@inheritDoc} */
    @Override public Set<IgniteTxKey> writeSet() {
        if (entry != null) {
            HashSet<IgniteTxKey> set = new HashSet<>(3, 0.75f);

            set.add(entry.txKey());

            return set;
        }
        else
            return Collections.<IgniteTxKey>emptySet();
    }

    /** {@inheritDoc} */
    @Override public Collection<IgniteTxEntry> writeEntries() {
        return entry != null ? Arrays.asList(entry) : Collections.<IgniteTxEntry>emptyList();
    }

    /** {@inheritDoc} */
    @Override public Collection<IgniteTxEntry> readEntries() {
        return Collections.emptyList();
    }

    /** {@inheritDoc} */
    @Override public Map<IgniteTxKey, IgniteTxEntry> writeMap() {
        return entry != null ? F.asMap(entry.txKey(), entry) :
            Collections.<IgniteTxKey, IgniteTxEntry>emptyMap();
    }

    /** {@inheritDoc} */
    @Override public Map<IgniteTxKey, IgniteTxEntry> readMap() {
        return Collections.emptyMap();
    }

    /** {@inheritDoc} */
    @Override public boolean empty() {
        return entry == null;
    }

    /** {@inheritDoc} */
    @Override public Collection<IgniteTxEntry> allEntries() {
        return entry != null ? Arrays.asList(entry) : Collections.<IgniteTxEntry>emptyList();
    }

    /** {@inheritDoc} */
    @Nullable @Override public IgniteTxEntry singleWrite() {
        return entry;
    }

    /** {@inheritDoc} */
    public String toString() {
        return S.toString(IgniteTxRemoteSingleStateImpl.class, this);
    }
}
