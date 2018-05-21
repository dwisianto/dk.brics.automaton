package d.brics.automaton;


import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;

import dk.brics.automaton.Automaton;
import dk.brics.automaton.BasicAutomata;
import dk.brics.automaton.BasicOperations;
import dk.brics.automaton.RegExp;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class Try4a_BasicOperations {
	
	static Logger logger = Logger.getLogger(Try4a_BasicOperations.class);
	

	/**
	 * Match a string without spaces
	 */
    @Test
	public void t1a() {
        logger.debug("t1a");
        
        Automaton a1dwi = BasicAutomata.makeString("dwi");
        Automaton a2sianto = BasicAutomata.makeString("sianto");
        Automaton a3mansjur = BasicAutomata.makeString("mansjur");
        logger.info(a1dwi.toDot());
        
        String sTxt="dwi";
        logger.info( BasicOperations.run(a1dwi, sTxt) );
        logger.info( BasicOperations.run(a2sianto, sTxt) );
        logger.info( BasicOperations.run(a3mansjur, sTxt) );
                
    }

    
    /**
     * Match a string with spaces
     */
    @Test
	public void t1b() {
        logger.debug("t1b");
                
        Automaton a1dwi = BasicAutomata.makeString("dwi");
        Automaton a2sianto = BasicAutomata.makeString("sianto");
        Automaton a3mansjur = BasicAutomata.makeString("mansjur");
        
		// Add in an Automaton that allows any whitespace by using the regular expression 
		Automaton a4AnySpaces = new RegExp("[ \t]+").toAutomaton();
		
		Automaton a1dwiSpaces = BasicOperations.concatenate(a1dwi, a4AnySpaces);
		logger.info(a1dwiSpaces.toDot());

        
        
        String sTxt="dwi ";
        logger.info( BasicOperations.run(a1dwi, sTxt) );
        logger.info( BasicOperations.run(a1dwiSpaces, sTxt) );        
        logger.info( BasicOperations.run(a2sianto, sTxt) );
        logger.info( BasicOperations.run(a3mansjur, sTxt) );        
        
    }

    /**
     * Recognize either dwi , sianto , or mansjur
     */
    @Test
	public void t1c() {
        logger.debug("t1c");
        
        String [] sNames = { "dwi", "sianto", "mansjur" };
		
		// [] Simply loop through the strings and place them in the automaton
        Automaton strUnion = null;
		for (String str: sNames) {
			// Basic building block, make an automaton that accepts a single string
			Automaton currStrAutomaton = BasicAutomata.makeString(str);
			if (strUnion == null) {
				strUnion = currStrAutomaton;
			} else {
				// Combine the current string with the Automatons for the previous string,  
				// saying that this new string is also valid
				strUnion = BasicOperations.union(strUnion, currStrAutomaton);
			}
		}
		logger.trace(strUnion.toDot());		
		
		List<Automaton> allAutomatons = new ArrayList<Automaton>();
		allAutomatons.add(strUnion);
		
		// Add in an Automaton that allows any whitespace by using the regular expression 
		allAutomatons.add(new RegExp("[ \t]*").toAutomaton());
		allAutomatons.add(new RegExp(".*").toAutomaton() );
				
		Automaton UnionAutomatons = BasicOperations.concatenate(allAutomatons);
		logger.trace(UnionAutomatons.toDot());
		
		// recognition stage
        String sTxt1Dwi="dwi ";
        String sTxt1Sianto="sianto ";
        String sTxt1Mansjur="mansjur ";        
        String sTxt1DSM="dwi sianto mansjur";        
        logger.info( BasicOperations.run( UnionAutomatons, sTxt1Dwi) );
        logger.info( BasicOperations.run( UnionAutomatons, sTxt1Sianto) );
        logger.info( BasicOperations.run( UnionAutomatons, sTxt1Mansjur) );
        logger.info( BasicOperations.run( UnionAutomatons, sTxt1DSM) );
		
    }
    
    
    @Test
	public void t1d() {
        logger.debug("t1d");
        
        String [] sNames = { "dwi", "sianto[s]?", "santos", "mansjur"  };
        
		// [] Simply loop through the strings and place them in the automaton
        Automaton strUnion = BasicAutomata.makeString(sNames[0]);
        strUnion = BasicOperations.union(strUnion,BasicAutomata.makeString(sNames[1])) ;
        strUnion = BasicOperations.union(strUnion,BasicAutomata.makeString(sNames[2])) ;
        strUnion.minimize();
        strUnion.reduce();
        //strUnion = strUnion.minus(BasicAutomata.makeString(sNames[2])) ;
        
		logger.info(strUnion.toDot());
		
    }
    
    @Test
	public void t1e() {
        logger.debug("t1e");
        
        Automaton a1dwi = BasicAutomata.makeString("dwi");
        
        Automaton a2regex = new RegExp(".*").toAutomaton();
        a2regex.minus(a2regex);
        
		//List<Automaton> allAutomatons = new ArrayList<Automaton>();
		//allAutomatons.add(new RegExp("[ \t]*").toAutomaton());
		//allAutomatons.add( );
        
        logger.info( a2regex.toDot() );
        
        

    }
    
    
    
    @Test
	public void t1x() {
        logger.debug("t1x");

    }
}