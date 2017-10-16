package dictionary;

import java.util.LinkedList;
import java.util.ListIterator;
import java.io.*;

public class CwDB{
	
	protected LinkedList<Entry> dict = new LinkedList<Entry>();
	String filename;
	
	
	public CwDB(String filename_) throws IOException {
		filename=filename_;
		createDB(filename);
	}
	public void add(String word_, String clue_) {
		Entry entry_= new Entry(word_, clue_);
		dict.add(entry_);
	}
	
	public Entry get(String word) {
		for(Entry i: dict) {
			if(i.getWord().equals(word)) {
				return i;
			}
		}
		return null;
	}
	
	public void remove(String word) {
		for(Entry i: dict) {
			if(i.getWord().equals(word)) {
				dict.remove(i);
				}
			}
	}
	
	public void saveDB(String filename) throws IOException {
		FileWriter fw = new FileWriter(filename);
		PrintWriter pw = new PrintWriter(fw);
		for(Entry i : dict) {
			pw.println(i.getWord());
			pw.println(i.getClue());
		}
	pw.close();	
}
	
	public int getSize() {
		return dict.size();
	}
	
	
	protected void createDB(String filename) throws IOException {
		FileReader fr = new FileReader(filename);
		BufferedReader bfr = new BufferedReader(fr);
		String bfrRL = bfr.readLine();
		while(bfrRL!=null) {
			String word_= bfrRL;
			String clue_ = bfr.readLine();
			add(word_,clue_);
			bfrRL = bfr.readLine();
		}
        bfr.close();
	}
}
