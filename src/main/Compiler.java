package main;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDate;

import org.antlr.v4.runtime.ANTLRInputStream;
import org.antlr.v4.runtime.Lexer;
import org.antlr.v4.runtime.Parser;
import org.antlr.v4.runtime.tree.ParseTreeVisitor;

import ast.SPBlock;
import ast.SPElementBase;
import parser.SimplePlusBaseVisitor;
import parser.SimplePlusLexer;
import parser.SimplePlusParser;
import parser.SimplePlusParser.BlockContext;
import parser.factories.LanguageFactory;
import parser.factories.SimplePlusFactory;
import util.EnvironmentCodeGen;
import util.EnvironmentEffects;
import util.EnvironmentEffectsFun;
import util.EnvironmentTypes;

public class Compiler {

	public static void main(String[] args) {
		
		if(args.length>1)System.out.println("Too many arguments");
		else if(args.length <=0)System.out.println("Missing input file");
		
		String filename = args[0];
		
		try {
			FileInputStream is = new FileInputStream(filename);
			LanguageFactory factory = new SimplePlusFactory();
			factory.initLexer(is);
			//Create Lexer

			Lexer lexer = factory.getLexer();
			
			//Create Parser
			Parser parser = factory.getParser();
			
			parser.setBuildParseTree(true);
			
			ParseTreeVisitor visitor = factory.getVisitor();
			
			//visit the root, this will recursively visit the whole tree
			BlockContext ctx = ((SimplePlusParser)parser).block();
			
			SimplePlusBaseVisitor<SPElementBase> v = (SimplePlusBaseVisitor<SPElementBase>) visitor;
			
			//AST
			SPBlock mainBlock = (SPBlock) v.visitBlock(ctx);
			//Types and scopes check
			EnvironmentTypes et = new EnvironmentTypes();
			et.openScope();
			mainBlock.checkSemantics(et);
			//Effects check
			EnvironmentEffects ee = new EnvironmentEffects();
			ee.openScope();
			EnvironmentEffectsFun eef = new EnvironmentEffectsFun();
			mainBlock.checkEffects(ee, eef);
			//Code generation
			String outfile = "out.simple";
			
			File fd = new File(outfile);
			
			fd.createNewFile();
			
			FileWriter fw = new FileWriter(fd);
			
			String endl = System.lineSeparator();
			java.time.LocalDate today = LocalDate.now();
			
			fw.write("# Simple plus bytecode compiled on day "+endl);
			fw.write("# Compiled on "+today.getDayOfMonth()+" "+today.getMonth()+" "+today.getYear()+endl);
			
			EnvironmentCodeGen ec = new EnvironmentCodeGen();
			//ec.openScope(false);
			mainBlock.codeGen(ec, fw);
			
			System.out.println("COMPILED");
			
			fw.flush();
			fw.close();
			
		} catch(IOException e) {
			e.printStackTrace();
		}

	}

}
