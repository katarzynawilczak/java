package dictionary;

import java.util.Random;
import java.util.LinkedList;
import java.util.regex.*;

public class InteliCwDB extends CwDB{
	
	public InteliCwDB(String filename) {
		//super?
		//przyjmuje sciezke do pliku?
	}
	
	public LinkedList<Entry> findAll(String pattern){
		LinkedList<Entry> list1 = new LinkedList<Entry>();
		for(Entry i : dict) {
			if(i.getWord().matches(pattern)) {
				list1.add(i);
			}
		}
		return list1;
	}
	public Entry getRandom() {
		Random rand = new Random();
		int randomNumber = rand.nextInt(dict.size());
		Entry result = dict.get(randomNumber);
		return result;
	}
	
	public Entry getRandom(int length) {
		LinkedList<Entry> list1 = new LinkedList<Entry>();
		for(Entry i : dict) {
			if(i.getWord().length()==length) {
				list1.add(i);
			}
		}
		Random rand = new Random();
		int randomNumber = rand.nextInt(list1.size());
		Entry result = list1.get(randomNumber);
		return result;
	}
	
	public Entry getRandom(String pattern) {
		LinkedList<Entry> list1 = new LinkedList<Entry>();
		for(Entry i : dict) {
			if(i.getWord().matches(pattern)) {
				list1.add(i);
			}
		}
		Random rand = new Random();
		int randomNumber = rand.nextInt(list1.size());
		Entry result = list1.get(randomNumber);
		return result;
	}
	public void add(String word, String clue) {
		Entry entry_= new Entry(word, clue);
		//dodaj do pliku w porzadku alfabetycnzym
	}
	
	
	public static void main(String[] argv) {
		
	}

}
