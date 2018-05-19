/**
 * 
 */
package d.brics.automaton;

import org.apache.log4j.Logger;

import org.junit.Assert;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;

import dk.brics.automaton.State;
import dk.brics.automaton.StatePair;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class Try1b_StatePair {

	static Logger logger = Logger.getLogger(Try1b_StatePair.class);

	@Test
	public void t2a() {
		logger.info("t1a");
		
		State s1a = new State();
		State s2a = new State();
		StatePair sp = new StatePair(s1a,s2a);
		Assert.assertEquals( 0, s1a.compareTo(sp.getFirstState()) );
		Assert.assertEquals( 0,s2a.compareTo(sp.getSecondState()) );
	}

	@Test
	public void t2b() {
		logger.info("t1b");
		
		
	}

	@Test
	public void t1c() {
		System.out.println("t1c");
	}

}