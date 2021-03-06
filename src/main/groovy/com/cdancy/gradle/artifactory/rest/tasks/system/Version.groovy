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
package com.cdancy.gradle.artifactory.rest.tasks.system

import com.cdancy.gradle.artifactory.rest.tasks.AbstractArtifactoryRestTask;

class Version extends AbstractArtifactoryRestTask {

    private def version;

    @Override
    void runRemoteCommand(artifactoryClient) {
        def api = artifactoryClient.api().systemApi()
        version = api.version()
        logger.quiet "Version: ${version?.version}"
        logger.quiet "Revision: ${version?.revision}"
        logger.quiet "Addons: ${version?.addons}"
        logger.quiet "License: ${version?.license}"
    }

    def version() { version }
}

