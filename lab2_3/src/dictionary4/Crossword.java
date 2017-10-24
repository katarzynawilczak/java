package dictionary4;

import dictionary.*;
import dictionary2.*;
import dictionary3.*;
import java.util.LinkedList;
import java.util.Iterator;

public class Crossword {
	private LinkedList<CwEntry> entries;
	private Board b;
	private InteliCwDB cwdb;
	private final long ID;  //domy�lnie -1
	
	public Iterator<CwEntry> getROEntryIter(){
		
	}//metoda zwracaj�ca iterator Read Only, kt�ry nie pozwala na np. usuwanie element�w z listy.
	
	public Board getBoardCopy() {
		Board board_= new Board(); //???
		return board_;
	}
	
	public InteliCwDB getCwDB() {
		
	}
	
	public void setCwDB(InteliCwDB cwdb) {
		
	}
	
	public boolean contains(String word) {
		
	}
	
	public final void addCwEntry(CwEntry cwe, Strategy s){
	  entries.add(cwe);
	  s.updateBoard(b,cwe);
	}
	
	public final void generate(Strategy s){
	  CwEntry e = null;
	  while((e = s.findEntry(this)) != null){
	    addCwEntry(e,s);
	  }
	}

}
