package org.apache.maven.plugins.enforcer;

import org.apache.maven.enforcer.rule.api.EnforcerRule;
import org.apache.maven.enforcer.rule.api.EnforcerRuleException;
import org.apache.maven.enforcer.rule.api.EnforcerRuleHelper;
import org.apache.maven.model.Dependency;
import org.apache.maven.plugin.logging.Log;
import org.apache.maven.project.MavenProject;

import java.util.Arrays;
import java.util.List;
import java.util.regex.Pattern;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class RestrictDependencyVersionRule implements EnforcerRule {

    private boolean enabled = true;

    private final List<Pattern> restrictedVersionRegexes =
            Stream.of("([|\\()*(]|\\))")
                    .map(Pattern::compile)
                    .collect(Collectors.toList());
    private final List<String> restrictedVersionStrings = Arrays.asList("LATEST", "RELEASE");

    public void execute(EnforcerRuleHelper helper) throws EnforcerRuleException {
        Log log = helper.getLog();
        if (!enabled) {
            log.info("Dependency check on restriction disabled.");
            return;
        }

        MavenProject mavenProject = EnforcerRuleUtils.getMavenProject(helper);
        List<Dependency> dependencies = mavenProject.getDependencies();

       for (Dependency d : dependencies) {
            String version = d.getVersion();
            boolean isRestrictedVersion = restrictedVersionRegexes.stream().anyMatch(p -> p.matcher(version).find());
            if (isRestrictedVersion || restrictedVersionStrings.contains(version)) {
                throw new EnforcerRuleException(String.format("Dependency %s has restricted version %s.", d, version));
            }
        }
    }

    public String getCacheId() {
        return "true";
    }

    public boolean isCacheable() {
        return false;
    }

    public boolean isResultValid(EnforcerRule rule) {
        return false;
    }

    public void isEnabled(boolean isEnabled) {
        this.enabled = isEnabled;
    }

}
