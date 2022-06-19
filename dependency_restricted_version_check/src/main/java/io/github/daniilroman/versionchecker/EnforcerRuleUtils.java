package io.github.daniilroman.versionchecker;

import org.apache.maven.enforcer.rule.api.EnforcerRuleHelper;
import org.apache.maven.project.MavenProject;
import org.codehaus.plexus.component.configurator.expression.ExpressionEvaluationException;

public class EnforcerRuleUtils {

    public static MavenProject getMavenProject(final EnforcerRuleHelper helper) {
        try {
            return (MavenProject) helper.evaluate("${project}");
        } catch (ExpressionEvaluationException e) {
            throw new IllegalStateException("Failed to get maven project", e);
        }
    }
}
