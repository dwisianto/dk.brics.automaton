/**
 * 
 */
package d.brics.automaton;


import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.log4j.Logger;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;

import dk.brics.automaton.Automaton;
import dk.brics.automaton.AutomatonMatcher;
import dk.brics.automaton.RegExp;
import dk.brics.automaton.RunAutomaton;


@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class Try2c_AutomatonMatcher {

	static Logger logger = Logger.getLogger(Try2c_AutomatonMatcher.class);

	@Test
	public void t2a() {
		logger.info("t1a");

		// [] 
		Automaton a = Automaton.makeEmpty();
		//Assert.assertEquals("initial state: 0 state 0 [reject]:", a.toString());		 
		logger.debug(a.toDot());
		logger.debug(a.toString());

		// [] 
		Automaton a2 = Automaton.makeString("car"); 		 
		logger.info(a2.toDot() );		

	}

	@Test
	public void t2b() {
		logger.info("t1b");


	}

	@Test
	public void t1c() {
		System.out.println("t1c");
	}

	static final RunAutomaton inlineLinkAutomaton = new RunAutomaton(new RegExp("(" + // Whole match = $1
			"\\[([^\\]]*)\\]" + // Link text = $2
			"\\(" +
			"([^\\)\\\\]|(\\\\.))+" +  // 3 cases: 1. simple URL. 2. escaped right-paren in URL. 3. escaped anything in URL. 
			"\\)" +
			")", RegExp.NONE).toAutomaton());

	// Use inlineLinkAutomaton to check for whole match, then use inlineLink to capture groups
	static final Pattern inlineLink = Pattern.compile("(" + // Whole match = $1
			"\\[(.*?)\\]" + // Link text = $2
			"\\(" +
			"[ \\t]*" +
			"<?(.*?)>?" + // href = $3
			"[ \\t]*" +
			"(" +
			"(['\"])" + // Quote character = $5
			"(.*?)" + // Title = $6
			"\\5" +
			")?" +
			"\\)" +
			")", Pattern.DOTALL);	

	/**
	 * 
	 */
	@Test
	public void t1d() {
		logger.info("t1d");

		StringBuilder sb = new StringBuilder();
		// Inline-style links: [link text](url "optional title")
		AutomatonMatcher am = inlineLinkAutomaton.newMatcher(sb);
		// The offset into the entire original string 
		int start = 0;
		while (am.find()) {
			// The offsets from start (offset into orig. string = start + anchorStart)
			int anchorStart = am.start();
			int anchorEnd = am.end();
			Matcher m = inlineLink.matcher(am.group());
			if (!m.find())
				continue;
			String linkText = m.group(2);
			String url = m.group(3);
			String title = m.group(6);
			int linkTextLength = linkText.length();

			//logger.info(TAG, "pos="+am.start() + " linkText="+linkText + " url="+url + " title="+ title);

			// protect emphasis (* and _) within urls
			//          url = url.replaceAll("\\*", CHAR_PROTECTOR.encode("*"));
			//          url = url.replaceAll("_", CHAR_PROTECTOR.encode("_"));
			//urls.add(new MarkdownURL(start + anchorStart, url, linkText));
			//          StringBuffer result = new StringBuffer();
			// TODO: Show title (if any) alongside url in popup menu
			//          if (title != null) {
			//              // protect emphasis (* and _) within urls
			//              title = title.replaceAll("\\*", CHAR_PROTECTOR.encode("*"));
			//              title = title.replaceAll("_", CHAR_PROTECTOR.encode("_"));
			//              title.replaceAll("\"", """);
			//              result.append(" title=\"");
			//              result.append(title);
			//              result.append("\"");
			//          }        

		}
	}


}