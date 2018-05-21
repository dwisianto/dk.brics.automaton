package d.ls.core.utl.fsa.aset;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

//import org.apache.lucene.util.automaton.Automaton;
//import org.apache.lucene.util.automaton.BasicOperations;
import org.junit.Test;

import dk.brics.automaton.Automaton;
import dk.brics.automaton.BasicOperations;

public class AutomatonSet1Test {
	
	@Test
	public void t1() {
		
		AutomatonSet1 set = new AutomatonSet1();
		for (int i = Integer.MAX_VALUE; i != 0; --i) {
			String s = Integer.toHexString(i);
			set.add(s);
			if (i % 1000 == 0) {
				System.out.format("Writing %d Size %s/%s\r", i, set.numNodes(), set.numTransitions());

			}
		}
		
	}
	
	@Test
	public void test() {
		String[] firstNames = {"Doug", "John"};
		String[] lastNames = {"Turnbull", "Berryman"};
		
		Automaton auto = FirstNameLastNameValidator.createNameValidator(firstNames, lastNames);
		assertTrue(BasicOperations.run(auto, "Doug   Turnbull"));
		assertTrue(BasicOperations.run(auto, "Doug\t\tBerryman"));		
		assertTrue(BasicOperations.run(auto, "Turnbull,  Doug"));
		assertTrue(BasicOperations.run(auto, "Berryman, John"));		

		assertFalse(BasicOperations.run(auto, "DougBerryman"));		
	}
	

}
