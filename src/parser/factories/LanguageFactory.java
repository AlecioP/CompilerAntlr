package parser.factories;

import java.io.FileInputStream;
import java.io.IOException;

import org.antlr.v4.runtime.ANTLRInputStream;
import org.antlr.v4.runtime.Lexer;
import org.antlr.v4.runtime.Parser;
import org.antlr.v4.runtime.tree.ParseTreeVisitor;

public abstract class LanguageFactory {
	
	protected FileInputStream filestream;
	protected ANTLRInputStream antlrstream;
	protected Lexer lexer;
	protected Parser parser;
	
	
	public abstract  void initLexer(FileInputStream is) throws IOException;
	
	public abstract Lexer getLexer();
	
	public abstract  Parser getParser();
	
	public abstract ParseTreeVisitor getVisitor();
}
