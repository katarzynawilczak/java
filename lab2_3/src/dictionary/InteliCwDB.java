package dictionary;

import java.util.Random;
import java.io.*;
import java.util.LinkedList;
import java.util.regex.*;

public class InteliCwDB extends CwDB{
	
	
	public InteliCwDB(String filename_) throws IOException {
		super(filename_);
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
	
	/*public void add(String word, String clue) {
		Entry entry_= new Entry(word, clue);
		for (int i = 0; i < dict.size()-1; i++)  {
			Entry w1 =dict.get(i);
			Entry w2=dict.get(i+1);
			if(dict.size()==0) {
				dict.add(entry_);
			}
			if(dict.get(0).getWord().compareTo(word)>0) {
				dict.add(0,entry_);
			}
			if(dict.get(dict.size()-1).getWord().compareTo(word)<0) {
				dict.add(entry_);
			}
			if(w1.getWord().compareTo(word)<=0 && w2.getWord().compareTo(word)>0) {
				dict.add(i+1,entry_);	
			}
		}
	}*/
	
	
	public static void main(String[] argv) throws IOException {
		InteliCwDB inteli = new InteliCwDB("C:\\Users\\KASIA\\eclipse-workspace\\lab2_3\\src\\dictionary\\cwdb.txt");
		System.out.println(inteli.get("Agata").getClue());
		System.out.println(inteli.getSize());
		LinkedList<Entry> ls =inteli.findAll("m..a");
		for(int i=0; i<ls.size();i++) {
		System.out.println(ls.get(i).getWord());
		}
		Entry e1 = inteli.getRandom();
		System.out.println(e1.getWord() + "-" + e1.getClue());
		System.out.println(inteli.getRandom(4).getWord());
		inteli.saveDB("C:\\Users\\KASIA\\eclipse-workspace\\lab2_3\\src\\dictionary\\cwdb.txt");
	}

}
