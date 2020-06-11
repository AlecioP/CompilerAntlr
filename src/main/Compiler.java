package main;

import java.io.FileInputStream;
import java.io.IOException;

import org.antlr.v4.runtime.ANTLRInputStream;
import org.antlr.v4.runtime.Lexer;
import org.antlr.v4.runtime.Parser;
import org.antlr.v4.runtime.tree.ParseTreeVisitor;

import ast.SimplePlusBlock;
import ast.SimplePlusElementBase;
import parser.SimplePlusBaseVisitor;
import parser.SimplePlusLexer;
import parser.SimplePlusParser;
import parser.SimplePlusParser.BlockContext;
import parser.factories.LanguageFactory;
import parser.factories.SimplePlusFactory;

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
			
			SimplePlusBaseVisitor<SimplePlusElementBase> v = (SimplePlusBaseVisitor<SimplePlusElementBase>) visitor;
			
			SimplePlusBlock mainBlock = (SimplePlusBlock) v.visitBlock(ctx);
			
		} catch(IOException e) {
			e.printStackTrace();
		}

	}

}
