package pkg1;
import java.util.LinkedList;


public class EmailMessage {
	
	private String from; //required (must be e-mail)
    private LinkedList<String> to; //required at least one (must be e-mail)
    private String subject; //optional
    private String content; //optional
    private String mimeType;  // optional
    private LinkedList<String> cc; //optional
    //private LinkedList<String> bcc; // optional
	

	private EmailMessage(Builder builder) {
		this.from=Builder.from;
		this.to=Builder.to;
		this.subject=Builder.subject;
		this.content=Builder.content;
		this.mimeType=Builder.mimeType;
		this.cc=Builder.cc;
		
	}
	
	public static class Builder{
		
		private static String from; //required (must be e-mail)
	    private static LinkedList<String> to; //required at least one (must be e-mail)
	    private static String subject; //optional
	    private static String content; //optional
	    private static String mimeType;  // optional
	    private static LinkedList<String> cc; //optional
	   // private static LinkedList<String> bcc; // optional
	    
	    
	    public Builder(String from, LinkedList<String> to) {
	    	//sprawdzenie
	    	this.from=from;
	    	this.to=to;
	    }
		
		public Builder addSubject(String sub) {
			this.subject=sub;
			return this;
		}
		
		public Builder addcontent(String con) {
			this.content=con;
			return this;
		}
		
		public Builder addmimeType(String mimetype){
			this.mimeType=mimetype;
			return this;
		} 
		
		public Builder addcc(LinkedList<String> ll){
			this.cc=ll;
	    	return this;
	    }
	    
		/*public Builder addbcc(LinkedList<String> ll){
			this.bcc=ll;
	    	return this;
	    }*/
		
		
		public EmailMessage build() {
			return new EmailMessage(this);
			
		}
	}
	
	public static Builder builder(String from, LinkedList<String> to) {
		return new EmailMessage.Builder(from,to);
	}
	
	public void Print() {
		System.out.println("FROM: " + this.from);
		System.out.println("TO: " + this.to);
		System.out.println("SUBJECT: " + this.subject);
		System.out.println("CONTENT: " + this.content);
		System.out.println("MIMETYPE: " + this.mimeType);
		System.out.println("LIST: " + this.cc);

	}
	
	public String From() {
		return this.from;
	}
	
	public LinkedList<String> To(){
		return this.to;
	}
	
	public String Subject() {
		return this.subject;
	}
	
	public String Content() {
		return this.content;
	}
	
	
}
