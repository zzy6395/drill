/**
 * Licensed to the Apache Software Foundation (ASF) under one
 * or more contributor license agreements.  See the NOTICE file
 * distributed with this work for additional information
 * regarding copyright ownership.  The ASF licenses this file
 * to you under the Apache License, Version 2.0 (the
 * "License"); you may not use this file except in compliance
 * with the License.  You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package org.apache.drill.exec.proto.helper;

import java.util.Arrays;
import java.util.List;
import java.util.UUID;

import org.apache.drill.exec.proto.ExecProtos.FragmentHandle;
import org.apache.drill.exec.proto.UserBitShared.QueryId;

/* Helper class around the QueryId protobuf */
public class QueryIdHelper {

  /* Generate a UUID from the two parts of the queryid */
  public static String getQueryId(QueryId queryId) {
    return (new UUID(queryId.getPart1(), queryId.getPart2())).toString();
  }

  public static QueryId getQueryIdFromString(String queryId) {
    UUID uuid = UUID.fromString(queryId);
    return QueryId.newBuilder().setPart1(uuid.getMostSignificantBits()).setPart2(uuid.getLeastSignificantBits()).build();
  }

  public static String getQueryIdentifier(FragmentHandle h) {
    return getQueryId(h.getQueryId()) + ":" + h.getMajorFragmentId() + ":" + h.getMinorFragmentId();
  }

  public static String getQueryIdentifiers(QueryId queryId, int majorFragmentId, List<Integer> minorFragmentIds) {
    String fragmentIds = minorFragmentIds.size() == 1 ? minorFragmentIds.get(0).toString() : minorFragmentIds.toString();
    return getQueryId(queryId) + ":" + majorFragmentId + ":" + fragmentIds;
  }

}
