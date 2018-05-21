package d.ls.core.utl.fsa.aset;

import java.util.ArrayList;
import java.util.List;

import dk.brics.automaton.Automaton;
import dk.brics.automaton.BasicAutomata;
import dk.brics.automaton.BasicOperations;
import dk.brics.automaton.RegExp;

public class FirstNameLastNameValidator {
	
	public static Automaton createNameValidator(	String[] firstNames, 
												String[] lastNames) {
		return BasicOperations.union(
				createFirstBeforeLastAutomaton(firstNames, lastNames),
				createLastBeforeFirstAutomaton(firstNames, lastNames)
				);		
	}
	
	
	
	/**
	 * @param firstNames - set of allowable first names
	 * @param lastNames  - set of allowable last names
	 * @return An automaton that allows <FirstName>\s+<LastName>
	 */
	protected static Automaton createFirstBeforeLastAutomaton(	String[] firstNames, 
																String[] lastNames ) {
		
		List<Automaton> allAutomatons = new ArrayList<Automaton>();
		// Use our builder to create an automaton that allows all the first names
		allAutomatons.add(stringUnionAutomaton(firstNames));
		
		// Add in an Automaton that allows any whitespace by using the regular expression 
		RegExp anyNumberOfSpaces = new RegExp("[ \t]+");
		Automaton anySpaces = anyNumberOfSpaces.toAutomaton();
		allAutomatons.add(anySpaces);
		
		// Add in an Automaton that allows all our last names		
		allAutomatons.add(stringUnionAutomaton(lastNames));	
		
		// Return the concatenation off all these automatons
		return BasicOperations.concatenate(allAutomatons);
	}
	
	/**
	 * @param firstNames - set of allowable first names
	 * @param lastNames  - set of allowable last names
	 * @return An automaton that allows <LastName>\s+<FirstName>
	 * @return
	 */
	protected static Automaton createLastBeforeFirstAutomaton(	String[] firstNames, 
																String[] lastNames ) {
		
		List<Automaton> allAutomatons = new ArrayList<Automaton>();
		allAutomatons.add(stringUnionAutomaton(lastNames));		
		
		RegExp commaPlusAnyNumberOfSpaces = new RegExp(",[ \t]+");
		allAutomatons.add(commaPlusAnyNumberOfSpaces.toAutomaton());
		allAutomatons.add(stringUnionAutomaton(firstNames));
		
		return BasicOperations.concatenate(allAutomatons);		
	}
	
	/**
	 * @param strs - All the strings that we want to allow in the returned language
	 * @return - An automaton that allows only the passed in strings
	 */
	private static Automaton stringUnionAutomaton(String[] strs) {
		
		Automaton strUnion = null;
		
		// Simply loop through the strings and place them in the automaton
		for (String str: strs) {
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
		
		return strUnion;
	}
	
	
	
}
