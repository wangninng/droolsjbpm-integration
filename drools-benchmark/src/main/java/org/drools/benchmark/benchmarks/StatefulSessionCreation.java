/*
 * Copyright 2015 Red Hat, Inc. and/or its affiliates.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * 
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
*/

package org.drools.benchmark.benchmarks;

import org.drools.benchmark.BenchmarkDefinition;
import org.kie.api.KieBase;
import org.kie.api.runtime.KieSession;

public class StatefulSessionCreation extends AbstractBenchmark {

    private final KieSession[] kSessions;
    private static KieBase kbase;

    public StatefulSessionCreation(int sessionNumber) {
        this.kSessions = new KieSession[sessionNumber];
    }

    public void init(BenchmarkDefinition definition, boolean isFirst) {
        if (isFirst) {
            kbase = createKnowledgeBase(createKnowledgeBuilder("licenseApplication.drl"));
        }
    }

    public void execute(int repNr) {
        for (int i = 0; i < kSessions.length; i++) {
            kSessions[i] = kbase.newKieSession();
        }
        for (int i = 0; i < kSessions.length; i++) {
            kSessions[i].dispose();
        }
    }

    public StatefulSessionCreation clone() {
        return new StatefulSessionCreation(kSessions.length);
    }
}
