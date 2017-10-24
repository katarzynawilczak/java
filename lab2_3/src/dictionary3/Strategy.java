package dictionary3;

import dictionary.*;
import dictionary2.*;
import dictionary4.Crossword;

public abstract class Strategy {

	
	//klasê abstrakcyjn¹, zawieraj¹c¹ dwie abstrakcyjne metody (nie implementuj ich). 
	//Klasa ta bêdzie wykorzystywana do generowania krzy¿ówek:
		public abstract CwEntry findEntry(Crossword cw) {}
		public abstract void updateBoard(Board b, CwEntry e) {}
}

