package org.jenkinsci.plugins.helpfultokens;

import hudson.Extension;
import hudson.model.AbstractBuild;
import hudson.model.TaskListener;
import org.jenkinsci.plugins.tokenmacro.DataBoundTokenMacro;
import org.jenkinsci.plugins.tokenmacro.MacroEvaluationException;
import java.io.IOException;


@Extension
public class HtmlChangesToken extends DataBoundTokenMacro {
	
	@Override
	public boolean acceptsMacroName(String macroName) {
		return macroName.equals("HT_HTML_CHANGES");
	}
	
	@Override
    public String evaluate(AbstractBuild<?, ?> build, TaskListener listener, String macroName)
    		throws MacroEvaluationException, IOException, InterruptedException {    	
    	return
    			"<table width=\"100%\">\n" +
    			"<tr><td class=\"dark\"><b>CHANGES</b></td></tr>\n" +
    			"${CHANGES, showPaths=true, " +
    				"format=\"" +
    					"<tr>" +
    						"<td class=\"med\">Revision <b>%r</b> by <b>%a</b></td>" +
    					"</tr>" +
    					"<tr>" +
    						"<td class=\"light\">%m</td>" +
    					"</tr>" +
    					"%p" +
    				"\", " +
    				"pathFormat=\"<tr><td><li>%p</li></td></tr>\" +" +
				"}\n" +
				"</table>\n";
    }

	@Override
	public boolean hasNestedContent() {
		return true;
	}
}
