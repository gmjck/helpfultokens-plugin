package org.jenkinsci.plugins.helpfultokens;

import hudson.Extension;
import hudson.model.AbstractBuild;
import hudson.model.TaskListener;
import org.jenkinsci.plugins.tokenmacro.DataBoundTokenMacro;
import org.jenkinsci.plugins.tokenmacro.MacroEvaluationException;
import java.io.IOException;


@Extension
public class HtmlStyleToken extends DataBoundTokenMacro {
	
	@Override
	public boolean acceptsMacroName(String macroName) {
		return macroName.equals("HT_HTML_STYLE");
	}
	
	@Override
    public String evaluate(AbstractBuild<?, ?> build, TaskListener listener, String macroName)
    		throws MacroEvaluationException, IOException, InterruptedException {    	
    	return
    			"<style>\n" +
    			"body, table, td, th, p { font-family:Verdana,Helvetica,sans serif; font-size:11px; color:black; }\n" +
    			"\n" +
    			"td.dark { color:white; background-color:#0000C0; font-size:120% }\n" +
    			"td.med { color:white; background-color:#4040FF; font-size:110% }\n" +
    			"td.light { color:black; background-color:lightgray; font-size:110% }\n" +
    			"\n" +
    			"h1 { font-size: 125%; }\n" +
    			"h1.FAILURE { color:red; }\n" +
    			"h1.SUCCESS { color:blue; }\n" +
    			"\n" +
    			".hidefailFAILURE { display:none; }\n" +
    			"</style>\n";
    }
}
