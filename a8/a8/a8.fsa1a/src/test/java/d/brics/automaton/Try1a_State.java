/**
 * State - StatePair
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
public class Try1a_State {

	static Logger logger = Logger.getLogger(Try1a_State.class);

	@Test
	public void t2a() {
		logger.info("t1a");

		boolean bAccept = true;
		State s1a = new State();
		s1a.setAccept(bAccept);
		logger.info(s1a.toString());
		
	}

	@Test
	public void t2b() {
		System.out.println("t1b");
		
	}

	@Test
	public void t2c() {
		System.out.println("t1c");		
	}

	
	@Test
	public void t3a_statepair() {
		System.out.println("t3a");		
		
		State s1a = new State();
		State s2a = new State();
		StatePair sp = new StatePair(s1a,s2a);
		Assert.assertEquals( 0, s1a.compareTo(sp.getFirstState()) );
		Assert.assertEquals( 0,s2a.compareTo(sp.getSecondState()) );
		
	}
	
	@Test
	public void t4a_state_transition() {
		
		
		
	}
	
	

}