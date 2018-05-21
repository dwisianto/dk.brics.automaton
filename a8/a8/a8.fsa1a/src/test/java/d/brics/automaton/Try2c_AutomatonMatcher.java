/**
 * 
 */
package d.brics.automaton;


import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.log4j.Logger;
import org.junit.Assert;
import org.junit.FixMethodOrder;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runners.MethodSorters;

import dk.brics.automaton.Automaton;
import dk.brics.automaton.AutomatonMatcher;
import dk.brics.automaton.RegExp;
import dk.brics.automaton.RunAutomaton;


@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class Try2c_AutomatonMatcher {

	static Logger logger = Logger.getLogger(Try2c_AutomatonMatcher.class);
	

	private static final String MANDATORY_REGEXP = "[abc]+";
	private static final RunAutomaton MANDATORY_AUTOMATON = new RunAutomaton(new RegExp(MANDATORY_REGEXP).toAutomaton());
	private static final Pattern MANDATORY_PATTERN = Pattern.compile(MANDATORY_REGEXP);
	
	
	
	// 
	private static final String OPTIONAL_REGEXP = "[abc]*";
	private static final RunAutomaton OPTIONAL_AUTOMATON = new RunAutomaton(new RegExp(OPTIONAL_REGEXP).toAutomaton());
	private static final Pattern OPTIONAL_PATTERN = Pattern.compile(OPTIONAL_REGEXP);
	
	
	@Test
	public void t1a_plainTest() {
        final String testString = "aa bb abc ";
        Assert.assertFalse("Fully matched '" + testString + "' using " + OPTIONAL_REGEXP, OPTIONAL_AUTOMATON.run(testString));
        logger.info("Fully matched '" + testString + "' using " + OPTIONAL_REGEXP + " " +  OPTIONAL_AUTOMATON.run(testString));
        
        // [] compareMatchers(OPTIONAL_PATTERN, OPTIONAL_AUTOMATON, testString); 
        final AutomatonMatcher automatonMatcher = OPTIONAL_AUTOMATON.newMatcher(testString);
		final Matcher javaMatcher = OPTIONAL_PATTERN.matcher(testString);
		
		boolean bMatchJava = javaMatcher.find();
		boolean bMatchAuto = automatonMatcher.find();
		
		do { 			
			/*
			final boolean automaton_found = automatonMatcher.find();
			
			logger.info("Java matched, but automaton didn't. Java start: " + javaMatcher.start() + " Java end: " + javaMatcher.end()+ " automaton_found "+ automaton_found);
			Assert.assertTrue("Java matched, but automaton didn't. Java start: " + javaMatcher.start() + " Java end: " + javaMatcher.end(), automaton_found);
			Assert.assertEquals(javaMatcher.group(), automatonMatcher.group());
			Assert.assertEquals(javaMatcher.start(), automatonMatcher.start());
			Assert.assertEquals(javaMatcher.end(), automatonMatcher.end());
			*/
			String sGroupJava = javaMatcher.group();
			String sGroupAuto = automatonMatcher.group();
			
			if( sGroupJava.length() > 0 ) { 
				logger.info(" group : " + sGroupJava + " " +  sGroupAuto );			
				logger.info(" start : " + javaMatcher.start() + " " +  automatonMatcher.start() );
				logger.info(" end   : " + javaMatcher.end() + " " +  automatonMatcher.end() );
			}
			
			bMatchJava = javaMatcher.find();
			bMatchAuto = automatonMatcher.find();

		} while( bMatchJava && bMatchAuto ); 
		
		
	}

	
	/**
	 * This is derived from the example bug that Hans-Martin submitted. 
	 * The results of the test were altered to match Java's {@code Matcher}.
	 */
	@Ignore
	@Test
	public void t1a_hansMartinBug() {
		logger.info( " t1a_hansMartinBug ");
		
        final String testString = " ";
		final Matcher matcher = Pattern.compile(OPTIONAL_REGEXP).matcher(" ");
		boolean found = matcher.find();
		logger.info( " Jv found : "+ found);
		Assert.assertTrue("While using Java didn't get empty match against '" + testString + "' using " + OPTIONAL_REGEXP, found);
		Assert.assertEquals(0, matcher.start());
		Assert.assertEquals(0, matcher.end());
		
		found = matcher.find();
		logger.info( " Jv found : "+ found);
		Assert.assertTrue("While using Java didn't get empty match against '" + testString + "' using " + OPTIONAL_REGEXP, found);
		Assert.assertEquals(1, matcher.start());
		Assert.assertEquals(1, matcher.end());

		found = matcher.find();
		logger.info( " Jv found : "+ found);
		Assert.assertFalse("While using Java found a third empty match against '" + testString + "' using " + OPTIONAL_REGEXP + " start was: " + matcher.start() + " end was: " + matcher.end(), found);
		Assert.assertEquals(1, matcher.start());
		Assert.assertEquals(1, matcher.end());


		Assert.assertFalse("Fully matched '" + testString + "' using " + OPTIONAL_REGEXP, OPTIONAL_AUTOMATON.run(testString));
		
        AutomatonMatcher automatonMatcher = OPTIONAL_AUTOMATON.newMatcher(testString);
		found = automatonMatcher.find();
		logger.info( " Au found : "+ found);
		Assert.assertTrue("Didn't get empty match against '" + testString + "' using " + OPTIONAL_REGEXP, found);
		Assert.assertEquals(0, automatonMatcher.start());
		Assert.assertEquals(0, automatonMatcher.end());
		
		found = automatonMatcher.find();
		logger.info( " Au found : "+ found);
		Assert.assertTrue("Didn't get empty match against '" + testString + "' using " + OPTIONAL_REGEXP, found);
		Assert.assertEquals(1, automatonMatcher.start());
		Assert.assertEquals(1, automatonMatcher.end());

		found = automatonMatcher.find();
		logger.info( " Au found : "+ found);
		Assert.assertFalse("Found a third empty match against '" + testString + "' using " + OPTIONAL_REGEXP, found);
		// Java's Matcher is inconsistent here.  When an the pattern
		// is fully optional and the last match is the empty string
		// at the end of the input, then group() is null and start()
		// and end() are their last valid values. In other cases
		// when the matcher has found its last match start(),
		// group(), and end() throw IllegalStateException as the
		// javadocs indicate that they should
		// If we wanted to match Java's Matcher exactly then these
		// tests should be uncommented.
		//Assert.assertEquals(1, automatonMatcher.start());
		//Assert.assertEquals(1, automatonMatcher.end());
		//Assert.assertEquals(null, automatonMatcher.group());
		
	}
	
	
	
	

	@Ignore
	@Test
	public void t2a() {
		logger.info("t2a");

		// [] 
		Automaton a = Automaton.makeEmpty();
		//Assert.assertEquals("initial state: 0 state 0 [reject]:", a.toString());		 
		logger.debug(a.toDot());
		logger.debug(a.toString());

		// [] 
		Automaton a2 = Automaton.makeString("car"); 		 
		logger.info(a2.toDot() );
		
		Automaton a3Re = new RegExp("[a-z]*").toAutomaton();

	}

	@Ignore
	@Test
	public void t2b() {
		logger.debug("t2b");
		
		StringBuilder sb = new StringBuilder();
		sb.append("dwisiantomansjur");		
		String sTxt = sb.toString(); 
		sTxt.replaceAll("\\r\\n", "\n"); // DOS to Unix
		sTxt = sTxt.replaceAll("\\r", "\n"); // Mac to Unix
		sTxt = sTxt.replaceAll("^[ \\t]+$", "");
		logger.info(sTxt);
		
		RunAutomaton ra = new RunAutomaton(new RegExp("[a-zA-Z0-9]*", RegExp.NONE).toAutomaton());
		AutomatonMatcher am = ra.newMatcher(sTxt);
		
		//int start = 0;
		while (am.find()) {
			// The offsets from start (offset into orig. string = start + anchorStart)
			int iStart = am.start();
			int iEnd = am.end();
			Matcher m = inlineLink.matcher(am.group());
			if (!m.find()) { continue; }
			
			logger.info( "s:" + iStart + "e:"+ iEnd );
			//String linkText = m.group(2);
			//String url = m.group(3);
			//String title = m.group(6);
			//int linkTextLength = linkText.length();
		}		

	}

	@Ignore
	@Test
	public void t1c() {
		logger.debug("t2c");
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
	@Ignore
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
	
	
	/**
	 * compare Java vs Automaton matcher
	 * 
	 * @param pattern
	 * @param automaton
	 * @param testString
	 */
	private void compareMatchers(final Pattern pattern, final RunAutomaton automaton, final String testString) {
		
        final AutomatonMatcher automatonMatcher = automaton.newMatcher(testString);
		final Matcher javaMatcher = pattern.matcher(testString);
		
		while(javaMatcher.find()) {
			
			final boolean automaton_found = automatonMatcher.find();
			logger.info("Java matched, but automaton didn't. Java start: " + javaMatcher.start() + " Java end: " + javaMatcher.end()+ " automaton_found "+ automaton_found);
			Assert.assertTrue("Java matched, but automaton didn't. Java start: " + javaMatcher.start() + " Java end: " + javaMatcher.end(), automaton_found);
			Assert.assertEquals(javaMatcher.group(), automatonMatcher.group());
			Assert.assertEquals(javaMatcher.start(), automatonMatcher.start());
			Assert.assertEquals(javaMatcher.end(), automatonMatcher.end());
		}
		
		if(automatonMatcher.find()) {
			Assert.fail("Java didn't match, but automaton did. Automaton start: " + automatonMatcher.start() + " Automaton end: " + automatonMatcher.end() + " matched sequence: '" + automatonMatcher.group() + "'");
		}
	}
	


}