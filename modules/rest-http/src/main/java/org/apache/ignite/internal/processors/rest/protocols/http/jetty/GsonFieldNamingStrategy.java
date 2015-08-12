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

package org.apache.ignite.internal.processors.rest.protocols.http.jetty;

import com.google.gson.*;
import org.apache.ignite.internal.processors.rest.*;

import java.lang.reflect.*;

/**
 * Ignite field name strategy.
 */
public class GsonFieldNamingStrategy implements FieldNamingStrategy {
    /** {@inheritDoc} */
    @Override public String translateName(Field f) {
        JsonName ann = f.getAnnotation(JsonName.class);

        return ann == null ? f.getName() : ann.value();
    }
}