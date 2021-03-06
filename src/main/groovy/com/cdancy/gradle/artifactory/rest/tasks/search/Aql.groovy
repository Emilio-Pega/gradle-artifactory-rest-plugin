/*
 * Copyright 2014 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.cdancy.gradle.artifactory.rest.tasks.search

import com.cdancy.gradle.artifactory.rest.tasks.AbstractArtifactoryRestTask
import org.gradle.api.GradleException
import org.gradle.api.provider.Property
import org.gradle.api.tasks.Input

class Aql extends AbstractArtifactoryRestTask {

    @Input
    final Property<String> query = project.objects.property(String)

    private def aqlResult

    @Override
    void runRemoteCommand(artifactoryClient) {
        String tempQuery = query.orNull
        if (tempQuery?.trim()) {
            def api = artifactoryClient.api();
            aqlResult = api.searchApi().aql(tempQuery.toString());
        } else {
            throw new GradleException("`query` does not resolve to a valid String: query=${tempQuery}")
        }
    }

    def aqlResult() { aqlResult }
}

