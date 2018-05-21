package d.ls.core.utl.fsa.aset;

import dk.brics.automaton.Automaton;
import dk.brics.automaton.BasicAutomata;
import dk.brics.automaton.BasicOperations;


public class AutomatonSet1 {

	private Automaton mergedAutomaton;
	
	public AutomatonSet1() {	
	}	
	
	public void add(String newTerm) {
		newTerm = newTerm.toUpperCase();
		if (mergedAutomaton == null) {
			mergedAutomaton = BasicAutomata.makeString(newTerm);
		} else {
			mergedAutomaton = BasicOperations.union(mergedAutomaton,BasicAutomata.makeString(newTerm));
		}
		mergedAutomaton = Automaton.minimize(mergedAutomaton);
	}
	
	public int numNodes() {
		return mergedAutomaton.getNumberOfStates();
	}
	
	public int numTransitions() {
		return mergedAutomaton.getNumberOfTransitions();
	}	
	
}
