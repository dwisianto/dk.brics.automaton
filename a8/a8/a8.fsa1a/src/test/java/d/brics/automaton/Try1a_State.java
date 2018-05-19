package d.brics.automaton;


import org.apache.log4j.Logger;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;

import dk.brics.automaton.State;

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
	
	

}