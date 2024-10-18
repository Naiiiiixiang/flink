/*
 * Licensed to the Apache Software Foundation (ASF) under one
 * or more contributor license agreements.  See the NOTICE file
 * distributed with this work for additional information
 * regarding copyright ownership.  The ASF licenses this file
 * to you under the Apache License, Version 2.0 (the
 * "License"); you may not use this file except in compliance
 * with the License.  You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package org.apache.flink.container.entrypoint;

import org.apache.flink.api.common.JobID;
import org.apache.flink.runtime.entrypoint.EntrypointClusterConfiguration;
import org.apache.flink.runtime.jobgraph.SavepointRestoreSettings;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

import java.util.Properties;

import static java.util.Objects.requireNonNull;


/** Configuration for the {@link StandaloneApplicationClusterEntryPoint}. */
// 本地应用集群入口点配置
// 1. savepoint 恢复配置
// 2. job id
// 3. job 类名
// 4. jar 包
public final class StandaloneApplicationClusterConfiguration extends EntrypointClusterConfiguration {

    @Nonnull private final SavepointRestoreSettings savepointRestoreSettings;

    @Nullable private final JobID jobId;

    @Nullable private final String jobClassName;

    @Nullable private final String[] jars;

    // 1. configDir: flink-conf.yaml 文件所在的目录
    // 2. dynamicProperties: 动态配置
    // 3. args: 命令行参数
    // 4. hostname: hostname
    // 5. restPort: rest api 端口
    // 6. savepointRestoreSettings: savepoint 恢复配置
    // 7. jobId: job id
    // 8. jobClassName: job 类名
    // 9. jars: jar 包
    StandaloneApplicationClusterConfiguration(
            @Nonnull String configDir,
            @Nonnull Properties dynamicProperties,
            @Nonnull String[] args,
            @Nullable String hostname,
            int restPort,
            @Nonnull SavepointRestoreSettings savepointRestoreSettings,
            @Nullable JobID jobId,
            @Nullable String jobClassName,
            @Nullable String[] jars) {
        super(configDir, dynamicProperties, args, hostname, restPort);
        this.savepointRestoreSettings =
                requireNonNull(savepointRestoreSettings, "savepointRestoreSettings");
        this.jobId = jobId;
        this.jobClassName = jobClassName;
        this.jars = jars;
    }

    boolean hasJars() {
        return jars != null && jars.length > 0;
    }

    @Nonnull
    SavepointRestoreSettings getSavepointRestoreSettings() {
        return savepointRestoreSettings;
    }

    @Nullable
    JobID getJobId() {
        return jobId;
    }

    @Nullable
    String getJobClassName() {
        return jobClassName;
    }

    @Nullable
    String[] getJars() {
        return jars;
    }
}
