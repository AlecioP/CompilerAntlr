package parser.factories;

import java.io.FileInputStream;
import java.io.IOException;

import org.antlr.v4.runtime.CommonTokenStream;
import org.antlr.v4.runtime.ANTLRInputStream;
import org.antlr.v4.runtime.Lexer;
import org.antlr.v4.runtime.Parser;

import parser.SimplePlusLexer;
import parser.SimplePlusParser;
import parser.visitors.SimplePlusVisitorImpl;

public class SimplePlusFactory extends LanguageFactory {
	
	private SimplePlusVisitorImpl visitor;

	@Override
	public void initLexer(FileInputStream fs) throws IOException {
		filestream =fs;
		antlrstream= new ANTLRInputStream(this.filestream);
		lexer = new SimplePlusLexer(antlrstream);
		return;
	}

	@Override
	public Lexer getLexer() {
		if(lexer != null) return lexer;
		else throw new RuntimeException("Lexer not initialized. Try to call initLexer method");
	}
	
	@Override
	public Parser getParser() {
		if(parser != null) return parser;
		
		CommonTokenStream tokenstream = new CommonTokenStream(lexer);
		
		parser = new SimplePlusParser(tokenstream);
		
		return parser;
	}
	
	@Override
	public SimplePlusVisitorImpl getVisitor() {
		if(visitor != null) return visitor;
		
		visitor = new SimplePlusVisitorImpl();
		return visitor;
	}

}
