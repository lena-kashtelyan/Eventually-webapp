package edu.brown.cs.finalproject.main;

public class Main {
	public static void main(String[] args) {
		new Main(args).run();
	}

	private String[] args;

	private Main(String[] args) {
		this.args = args;
	}

	private void run() {
		System.out.println("helloworld");

//		lines to instantiate tables in the database and create indices; uncomment and change login name if you want to reset the tables
//		Database db = new Database("/home/ipetrov/course/cs032/final_project/32final/database/finalproject.db");
//		new DatabaseFactory(db).createAndIndexTables();	
		
		
		
		
		
		System.out.println("all done");
	}	
}
