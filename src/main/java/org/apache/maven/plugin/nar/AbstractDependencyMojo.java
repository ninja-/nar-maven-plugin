package org.apache.maven.plugin.nar;

/*
 * Licensed to the Apache Software Foundation (ASF) under one
 * or more contributor license agreements.  See the NOTICE file
 * distributed with this work for additional information
 * regarding copyright ownership.  The ASF licenses this file
 * to you under the Apache License, Version 2.0 (the
 * "License"); you may not use this file except in compliance
 * with the License.  You may obtain a copy of the License at
 *
 *  http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing,
 * software distributed under the License is distributed on an
 * "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
 * KIND, either express or implied.  See the License for the
 * specific language governing permissions and limitations
 * under the License.
 */

import java.util.List;

import org.apache.maven.artifact.repository.ArtifactRepository;
import org.apache.maven.artifact.resolver.ArtifactResolver;
import org.apache.maven.plugin.MojoExecutionException;
import org.apache.maven.plugin.MojoFailureException;

/**
 * @author Mark Donszelmann
 */
public abstract class AbstractDependencyMojo
    extends AbstractNarMojo
{

    /**
     * @parameter expression="${localRepository}"
     * @required
     * @readonly
     */
    private ArtifactRepository localRepository;

    protected final ArtifactRepository getLocalRepository()
    {
        return localRepository;
    }

    /**
     * Artifact resolver, needed to download the attached nar files.
     *
     * @component role="org.apache.maven.artifact.resolver.ArtifactResolver"
     * @required
     * @readonly
     */
    private ArtifactResolver artifactResolver;

	protected final ArtifactResolver getArtifactResolver()
    {
        return artifactResolver;
    }

    /**
     * Remote repositories which will be searched for nar attachments.
     *
     * @parameter expression="${project.remoteArtifactRepositories}"
     * @required
     * @readonly
     */
    private List remoteArtifactRepositories;

    protected final List /* ArtifactRepository */ getRemoteArtifactRepositories()
    {
        return remoteArtifactRepositories;
    }

    protected final NarManager getNarManager()
        throws MojoFailureException, MojoExecutionException
    {
        return new NarManager( getLog(), getLocalRepository(), getRemoteArtifactRepositories(), getArtifactResolver(),
                               getMavenProject(), getArchitecture(), getOS(), getLinker() );
    }
}
