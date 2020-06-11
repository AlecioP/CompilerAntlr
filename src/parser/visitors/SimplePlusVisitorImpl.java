package parser.visitors;

import org.antlr.v4.runtime.tree.ErrorNode;
import org.antlr.v4.runtime.tree.ParseTree;
import org.antlr.v4.runtime.tree.RuleNode;
import org.antlr.v4.runtime.tree.TerminalNode;

import ast.SimplePlusElementBase;
import parser.SimplePlusBaseVisitor;
import parser.SimplePlusParser.ArgContext;
import parser.SimplePlusParser.AssignmentContext;
import parser.SimplePlusParser.BaseExpContext;
import parser.SimplePlusParser.BinExpContext;
import parser.SimplePlusParser.BlockContext;
import parser.SimplePlusParser.BoolExpContext;
import parser.SimplePlusParser.CallContext;
import parser.SimplePlusParser.CallExpContext;
import parser.SimplePlusParser.DecFunContext;
import parser.SimplePlusParser.DecVarContext;
import parser.SimplePlusParser.DeclarationContext;
import parser.SimplePlusParser.DeletionContext;
import parser.SimplePlusParser.IteContext;
import parser.SimplePlusParser.NegExpContext;
import parser.SimplePlusParser.NotExpContext;
import parser.SimplePlusParser.PrintContext;
import parser.SimplePlusParser.RefContext;
import parser.SimplePlusParser.RetContext;
import parser.SimplePlusParser.StatementContext;
import parser.SimplePlusParser.TypeContext;
import parser.SimplePlusParser.ValExpContext;
import parser.SimplePlusParser.VarExpContext;

public class SimplePlusVisitorImpl extends SimplePlusBaseVisitor<SimplePlusElementBase> {

	@Override
	public SimplePlusElementBase visitBlock(BlockContext ctx) {
		// TODO Auto-generated method stub
		return super.visitBlock(ctx);
	}

	@Override
	public SimplePlusElementBase visitStatement(StatementContext ctx) {
		// TODO Auto-generated method stub
		return super.visitStatement(ctx);
	}

	@Override
	public SimplePlusElementBase visitDeclaration(DeclarationContext ctx) {
		// TODO Auto-generated method stub
		return super.visitDeclaration(ctx);
	}

	@Override
	public SimplePlusElementBase visitDecFun(DecFunContext ctx) {
		// TODO Auto-generated method stub
		return super.visitDecFun(ctx);
	}

	@Override
	public SimplePlusElementBase visitDecVar(DecVarContext ctx) {
		// TODO Auto-generated method stub
		return super.visitDecVar(ctx);
	}

	@Override
	public SimplePlusElementBase visitType(TypeContext ctx) {
		// TODO Auto-generated method stub
		return super.visitType(ctx);
	}

	@Override
	public SimplePlusElementBase visitArg(ArgContext ctx) {
		// TODO Auto-generated method stub
		return super.visitArg(ctx);
	}

	@Override
	public SimplePlusElementBase visitRef(RefContext ctx) {
		// TODO Auto-generated method stub
		return super.visitRef(ctx);
	}

	@Override
	public SimplePlusElementBase visitAssignment(AssignmentContext ctx) {
		// TODO Auto-generated method stub
		return super.visitAssignment(ctx);
	}

	@Override
	public SimplePlusElementBase visitDeletion(DeletionContext ctx) {
		// TODO Auto-generated method stub
		return super.visitDeletion(ctx);
	}

	@Override
	public SimplePlusElementBase visitPrint(PrintContext ctx) {
		// TODO Auto-generated method stub
		return super.visitPrint(ctx);
	}

	@Override
	public SimplePlusElementBase visitRet(RetContext ctx) {
		// TODO Auto-generated method stub
		return super.visitRet(ctx);
	}

	@Override
	public SimplePlusElementBase visitIte(IteContext ctx) {
		// TODO Auto-generated method stub
		return super.visitIte(ctx);
	}

	@Override
	public SimplePlusElementBase visitCall(CallContext ctx) {
		// TODO Auto-generated method stub
		return super.visitCall(ctx);
	}

	@Override
	public SimplePlusElementBase visitBaseExp(BaseExpContext ctx) {
		// TODO Auto-generated method stub
		return super.visitBaseExp(ctx);
	}

	@Override
	public SimplePlusElementBase visitVarExp(VarExpContext ctx) {
		// TODO Auto-generated method stub
		return super.visitVarExp(ctx);
	}

	@Override
	public SimplePlusElementBase visitBinExp(BinExpContext ctx) {
		// TODO Auto-generated method stub
		return super.visitBinExp(ctx);
	}

	@Override
	public SimplePlusElementBase visitValExp(ValExpContext ctx) {
		// TODO Auto-generated method stub
		return super.visitValExp(ctx);
	}

	@Override
	public SimplePlusElementBase visitNegExp(NegExpContext ctx) {
		// TODO Auto-generated method stub
		return super.visitNegExp(ctx);
	}

	@Override
	public SimplePlusElementBase visitBoolExp(BoolExpContext ctx) {
		// TODO Auto-generated method stub
		return super.visitBoolExp(ctx);
	}

	@Override
	public SimplePlusElementBase visitCallExp(CallExpContext ctx) {
		// TODO Auto-generated method stub
		return super.visitCallExp(ctx);
	}

	@Override
	public SimplePlusElementBase visitNotExp(NotExpContext ctx) {
		// TODO Auto-generated method stub
		return super.visitNotExp(ctx);
	}

	@Override
	public SimplePlusElementBase visit(ParseTree tree) {
		// TODO Auto-generated method stub
		return super.visit(tree);
	}

	@Override
	public SimplePlusElementBase visitChildren(RuleNode arg0) {
		// TODO Auto-generated method stub
		return super.visitChildren(arg0);
	}

	@Override
	public SimplePlusElementBase visitErrorNode(ErrorNode node) {
		// TODO Auto-generated method stub
		return super.visitErrorNode(node);
	}

	@Override
	public SimplePlusElementBase visitTerminal(TerminalNode node) {
		// TODO Auto-generated method stub
		return super.visitTerminal(node);
	}
	
}
