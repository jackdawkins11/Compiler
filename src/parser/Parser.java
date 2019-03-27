
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

	public ProgramNode program() throws Exception {

		try{

		match( EnumToken.PROGRAM );

		String programName = match( EnumToken.ID );

		match( EnumToken.SEMICOLON );
		
		DeclarationsNode variables = declarations() ;

		SubProgramDeclarationsNode functions = subprogram_declarations() ;

		CompoundStatementNode main = compound_statement() ;

		match( EnumToken.DOT );

		return new ProgramNode( programName, variables, functions, main );
	
		}catch( Exception e ){

			throw e;

		}
	}

	public void printSymbolTable(){

		symbolTable.print();

	}

	////////////////////////
	//      Helpers       //
	////////////////////////	
	
	private String match( EnumToken expectedType ) throws Exception {

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

			throw new Exception( "Trying to match a " + expectedType.toString() + " failed. Found " + lookAHeadTypeString
				+ " instead." );

		}

		return ret;

	}

	ArrayList< String > identifier_list() throws Exception {

		try{

		ArrayList< String > ID_list = new ArrayList< String >();

		ID_list.add( match( EnumToken.ID ) );

		while( lookAHead.getType() == EnumToken.COMMA ){

			match( EnumToken.COMMA );

			ID_list.add( match( EnumToken.ID ) );

		}

		return ID_list;

		} catch( Exception e ){

			throw e;

		}
	
	}

	DeclarationsNode declarations() throws Exception {

		try{

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

		}catch( Exception e ){

			throw e;

		}
	}
	
	VariableType type() throws Exception {

		try{

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

			}else{ throw new Exception( "error" ); }

		}

		return variableType;
	
		}catch( Exception e ){

			throw e;

		}
	}

	SubProgramDeclarationsNode subprogram_declarations() throws Exception {

		try{

		SubProgramDeclarationsNode subProgramDeclarationsNode = new SubProgramDeclarationsNode();

		while( lookAHead.getType() == EnumToken.FUNCTION
				|| lookAHead.getType() == EnumToken.PROCEDURE ){

			SubProgramNode subProgramNode = subprogram_declaration();

			subProgramDeclarationsNode.addSubProgram( subProgramNode );

			symbolTable.addSubProgram( subProgramNode );

			match( EnumToken.SEMICOLON );

		}

		return subProgramDeclarationsNode;

		}catch( Exception e ){

			throw e;

		}

	}

	SubProgramNode subprogram_declaration() throws Exception {

		try{

		SubProgramNode subProgramNode = null;

		if( lookAHead.getType() == EnumToken.FUNCTION ){

			match( EnumToken.FUNCTION );

			String functionName = match( EnumToken.ID );

			DeclarationsNode parameters = arguments();

			match( EnumToken.COLON );

			VariableType functionType = type();

			match( EnumToken.SEMICOLON );

			DeclarationsNode variables = declarations() ;

			CompoundStatementNode main = compound_statement() ;

			variables.addVariables( parameters );

			subProgramNode = new SubProgramNode( functionName,
					functionType,
					variables,
					main );

		}else if( lookAHead.getType() == EnumToken.PROCEDURE ){

			match( EnumToken.PROCEDURE );

			String procedureName = match( EnumToken.ID );

			DeclarationsNode parameters = arguments();

			match( EnumToken.SEMICOLON );

			VariableType procedureType = new VariableType( -1, -1, EnumStandardType.VOID );

			DeclarationsNode variables = declarations() ;

			CompoundStatementNode main = compound_statement() ;

			variables.addVariables( parameters );
			
			subProgramNode = new SubProgramNode( procedureName,
					procedureType,
					variables,
					main );

		}else{

			throw new Exception( "subprogram_declaration" );

		}

		return subProgramNode;

		}catch( Exception e ){

			throw e;

		}
	}

	DeclarationsNode arguments() throws Exception {

		try{

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

		}catch( Exception e ){

			throw e;

		}
	}

	CompoundStatementNode compound_statement() throws Exception {

		try{

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

		}catch( Exception e ){

			throw e;

		}
	
	}

	StatementNode statement() throws Exception {

		try{

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

				statementNode = new VariableAssignmentStatementNode( variableNode, new NumValueExpressionNode( "0" ), rValue );

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

			statementNode = new ReadStatementNode( readName );

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

		}

		return statementNode;

		}catch( Exception e ){

			throw e;

		}
	}

	ArrayList< ExpressionNode > expression_list() throws Exception {

		try{

		ArrayList< ExpressionNode > expressionList = new ArrayList< ExpressionNode >();

		expressionList.add( expression() );

		while( lookAHead.getType() == EnumToken.COMMA ){

			match( EnumToken.COMMA );

			expressionList.add( expression() );

		}

		return expressionList;

		}catch( Exception e ){

			throw e;

		}

	}

	ExpressionNode expression() throws Exception {

		try{

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

		} catch( Exception e ){

			throw e;

		}
	}

	ExpressionNode simple_expression() throws Exception {
		
		try{

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

		}catch( Exception e ){

			throw e;

		}
	}

	ExpressionNode term() throws Exception {

		try{

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

		}catch( Exception e ){

			throw e;

		}
	}

	ExpressionNode factor() throws Exception {

		try{

		ExpressionNode result = null;

		if( lookAHead.getType() == EnumToken.ID ){

			String idName = match( EnumToken.ID );

			if( lookAHead.getType() == EnumToken.LSPAREN ){

				match( EnumToken.LSPAREN );

				ExpressionNode arrayIndex = expression();

				match( EnumToken.RSPAREN );

				result = new VariableValueExpressionNode( symbolTable.getVariableByName( idName ), (NumValueExpressionNode) arrayIndex );

			}else if( lookAHead.getType() == EnumToken.LRPAREN ){

				match( EnumToken.LRPAREN );

				ArrayList< ExpressionNode > parameters = expression_list();

				match( EnumToken.RRPAREN );

				result = new SubProgramValueExpressionNode( symbolTable.getSubProgramByName( idName ), parameters );

			}else{

				result = new VariableValueExpressionNode( symbolTable.getVariableByName( idName ), new NumValueExpressionNode( "0" ) );

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

			assert( false );

		}

		return result;	

		}catch( Exception e ){

			throw e;

		}
	}

}




