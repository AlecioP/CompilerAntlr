package main;
import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Scanner;
public class VM {

	public static void main(String[] args) {
		VM machine =new VM();
		File source=new File(args[0]);
		try {
			Scanner scan = new Scanner(source);
			int instruction =0;
			while (scan.hasNextLine()) {
				String data = scan.nextLine();
				String[] split = data.split(" ");
				if(split[1].equals(":")) {
					machine.getLabels().put(split[0], Integer.valueOf(instruction));
				}else {
					ArrayList<String> param=(ArrayList<String>) Arrays.asList(split);
					String name=param.remove(0);
					machine.getCode().add(new Command(name,param));
					instruction++;
				}
			}
			scan.close();

		} catch (FileNotFoundException e) {

			e.printStackTrace();
		}
		machine.cpu();
	}
	//public static final int CODESIZE = 10000;
	public static final int MEMSIZE = 10000;

	private  ArrayList<Command> code ;
	private  HashMap<String, Integer> labels; 
	private int[] memory = new int[MEMSIZE];
	private int ip = 0;
	private int sp = MEMSIZE;       
	private int fp = MEMSIZE; 
	private int ra;           
	private int a0;

	public VM() {
		this.code = new ArrayList<Command>();
		this.labels= new HashMap<String, Integer>();
	}

	public HashMap<String, Integer> getLabels() {
		return labels;
	}
	public ArrayList<Command> getCode() {
		return code;
	}
	public void cpu() {
		ip=0;
		while ( true ) {
			if(sp<0) {
				System.out.println("\nError: Out of memory");
				return;
			}
			else {
				Command current = this.code.get(ip++);
				switch(current.cmd){

				}
			} 
		}
	} 
}
class Command {
	final String cmd;
	final ArrayList<String> args;

	public String getCmd() {
		return cmd;
	}

	public ArrayList<String> getArgs() {
		return args;
	}
	Command(String cmd, ArrayList<String> args) {
		this.cmd = cmd;
		this.args = args;
	}

}
