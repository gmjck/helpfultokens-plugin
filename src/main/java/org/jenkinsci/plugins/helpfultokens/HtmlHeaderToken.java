package org.jenkinsci.plugins.helpfultokens;

import hudson.Extension;
import hudson.model.AbstractBuild;
import hudson.model.TaskListener;
import org.jenkinsci.plugins.tokenmacro.TokenMacro;
import org.jenkinsci.plugins.tokenmacro.DataBoundTokenMacro;
import org.jenkinsci.plugins.tokenmacro.MacroEvaluationException;
import java.io.IOException;


@Extension
public class HtmlHeaderToken extends DataBoundTokenMacro {
	
	@Override
	public boolean acceptsMacroName(String macroName) {
		return macroName.equals("HT_HTML_HEADER");
	}
	
	@Override
    public String evaluate(AbstractBuild<?, ?> build, TaskListener listener, String macroName)
    		throws MacroEvaluationException, IOException, InterruptedException {
		if (areDependentTokensDefined()) {			
	    	return
	    			"${HT_HTML_STYLE}\n" +
	    			"\n" +
	    			"<table>\n" +
	    			"<tr><td colspan=\"2\"><h1 class=\"${HT_BUILD_RESULT}\"><a href=\"${PROJECT_URL}\">${PROJECT_NAME}</a> ${HT_BUILD_RESULT}</h1></td></tr>\n" +
	    			"<tr><td>Build:</td><td><a href=\"${BUILD_URL}\">${BUILD_URL}</a></td></tr>\n" +
	    			"<tr><td>Date:</td><td>${HT_BUILD_TIMESTAMP}</td></tr>\n" +
	    			"<tr><td>Revision:</td><td>${SVN_REVISION}</td></tr>\n" +
	    			"</table>\n" +
	    			"<br/>\n";
		}
		else {
			return "[HT-Missing definitions for nested token macros]";
		}
    }
	
	@Override
	public boolean hasNestedContent() {
		return true;
	}
	
	private boolean areDependentTokensDefined() {
		boolean isProjUrlDefined = false;
		boolean isProjNameDefined = false;
		boolean isBuildUrlDefinde = false;
		boolean isSvnRevDefined = false;
		
		for (TokenMacro tm : TokenMacro.all()) {
			isProjUrlDefined |= tm.acceptsMacroName("PROJECT_URL");
			isProjNameDefined |= tm.acceptsMacroName("PROJECT_NAME");
			isBuildUrlDefinde |= tm.acceptsMacroName("BUILD_URL");
			isSvnRevDefined |= tm.acceptsMacroName("SVN_REVISION");
		}
		
		return (isProjUrlDefined && isProjNameDefined && isBuildUrlDefinde && isSvnRevDefined);
	}
}
