package main;
import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Scanner;

import util.EnvironmentCodeGen;
public class VM {

	public static void main(String[] args) {
		VM machine =new VM();
		File source=new File(args[0]);
		try {
			Scanner scan = new Scanner(source);
			int instruction =0;
			while (scan.hasNextLine()) {
				String data = scan.nextLine();
				
				if(data.charAt(0)=='#')
					continue;
				
				String[] split = data.split(" ");
				if(split[1].equals(":")) {
					machine.getLabels().put(split[0], Integer.valueOf(instruction));
				}else {
					ArrayList<String> param=new ArrayList<String>(Arrays.asList(split));
					String name=param.remove(0);
					machine.getCode().add(new Command(name,param));
					instruction++;
				}
			}
			scan.close();

		} catch (FileNotFoundException e) {

			e.printStackTrace();
		}
		machine.getCode().add(new Command("halt", null));
		machine.cpu();
	}
	//public static final int CODESIZE = 10000;
	public static final int MEMSIZE = 10000;

	private  ArrayList<Command> code ;
	private  HashMap<String, Integer> labels; 
	private  HashMap<String,Integer> registers;
	private int[] memory = new int[MEMSIZE];


	public VM() {
		this.code = new ArrayList<Command>();
		this.labels= new HashMap<String, Integer>();
		this.registers=new HashMap<String, Integer>();
		registers.put("$ip", 0);
		registers.put("$sp", MEMSIZE-1-(EnvironmentCodeGen.WORDDIM*2));
		registers.put("$fp", MEMSIZE-1);
		registers.put("$ra", 0);
		registers.put("$a0", 0);
		registers.put("$al", 0);
		registers.put("$t0", 0);
	}
	protected int r(String a) {
		return registers.get(a).intValue();
	}
	protected void r(String a,int b) {
		registers.replace(a,Integer.valueOf(b));
	}


	public HashMap<String, Integer> getLabels() {
		return labels;
	}
	public ArrayList<Command> getCode() {
		return code;
	}
	public void cpu() {
		final int wordDim=EnvironmentCodeGen.WORDDIM;
		r("$ip",0);
		while ( true ) {
			//System.err.println("Instruction "+r("$ip"));
			if(r("$sp")<0) {
				System.err.println("\nError: Out of memory");
				return;
			}
			else {
				Command current = this.code.get(r("$ip"));
				System.err.println("\n"+current.cmd+" "+current.args);
				System.err.println("Cpu status : "+registers);
				/*PRINT MEMORY*/
				System.err.print("[{");
				for(int it = memory.length-1;it>9800;it--) {
					System.err.print(memory[it]+", ");
					if((MEMSIZE-1-it)%4==3)
						System.err.print("}"+(it-1)+"->{");
					if(r("$fp")==it-1)
						System.err.print("\n\n LAST FRAME");
				}
				System.err.println("]");
				/*PRINT MEMORY*/
				r("$ip",r("$ip")+1);
				//System.err.println("Next instruction "+r("$ip"));
				switch(current.cmd){
				case "lw":{
					String r1 = current.args.get(1).split("[()]")[1];
					String of = current.args.get(1).split("[()]")[0];
					int offset=Integer.valueOf(of).intValue();
					int v1 = r(r1);
					for(int i=wordDim-1; i>=0; i--) 
						r(current.args.get(0),memory[v1+offset-i]);
					break;
				}
				case "sw":{
					String r1 = current.args.get(1).split("[()]")[1];
					String of = current.args.get(1).split("[()]")[0];
					int offset=Integer.valueOf(of).intValue();
					int v1 = r(r1);
					for(int i=wordDim-1; i>=0; i--) 
						memory[v1+offset-i]=r(current.args.get(0));
					break;
				}
				case "li":{
					int v = Integer.valueOf(current.args.get(1)).intValue();
					r(current.args.get(0),v);
					break;
				}
				case "addi":{
					int v = Integer.valueOf(current.args.get(1)).intValue()+r(current.args.get(0));
					r(current.args.get(0),v);
					break;
				}
				case "add":{
					int v = r(current.args.get(1))+r(current.args.get(2));
					r(current.args.get(0),v);
					break;
				}
				case "subi":{
					int add1 = Integer.valueOf(current.args.get(1)).intValue();
					int add2 = r(current.args.get(0));
					int v = add2-add1;
					r(current.args.get(0),v);
					break;
				}
				case "move":
				case"mov":{
					int v = r(current.args.get(1));
					r(current.args.get(0),v);
					break;

				}
				case "jr":{
					int v = r(current.args.get(0));
					r("$ip",v);
					break;

				}
				case "jal":{
					r("$ra",r("$ip"));
					System.err.println("Instruction at return is : "+this.code.get(r("$ra")).cmd);
					int v= labels.get(current.args.get(0)).intValue();
					r("$ip",v);
					break;

				}
				case "b":{
					int v= labels.get(current.args.get(0)).intValue();
					r("$ip",v);
					break;
				}
				case "beq":{
					int v1=0,v2=0;
					//ASSIGN FIRST VALUE
					if(current.args.get(0).charAt(0)=='$')
						v1=r(current.args.get(0));
					else
						v1=Integer.valueOf(current.args.get(0));
					//ASSIGN SECOND VALUE
					if(current.args.get(1).charAt(0)=='$')
						v2=r(current.args.get(1));
					else
						v2=Integer.valueOf(current.args.get(1));
					//COMPARE
					if (v1==v2){
						int v= labels.get(current.args.get(2)).intValue();
						r("$ip",v);
					}
					break;

				}
				case "bge":{
					int v1=0,v2=0;
					//ASSIGN FIRST VALUE
					if(current.args.get(0).charAt(0)=='$')
						v1=r(current.args.get(0));
					else
						v1=Integer.valueOf(current.args.get(0));
					//ASSIGN SECOND VALUE
					if(current.args.get(1).charAt(0)=='$')
						v2=r(current.args.get(1));
					else
						v2=Integer.valueOf(current.args.get(1));
					//COMPARE
					if (v2>=v1){
						int v= labels.get(current.args.get(2)).intValue();
						r("$ip",v);
					}
					break;
				}
				case "ble":{
					int v1=0,v2=0;
					//ASSIGN FIRST VALUE
					if(current.args.get(0).charAt(0)=='$')
						v1=r(current.args.get(0));
					else
						v1=Integer.valueOf(current.args.get(0));
					//ASSIGN SECOND VALUE
					if(current.args.get(1).charAt(0)=='$')
						v2=r(current.args.get(1));
					else
						v2=Integer.valueOf(current.args.get(1));
					//COMPARE
					if (v2<=v1){
						int v= labels.get(current.args.get(2)).intValue();
						r("$ip",v);
					}
					break;

				}
				case "print":{
					System.out.println(r(current.args.get(0)));
					break;

				}
				case "neg":{
					int v = r(current.args.get(0))*(-1);
					r(current.args.get(0),v);
					break;
				}
				case "mult":{
					int v = r(current.args.get(1))*r(current.args.get(2));
					r(current.args.get(0),v);
					break;
				}
				case "div":{
					int v = r(current.args.get(1))/r(current.args.get(2));
					r(current.args.get(0),v);
					break;
				}
				case "halt":{
					System.out.println("TERMINATED");
					System.exit(0);
				}default: {
					System.out.println("Error command "+ current.cmd +" not found");
					System.exit(-1);
				}
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
