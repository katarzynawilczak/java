package dictionary3;

import dictionary.*;
import dictionary2.*;
import dictionary4.Crossword;

public abstract class Strategy {

	
	//klas� abstrakcyjn�, zawieraj�c� dwie abstrakcyjne metody (nie implementuj ich). 
	//Klasa ta b�dzie wykorzystywana do generowania krzy��wek:
		public abstract CwEntry findEntry(Crossword cw) {}
		public abstract void updateBoard(Board b, CwEntry e) {}
}

