
package parser;

import scanner.*;
import symbolTable.*;
import syntaxTree.*;
import variableType.*;
import java.util.ArrayList;

public class Parser{

	////////////////
	//    Data    //
	////////////////
	
	MyScanner scanner;

	SymbolTable symbolTable = new SymbolTable();

	Token lookAHead;

	boolean endOfFile;

	//////////////////////////
	//	Constructor     //
	//////////////////////////

	public Parser( MyScanner scanner_tmp ){

		scanner = scanner_tmp;
			
		if( scanner.hasNext() ){

			lookAHead = scanner.next();

			endOfFile = false;
	
		}else{

			lookAHead = null;
			
			endOfFile = true;

		}

	}

	/////////////////////////////////
	//	Public Functions       //
	/////////////////////////////////

	public ProgramNode program(){

		match( EnumToken.PROGRAM );

		String programName = match( EnumToken.ID );

		match( EnumToken.SEMICOLON );
		
		DeclarationsNode variables = declarations() ;

		SubProgramDeclarationsNode functions = subprogram_declarations() ;

		CompoundStatementNode main = compound_statement() ;

		match( EnumToken.DOT );

		return new ProgramNode( programName, variables, functions, main );
	
	}

	public void printSymbolTable(){

		symbolTable.print();

	}

	////////////////////////
	//      Helpers       //
	////////////////////////	
	
	private String match( EnumToken expectedType ) throws RuntimeException {

		String ret = null;

		if( !endOfFile && lookAHead.getType() == expectedType ){

			ret = lookAHead.getString();

			if( scanner.hasNext() ){

				lookAHead = scanner.next();

				endOfFile = false;

			}else{

				lookAHead = null;
				
				endOfFile = true;

			}

		}else{

			String lookAHeadTypeString = "null";

			if( !endOfFile ){ lookAHeadTypeString = lookAHead.getType().toString(); }

			throw new RuntimeException( "Trying to match a " + expectedType.toString() + " failed. Found " + lookAHeadTypeString
				+ " instead." );

		}

		return ret;

	}

	ArrayList< String > identifier_list(){

		ArrayList< String > ID_list = new ArrayList< String >();

		ID_list.add( match( EnumToken.ID ) );

		while( lookAHead.getType() == EnumToken.COMMA ){

			match( EnumToken.COMMA );

			ID_list.add( match( EnumToken.ID ) );

		}

		return ID_list;

	}

	DeclarationsNode declarations(){

		DeclarationsNode declarationsNode = new DeclarationsNode();

		while( lookAHead.getType() == EnumToken.VAR ){
		
			match( EnumToken.VAR );

			ArrayList< String > variableNames = identifier_list();

			match( EnumToken.COLON );

			VariableType variableType = type();

			match( EnumToken.SEMICOLON );

			for( int i = 0; i < variableNames.size(); i++){

				VariableNode variableNode = new VariableNode( variableNames.get( i ), variableType );

				declarationsNode.addVariable( variableNode );

				symbolTable.addVariable( variableNode );

			}

		}

		return declarationsNode;

	}
	
	VariableType type() throws RuntimeException {

		VariableType variableType = null;

		if( lookAHead.getType() == EnumToken.INTEGER ){

			match( EnumToken.INTEGER );

			variableType = new VariableType( 0, 1, EnumStandardType.INTEGER );

		}else if( lookAHead.getType() == EnumToken.REAL ){

			match( EnumToken.REAL );

			variableType = new VariableType( 0, 1, EnumStandardType.REAL );

		}else{

			match( EnumToken.ARRAY );

			match( EnumToken.LSPAREN );

			String rowsString = match( EnumToken.NUM );

			int rows = Integer.parseInt( rowsString );

			match( EnumToken.COLON );

			String colsString = match( EnumToken.NUM );

			int cols = Integer.parseInt( colsString );

			match( EnumToken.RSPAREN );

			match( EnumToken.OF );

			if( lookAHead.getType() == EnumToken.REAL ){

				match( EnumToken.REAL );

				variableType = new VariableType( rows, cols, EnumStandardType.REAL );

			}else if( lookAHead.getType() == EnumToken.INTEGER ){

				match( EnumToken.INTEGER );

				variableType = new VariableType( rows, cols, EnumStandardType.INTEGER );

			}else{ throw new RuntimeException( "Error parsing array." ); }

		}

		return variableType;
	
	}

	SubProgramDeclarationsNode subprogram_declarations(){

		SubProgramDeclarationsNode subProgramDeclarationsNode = new SubProgramDeclarationsNode();

		while( lookAHead.getType() == EnumToken.FUNCTION
				|| lookAHead.getType() == EnumToken.PROCEDURE ){

			SubProgramNode subProgramNode = subprogram_declaration();

			subProgramDeclarationsNode.addSubProgram( subProgramNode );

			symbolTable.addSubProgram( subProgramNode );

			match( EnumToken.SEMICOLON );

		}

		return subProgramDeclarationsNode;

	}

	SubProgramNode subprogram_declaration() throws RuntimeException {

		SubProgramNode subProgramNode = null;

		if( lookAHead.getType() == EnumToken.FUNCTION ){

			match( EnumToken.FUNCTION );

			String functionName = match( EnumToken.ID );

			DeclarationsNode parameters = arguments();

			match( EnumToken.COLON );

			EnumStandardType returnType = null;

			if( lookAHead.getType() == EnumToken.INTEGER ){

				match( EnumToken.INTEGER );

				returnType = EnumStandardType.INTEGER;

			}else if( lookAHead.getType() == EnumToken.REAL ){

				match( EnumToken.REAL );

				returnType = EnumStandardType.REAL;

			}else{ throw new RuntimeException( "Error no function return type." ); }

			match( EnumToken.SEMICOLON );

			DeclarationsNode variables = declarations() ;

			CompoundStatementNode main = compound_statement() ;

			subProgramNode = new SubProgramNode( functionName,
					returnType,
					variables,
					parameters,
					main );

		}else if( lookAHead.getType() == EnumToken.PROCEDURE ){

			match( EnumToken.PROCEDURE );

			String procedureName = match( EnumToken.ID );

			DeclarationsNode parameters = arguments();

			match( EnumToken.SEMICOLON );

			EnumStandardType returnType = EnumStandardType.VOID;

			DeclarationsNode variables = declarations() ;

			CompoundStatementNode main = compound_statement() ;

			subProgramNode = new SubProgramNode( procedureName,
					returnType,
					variables,
					parameters,
					main );

		}else{ throw new RuntimeException( "Couldn't find subprogram declaration." ); }

		return subProgramNode;

	}

	DeclarationsNode arguments(){

		DeclarationsNode declarationsNode = new DeclarationsNode();
		
		boolean firstIteration = true;

		if( lookAHead.getType() == EnumToken.LRPAREN ){

			match( EnumToken.LRPAREN );
			
			do{

				if( !firstIteration ){

					match( EnumToken.SEMICOLON );

				}

				ArrayList< String > variableNames = identifier_list();

				match( EnumToken.COLON );

				VariableType variableType = type();

				for( int i = 0; i < variableNames.size(); i++){

					VariableNode variableNode = new VariableNode( variableNames.get( i ), variableType );
				
					declarationsNode.addVariable( variableNode );

					symbolTable.addVariable( variableNode );

				}

				firstIteration = false;

			}while( lookAHead.getType() == EnumToken.SEMICOLON );

			match( EnumToken.RRPAREN );
		
		}

		return declarationsNode;

	}

	CompoundStatementNode compound_statement(){

		CompoundStatementNode compoundStatementNode = new CompoundStatementNode();

		match( EnumToken.BEGIN );

		boolean isFirstIteration = true;

		while( lookAHead.getType() != EnumToken.END ){

			if( !isFirstIteration ){

				match( EnumToken.SEMICOLON );

			}

			compoundStatementNode.addStatement( statement() );

			isFirstIteration = false;

		}
			
		match( EnumToken.END );

		return compoundStatementNode;

	}

	StatementNode statement() throws RuntimeException {

		StatementNode statementNode = null;

		if( lookAHead.getType() == EnumToken.ID
				&& symbolTable.isVariableName( lookAHead.getString() ) ){

			String variableName = match( EnumToken.ID );

			VariableNode variableNode = symbolTable.getVariableByName( variableName );
			
			if( lookAHead.getType() == EnumToken.LSPAREN ){

				match( EnumToken.LSPAREN );

				ExpressionNode arrayIndex = expression();

				match( EnumToken.RSPAREN );

				match( EnumToken.ASSIGNOP );

				ExpressionNode rValue = expression();

				statementNode = new VariableAssignmentStatementNode( variableNode, arrayIndex, rValue );

			}else{
			
				match( EnumToken.ASSIGNOP );

				ExpressionNode rValue = expression();

				statementNode = new VariableAssignmentStatementNode( variableNode, rValue );

			}

		}else if( lookAHead.getType() == EnumToken.ID 
				&& symbolTable.isSubProgramName( lookAHead.getString() ) ){

			String procedureName = match( EnumToken.ID );

			SubProgramNode procedureNode = symbolTable.getSubProgramByName( procedureName );

			if( lookAHead.getType() == EnumToken.LRPAREN ){

				match( EnumToken.LRPAREN );

				ArrayList< ExpressionNode > parameters = expression_list();

				match( EnumToken.RRPAREN );

				statementNode = new SubProgramStatementNode( procedureNode, parameters );

			}else{

				statementNode = new SubProgramStatementNode( procedureNode );

			}

		}else if( lookAHead.getType() == EnumToken.BEGIN ){

			statementNode = compound_statement();

		}else if( lookAHead.getType() == EnumToken.IF ){

			match( EnumToken.IF );

			ExpressionNode testExpression = expression();

			match( EnumToken.THEN );

			StatementNode ifStatementNode = statement();

			match( EnumToken.ELSE );

			StatementNode thenStatementNode = statement();

			statementNode = new IfThenStatementNode( testExpression, ifStatementNode, thenStatementNode );

		}else if( lookAHead.getType() == EnumToken.WHILE ){

			match( EnumToken.WHILE );

			ExpressionNode testExpression = expression();

			match( EnumToken.DO );

			StatementNode doStatementNode = statement();

			statementNode = new WhileDoStatementNode( testExpression, doStatementNode );

		}else if( lookAHead.getType() == EnumToken.READ ){

			match( EnumToken.READ );

			match( EnumToken.LRPAREN );

			String readName = match( EnumToken.ID );

			match( EnumToken.RRPAREN );

			statementNode = new ReadStatementNode( symbolTable.getVariableByName( readName ) );

		}else if( lookAHead.getType() == EnumToken.WRITE ){

			match( EnumToken.WRITE );

			match( EnumToken.LRPAREN );

			ExpressionNode writeExpression = expression();

			match( EnumToken.RRPAREN );

			statementNode = new WriteStatementNode( writeExpression );

		}else if( lookAHead.getType() == EnumToken.RETURN ){

			match( EnumToken.RETURN );

			ExpressionNode returnExpression = expression();

			statementNode = new ReturnStatementNode( returnExpression );

		}else{ throw new RuntimeException( "Couldnt find any token to start statement." ); }

		return statementNode;

	}

	ArrayList< ExpressionNode > expression_list(){

		ArrayList< ExpressionNode > expressionList = new ArrayList< ExpressionNode >();

		expressionList.add( expression() );

		while( lookAHead.getType() == EnumToken.COMMA ){

			match( EnumToken.COMMA );

			expressionList.add( expression() );

		}

		return expressionList;

	}

	ExpressionNode expression(){

		ExpressionNode result = null;

		ExpressionNode simple_expression1 = simple_expression();

		if( lookAHead.getType() == EnumToken.RELOP ){

			String relOpString = match( EnumToken.RELOP );

			ExpressionNode simple_expression2 = simple_expression();

			result = new OperationExpressionNode( simple_expression1, relOpString, simple_expression2 );
			
		}else{

			result = simple_expression1;

		}

		return result;

	}

	ExpressionNode simple_expression() throws RuntimeException {
		
		String signString = "+";

		if( lookAHead.getType() == EnumToken.SIGN ){

			signString = match( EnumToken.SIGN );

		}

		ArrayList< ExpressionNode > terms = new ArrayList< ExpressionNode >();

		terms.add( term() );
		
		ArrayList< String > addOpStrings = new ArrayList< String >();

		while( lookAHead.getType() == EnumToken.ADDOP ){

			addOpStrings.add( match( EnumToken.ADDOP ) );

			terms.add( term() );

		}

		ArrayList< ExpressionNode > result = new ArrayList< ExpressionNode >();

		result.add( terms.get( terms.size() - 1 ) );

		for( int i = addOpStrings.size() - 1; 0 <= i; i--){

			result.add( new OperationExpressionNode( terms.get( i ), addOpStrings.get( i ), result.get( result.size() - 1 ) ) );

		}

		return result.get( result.size() - 1 );

	}

	ExpressionNode term(){

		ArrayList< ExpressionNode > factors = new ArrayList< ExpressionNode >();

		factors.add( factor() );
		
		ArrayList< String > mulOpStrings = new ArrayList< String >();

		while( lookAHead.getType() == EnumToken.MULOP ){

			mulOpStrings.add( match( EnumToken.MULOP ) );

			factors.add( factor() );

		}

		ArrayList< ExpressionNode > result = new ArrayList< ExpressionNode >();

		result.add( factors.get( factors.size() - 1 ) );

		for( int i = mulOpStrings.size() - 1; 0 <= i; i--){

			result.add( new OperationExpressionNode( factors.get( i ), mulOpStrings.get( i ), result.get( result.size() - 1 ) ) );

		}

		return result.get( result.size() - 1 );

	}

	ExpressionNode factor() throws RuntimeException {

		ExpressionNode result = null;

		if( lookAHead.getType() == EnumToken.ID ){

			String idName = match( EnumToken.ID );

			if( lookAHead.getType() == EnumToken.LSPAREN ){

				if( !symbolTable.isVariableName( idName ) ){

					throw new RuntimeException( "Variable " + idName + " not declared." );

				}
				
				match( EnumToken.LSPAREN );

				ExpressionNode arrayIndex = expression();

				match( EnumToken.RSPAREN );

				result = new VariableValueExpressionNode( symbolTable.getVariableByName( idName ), (NumValueExpressionNode) arrayIndex );

			}else if( lookAHead.getType() == EnumToken.LRPAREN ){

				if( !symbolTable.isSubProgramName( idName ) ){

					throw new RuntimeException( "Subprogram " + idName + " not declared." );

				}

				match( EnumToken.LRPAREN );

				ArrayList< ExpressionNode > parameters = expression_list();

				match( EnumToken.RRPAREN );

				result = new SubProgramValueExpressionNode( symbolTable.getSubProgramByName( idName ), parameters );

			}else{

				if( !symbolTable.isVariableName( idName ) ){

					throw new RuntimeException( "Variable " + idName + " not declared." );

				}
				
				result = new VariableValueExpressionNode( symbolTable.getVariableByName( idName ) );

			}

		}else if( lookAHead.getType() == EnumToken.NUM ){

			result = new NumValueExpressionNode( match( EnumToken.NUM ) );

		}else if( lookAHead.getType() == EnumToken.NOT ){

			match( EnumToken.NOT );

			result = new NotValueExpressionNode( factor() );

		}else if( lookAHead.getType() == EnumToken.LRPAREN ){

			match( EnumToken.LRPAREN );

			result = expression();

			match( EnumToken.RRPAREN );

		}else{

			throw new RuntimeException( "Couldn't find token to start factor." );

		}

		return result;

	}

}




