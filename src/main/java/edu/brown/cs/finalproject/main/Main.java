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
	}	
}
