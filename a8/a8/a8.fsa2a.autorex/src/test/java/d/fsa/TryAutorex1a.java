package d.fsa;

import java.io.BufferedWriter;
import java.io.FileWriter;

import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import dk.brics.automaton.Automaton;
import dk.brics.automaton.RegExp;

public class TryAutorex1a {

	final static Logger logger = LoggerFactory.getLogger(TryAutorex1a.class);

	@Test
	public void testArex() {

		String test = ".*X.*";
		String test2 = "das (X|a) ist ein test";

		Automaton a = new RegExp(test).toAutomaton();
		Automaton b = new RegExp(test2).toAutomaton();
		logger.info(a.toDot());		
		logger.info(b.toDot());

		Automaton b_minus_a = b.minus(a);
		Automaton b_union_a = b.union(a); 
		
		writeStr("target/tryAutorex1a.dot", b_minus_a.toDot());

	}

	protected void writeStr(String sFileName, String sContent ) {

		try (BufferedWriter buffer = new BufferedWriter(new FileWriter(sFileName))) {
			buffer.write(sContent);
		} catch (Exception e) {
			e.printStackTrace();
		}    	

	}
	
	@Test
	public void t3a_matcher() {
		
		
		
	}


}
