/**
 * 
 */
package d.brics.automaton;


import org.apache.log4j.Logger;

import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;

import dk.brics.automaton.Automaton;


@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class Try2a_Automaton {

	static Logger logger = Logger.getLogger(Try2a_Automaton.class);

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

}