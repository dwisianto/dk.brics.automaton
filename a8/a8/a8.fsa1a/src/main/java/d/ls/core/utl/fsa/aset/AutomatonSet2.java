package d.ls.core.utl.fsa.aset;

import java.util.LinkedList;
import java.util.List;

import dk.brics.automaton.Automaton;
import dk.brics.automaton.BasicAutomata;


public class AutomatonSet2 {
	
	private List<Automaton> allTermAutomata;
	
	public AutomatonSet2() {
		allTermAutomata = new LinkedList<Automaton>();
	}	

	public void add(String newTerm) {
		allTermAutomata.add(BasicAutomata.makeString(newTerm));
	}	
	
	public int numNodes() {
		return allTermAutomata.size();
	}	

}
