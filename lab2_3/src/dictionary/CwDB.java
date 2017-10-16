package dictionary;

import java.util.LinkedList;
import java.util.ListIterator;

public class CwDB extends Entry {
	
	protected LinkedList<Entry> dict;
	
	public CwDB(String filename) {
		//super ?
		//przyjmuje sciezkê do pliku
	}
	public void add(String word, String clue) {
		Entry entry_= new Entry(word, clue);
		//dodaj do pliku
	}
	public Entry get(String word) {
		for(Entry i: dict) {
			if(i.equals(word)) {
				int pos = dict.indexOf(i);
				Entry result = dict.get(pos);
				return result;
			}
		}
		return null;
	}
	public void remove(String word) {
		for(Entry i: dict) {
			if(i.equals(word)) {
				dict.remove(i);
				}
			}
	}
	public void saveDB(String filename) {
		//zapisz liste?
	}
	
	public int getSize() {
		return dict.size();
	}
	
	protected void createDB(String filename) {
		//plik wczytywany do listy
	}
	
}
