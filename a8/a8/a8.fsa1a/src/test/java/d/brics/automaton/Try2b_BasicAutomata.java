package d.brics.automaton;


import org.apache.log4j.Logger;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;

import dk.brics.automaton.Automaton;
import dk.brics.automaton.BasicAutomata;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class Try2b_BasicAutomata {

	static Logger logger = Logger.getLogger(Try2b_BasicAutomata.class);


	@Test
	public void t2a() {
		logger.info("t1a");

		Automaton a1 = BasicAutomata.makeString("car");
		Automaton a2 = BasicAutomata.makeStringMatcher("car");
		//logger.debug(a1.toString());
		logger.debug(a1.toDot());
		//logger.debug(a2.toString());
		logger.debug(a2.toDot());
		
		
	}

	@Test
	public void t2b() {
		System.out.println("t1b");



	}

	@Test
	public void t1c() {
		System.out.println("t1c");
	}

}