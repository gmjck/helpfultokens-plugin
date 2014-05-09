package org.jenkinsci.plugins.helpfultokens;

import hudson.Extension;
import hudson.Functions;
import hudson.model.AbstractBuild;
import hudson.model.TaskListener;
import org.jenkinsci.plugins.tokenmacro.DataBoundTokenMacro;
import org.jenkinsci.plugins.tokenmacro.MacroEvaluationException;
import java.io.IOException;


@Extension
public class BuildTimestampToken extends DataBoundTokenMacro {
	
	@Override
	public boolean acceptsMacroName(String macroName) {
		return macroName.equals("HT_BUILD_TIMESTAMP");
	}
	
	@Override
    public String evaluate(AbstractBuild<?, ?> build, TaskListener listener, String macroName)
    		throws MacroEvaluationException, IOException, InterruptedException {    	
    	return Functions.rfc822Date(build.getTimestamp());
    }
}
