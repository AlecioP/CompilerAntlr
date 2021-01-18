package parser.visitors;

import java.util.*;

import ast.SPArg;
import ast.SPAssignment;
import ast.SPBinExp;
import ast.SPBlock;
import ast.SPBoolExp;
import ast.SPCall;
import ast.SPCallExp;
import ast.SPDecFun;
import ast.SPDecVar;
import ast.SPDelete;
import ast.SPElementBase;
import ast.SPExp;
import ast.SPIfelse;
import ast.SPNumExp;
import ast.SPPrint;
import ast.SPReturn;
import ast.SPStmt;
import ast.SPType;
import ast.SPType.spType;
import ast.SPUnaryExp;
import ast.SPVarExp;
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
import parser.SimplePlusParser.ExpContext;
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

public class SimplePlusVisitorImpl extends SimplePlusBaseVisitor<SPElementBase> {

	@Override
	public SPElementBase visitBlock(BlockContext ctx) {
		List<SPStmt> children = new LinkedList<SPStmt>();
		
		for(StatementContext stmctx : ctx.statement())
			children.add((SPStmt) visitStatement(stmctx));
		return new SPBlock(children);
	}

	@Override
	public SPElementBase visitStatement(StatementContext ctx) {
		return visit(ctx.getChild(0));
	}

	@Override
	public SPElementBase visitDeclaration(DeclarationContext ctx) {
		return visit(ctx.getChild(0));
	}

	@Override
	public SPElementBase visitDecFun(DecFunContext ctx) {
		String type = ctx.type().getText();
		String name = ctx.ID().getText();
		List<SPArg> args = new LinkedList<SPArg>();
		//IF DECFUN HAS AT LEAST ONE ARG
		boolean nextArg = ctx.getChildCount()>5;
		//INDEX OF FIRST ARG
		int child=3;
		//ARG INDEX
		int ARGN = 0;
		
		while(nextArg){
			//IF NEXT TOKEN IS COMMA, THEN THERE IS AT LEAST ANOTHER ARG
			if(ctx.getChild(child+1).getText().equals(")"))
				nextArg = false;
			//CREATE ARG SUBTREE AND ADD IT TO THE ARGS LIST
			args.add((SPArg) visitArg(ctx.arg(ARGN)));
			
			//JUMP NEXT TOKEN, AKA COMMA
			child+=2;
			ARGN++;
		}
		
		SPBlock body = (SPBlock) visitBlock(ctx.block());
		
		return new SPDecFun(type,name,args,body);
	}

	@Override
	public SPElementBase visitDecVar(DecVarContext ctx) {
		String type=ctx.type().getText();
		String name=ctx.ID().getText();
		SPExp value = null;
		if(ctx.exp()!=null)
			value = (SPExp)visit(ctx.exp());
		
		return new SPDecVar(type, name, value);
	}

	@Override
	public SPElementBase visitType(TypeContext ctx) {
		SPType type=null;
		switch(ctx.getText()) {
		case"int":{
			type = new SPType(spType.INT);
			break;
		}
		case"bool":{
			type = new SPType(spType.BOOL);
			break;
		}
		case"void":{
			type = new SPType(spType.VOID);
			break;
		}
		}
		return type;
	}

	@Override
	public SPElementBase visitArg(ArgContext ctx) {
		String name=ctx.ID().getText();
		SPType type = (SPType) visitType(ctx.type());
		boolean isref = ctx.getChildCount() == 3;
		return new SPArg(name, type, isref);
	}

	@Override
	public SPElementBase visitRef(RefContext ctx) {
		// TODO Auto-generated method stub
		return super.visitRef(ctx);
		//Never used therefore not implemented
	}

	@Override
	public SPElementBase visitAssignment(AssignmentContext ctx) {
		String name = ctx.ID().getText();
		SPExp value = (SPExp)visit(ctx.exp());
		return new SPAssignment(name, value);
	}

	@Override
	public SPElementBase visitDeletion(DeletionContext ctx) {
		String name = ctx.ID().getText();
		return new SPDelete(name);
	}

	@Override
	public SPElementBase visitPrint(PrintContext ctx) {
		SPExp value = (SPExp)visit(ctx.exp());
		return new SPPrint(value);
	}

	@Override
	public SPElementBase visitRet(RetContext ctx) {
		SPExp value = (SPExp)visit(ctx.exp());
		return new SPReturn(value);
	}

	@Override
	public SPElementBase visitIte(IteContext ctx) {
		
		SPExp guard = (SPExp) visit(ctx.exp());
		
		SPStmt then_ = (SPStmt) visit(ctx.statement(0));
		
		SPStmt else_ = null;
		
		if(ctx.getChildCount()>5)
			else_ = (SPStmt) visit(ctx.statement(1));
		
		return new SPIfelse(guard,then_,else_);
		
	}

	@Override
	public SPElementBase visitCall(CallContext ctx) {
		
		String name = ctx.ID().getText();
		
		List<SPExp> args = new LinkedList<SPExp>();
		
		for(ExpContext expc : ctx.exp()) {
			args.add((SPExp) visit(expc));
		}
		
		return new SPCall(name, args);
	}

	@Override
	public SPElementBase visitBaseExp(BaseExpContext ctx) {
		
		return visit(ctx.exp());
	}

	@Override
	public SPElementBase visitVarExp(VarExpContext ctx) {
		return new SPVarExp(ctx.ID().getText());
	}

	@Override
	public SPElementBase visitBinExp(BinExpContext ctx) {
		SPExp right = (SPExp) visit(ctx.right);
		SPExp left = (SPExp) visit(ctx.left);
		String op = ctx.op.getText();
		
		return new SPBinExp(right, left, op);
	}

	@Override
	public SPElementBase visitValExp(ValExpContext ctx) {
		Integer value = Integer.valueOf(ctx.NUMBER().getText());
		return new SPNumExp(value);
	}

	@Override
	public SPElementBase visitNegExp(NegExpContext ctx) {
		
		String operator = ctx.getChild(0).getText();
		SPExp value = (SPExp) visit(ctx.exp());
		
		
		return new SPUnaryExp(operator,value);
	}

	@Override
	public SPElementBase visitBoolExp(BoolExpContext ctx) {
		Boolean value = Boolean.valueOf(ctx.BOOL().getText());
		return new SPBoolExp(value);
	}

	@Override
	public SPElementBase visitCallExp(CallExpContext ctx) {
		return new SPCallExp((SPCall)visitCall(ctx.call()));
	}

	@Override
	public SPElementBase visitNotExp(NotExpContext ctx) {
		String operator = ctx.getChild(0).getText();
		SPExp value = (SPExp) visit(ctx.exp());
		
		
		return new SPUnaryExp(operator,value);
	}
	
}
