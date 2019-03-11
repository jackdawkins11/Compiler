
package parser;

import scanner.*;
import symbolTable.*;
import syntaxTree.*;
import java.util.Vector;

public class Parser{

	Vector< Token > allTokens = new Vector< Token >();
	
	int glIter = 0;

	SymbolTable symbolTable = new SymbolTable();

	public Parser( MyScanner myScanner ){

		while( myScanner.hasNext() ){

			allTokens.add( myScanner.next() );

		}

	}

	public void printTable(){ symbolTable.print(); }
	
	public ProgramNode programNT(){

		int savedGlIter = glIter;

		//EXTRA

		ProgramNode programNode1 = null;

		//EXTRA

		if( glIter < allTokens.size() && allTokens.get( glIter ).getType() == EnumToken.PROGRAM ){

			glIter++;

			if( glIter < allTokens.size() && allTokens.get( glIter ).getType() == EnumToken.ID ){

				//EXTRA

				programNode1 = new ProgramNode( allTokens.get( glIter ).getString() );

				Symbol newProgramId = new Symbol( EnumId.PROGRAM, allTokens.get( glIter ).getString() );

				symbolTable.add( newProgramId );
				
				//EXTRA

				glIter ++;

				if( glIter < allTokens.size() && allTokens.get( glIter ).getType() == EnumToken.SEMICOLON ){

					glIter ++;

					//EXTRA

					DeclarationsNode declarationsNode1 = new DeclarationsNode();
					
					//EXTRA

					if( declarationsNT( declarationsNode1 ) ){

						//EXTRA

						programNode1.setVariables( declarationsNode1 );

						//EXTRA

						glIter ++;

						//EXTRA

						SubProgramDeclarationsNode subProgramDeclarationsNode1 = new SubProgramDeclarationsNode();

						//EXTRA

						if( subprogram_declarationsNT( subProgramDeclarationsNode1 ) ){

							//EXTRA

							programNode1.setFunctions( subProgramDeclarationsNode1 );

							//EXTRA

							glIter ++;

							//EXTRA

							CompoundStatementNode compoundStatementNode1 = new CompoundStatementNode();

							//EXTRA

							if( compound_statementNT( compoundStatementNode1 ) ){

								//EXTRA

								programNode1.setMain( compoundStatementNode1 );

								//EXTRA

								glIter ++;

								if( glIter < allTokens.size() && allTokens.get( glIter ).getType() == EnumToken.DOT ){

									return programNode1;

								}

							}

						}

					}

				}
			
				//EXTRA

				symbolTable.delete( EnumId.PROGRAM, newProgramId.getIdentifier() );

				//EXTRA

			}

		}
		glIter = savedGlIter;

		return null;

	}

	boolean identifier_listNT( Vector< String > identifiers ){

		int savedGlIter = glIter;

		if( glIter < allTokens.size() && allTokens.get( glIter ).getType() == EnumToken.ID ){

			//EXTRA

			identifiers.add( allTokens.get( glIter ).getString() );

			//EXTRA

			glIter ++;

			if( glIter < allTokens.size() && allTokens.get( glIter ).getType() == EnumToken.COMMA ){

				glIter++;

				if( identifier_listNT( identifiers ) ){

					return true ;

				}

			}

			//EXTRA

			identifiers.remove( identifiers.size() - 1 );

			//EXTRA

		}
		glIter = savedGlIter;

		if( glIter < allTokens.size() && allTokens.get( glIter ).getType() == EnumToken.ID ){

			//EXTRA

			identifiers.add( allTokens.get( glIter ).getString() );
			
			//EXTRA

			return true;

		}
		glIter = savedGlIter;

		return false;

	}

	boolean declarationsNT( DeclarationsNode declarationsNode1 ){

		int savedGlIter = glIter;

		if( glIter < allTokens.size() && allTokens.get( glIter ).getType() == EnumToken.VAR ){

			glIter++;

			//EXTRA

			Vector< String > identifiers = new Vector<String>();

			//EXTRA

			if( identifier_listNT( identifiers ) ){

				assert( 0 < identifiers.size() );

				glIter++;

				if( glIter < allTokens.size() && allTokens.get( glIter ).getType() == EnumToken.COLON ){

					glIter++;

					//EXTRA

					Symbol newSymbolType = new Symbol( EnumId.FUNCTION, "" );

					//EXTRA

					if( typeNT( newSymbolType ) ){

						assert( ( newSymbolType.getIdType() == EnumId.VARIABLE
								|| newSymbolType.getIdType() == EnumId.ARRAY )
						     		&& newSymbolType.getVarType() != EnumVar.NULL );

						glIter++;

						if( glIter < allTokens.size() && allTokens.get( glIter ).getType() == EnumToken.SEMICOLON ){

							glIter++;
						
							//EXTRA	

							for( int i = 0; i < identifiers.size(); i++){
								symbolTable.add( new Symbol( newSymbolType.getIdType(), newSymbolType.getVarType(),
										newSymbolType.getRows(), newSymbolType.getCols(),
										identifiers.get( i ) ) );
							}

							for( int i = 0; i < identifiers.size(); i++){
								declarationsNode1.addVariable( new Variable( identifiers.get( i ) ) );
							}

							//EXTRA

							if( declarationsNT( declarationsNode1 ) ){

								return true;

							}

						}

					}

				}

			}

		}
		glIter = savedGlIter;

		//empty
		glIter --;
		return true;

	}

	boolean typeNT( Symbol newSymbolType ){

		int savedGlIter = glIter;

		if( standard_typeNT( newSymbolType ) ){

			return true;

		}
		glIter = savedGlIter;

		if( glIter < allTokens.size() && allTokens.get( glIter ).getType() == EnumToken.ARRAY ){

			glIter++;

			if( glIter < allTokens.size() && allTokens.get( glIter ).getType() == EnumToken.LSPAREN ){

				glIter++;

				if( glIter < allTokens.size() && allTokens.get( glIter ).getType() == EnumToken.NUM ){

					int rows = 0;

					try{
						rows = Integer.parseInt( allTokens.get( glIter ).getString() );

					}catch( Exception e ){ e.printStackTrace(); }

					assert( 0 < rows );
					
					glIter++;

					if( glIter < allTokens.size() && allTokens.get( glIter ).getType() == EnumToken.COLON ){

						glIter++;

						if( glIter < allTokens.size() && allTokens.get( glIter ).getType() == EnumToken.NUM ){

							int cols = 0;

							try{
								cols = Integer.parseInt( allTokens.get( glIter ).getString() );

							}catch( Exception e ){ e.printStackTrace(); }
					
							assert( 0 < cols );

							glIter++;

							if( glIter < allTokens.size() && allTokens.get( glIter ).getType() == EnumToken.RSPAREN ){

								glIter++;

								if( glIter < allTokens.size() && allTokens.get( glIter ).getType() == EnumToken.OF ){

									glIter++;

									if( standard_typeNT( newSymbolType ) ){

										newSymbolType.init( EnumId.ARRAY, newSymbolType.getVarType(), rows, cols, "" ); 

										return true;

									}

								}

							}

						}

					}

				}

			}

		}
		glIter = savedGlIter;

		return false;

	}

	boolean standard_typeNT( Symbol newSymbolType ){

		int savedGlIter = glIter;

		if( glIter < allTokens.size() && allTokens.get( glIter ).getType() == EnumToken.REAL ){

			newSymbolType.init( EnumId.VARIABLE, EnumVar.REAL, "" );

			return true;

		}
		glIter = savedGlIter;

		if( glIter < allTokens.size() && allTokens.get( glIter ).getType() == EnumToken.INTEGER ){

			newSymbolType.init( EnumId.VARIABLE, EnumVar.INTEGER, "" );
			
			return true;

		}
		glIter = savedGlIter;

		return false;

	}

	boolean subprogram_declarationsNT( SubProgramDeclarationsNode subProgramDeclarationsNode1 ){

		int savedGlIter = glIter;

		//EXTRA

		SubProgramNode subProgramNode1 = new SubProgramNode();

		//EXTRA

		if( subprogram_declarationNT( subProgramNode1 ) ){

			//EXTRA

			subProgramDeclarationsNode.addSubProgram( subProgramNode1 );

			//EXTRA

			glIter++;

			if( glIter < allTokens.size() && allTokens.get( glIter ).getType() == EnumToken.SEMICOLON ){

				glIter++;

				if( subprogram_declarationsNT( subProgramDeclarationsNode ) ){

					return true;

				}

			}

		}
		glIter = savedGlIter;

		//empty
		glIter--;
		return true;

	}

	boolean subprogram_declarationNT( SubProgramNode subProgramNode ){

		int savedGlIter = glIter;

		if( subprogram_headNT( subProgramNode ) ){

			glIter++;

			//EXTRA

			DeclarationsNode declarationsNode = new DeclarationsNode();

			//EXTRA

			if( declarationsNT( declarationsNode ) ){

				//EXTRA

				subProgramNode.setVariables( declarationsNode );

				//EXTRA

				glIter++;

				//EXTRA

				CompoundStatementNode compoundStatementNode = new CompoundStatementNode();

				//EXTRA

				if( compound_statementNT( compoundStatementNode ) ){

					//EXTRA

					subProgramNode.setMain( compoundStatementNode );

					//EXTRA

					return true;

				}

			}

		}
		glIter = savedGlIter;

		return false;

	}

	boolean subprogram_headNT( SubProgramNode subProgramNode ){

		int savedGlIter = glIter;

		if( glIter < allTokens.size() && allTokens.get( glIter ).getType() == EnumToken.FUNCTION ){

			glIter++;

			if( glIter < allTokens.size() && allTokens.get( glIter ).getType() == EnumToken.ID ){

				//EXTRA

				Symbol functionSymbol = new Symbol( EnumId.FUNCTION, allTokens.get( glIter ).getString() );

				subProgramNode.setName( allTokens.get( glIter ).getString() );
				
				//EXTRA

				glIter ++;

				if( argumentsNT() ){

					glIter++;

					if( glIter < allTokens.size() && allTokens.get( glIter ).getType() == EnumToken.COLON ){

						glIter++;

						//EXTRA

						Symbol newSymbolType = new Symbol( EnumId.FUNCTION, "" );

						//EXTRA

						if( standard_typeNT( newSymbolType ) ){

							glIter++;

							if( glIter < allTokens.size() && allTokens.get( glIter ).getType() == EnumToken.SEMICOLON ){
					
								//EXTRA	

								symbolTable.add( functionSymbol );

								//EXTRA

								return true;

							}

						}

					}

				}

			}

		}
		glIter = savedGlIter;

		/*
		if( glIter < allTokens.size() && allTokens.get( glIter ).getType() == EnumToken.PROCEDURE ){

			glIter++;

			if( glIter < allTokens.size() && allTokens.get( glIter ).getType() == EnumToken.ID ){

				Symbol functionSymbol = new Symbol( EnumId.FUNCTION, allTokens.get( glIter ).getString() );
			
				subProgramNode.setName( allTokens.get( glIter ).getString() );

				glIter++;

				if( argumentsNT() ){

					glIter++;

					if( glIter < allTokens.size() && allTokens.get( glIter ).getType() == EnumToken.SEMICOLON ){

						symbolTable.add( functionSymbol );
						
						return true;

					}

				}


			}

		}
		glIter = savedGlIter;
		*/

		return false;

	}

	boolean argumentsNT(){

		int savedGlIter = glIter;

		if( glIter < allTokens.size() && allTokens.get( glIter ).getType() == EnumToken.LRPAREN ){

			glIter++;

			if( parameter_listNT() ){

				glIter++;

				if( glIter < allTokens.size() && allTokens.get( glIter ).getType() == EnumToken.RRPAREN ){

					return true;

				}

			}

		}
		glIter = savedGlIter;

		//empty
		glIter--;
		return true;

	}

	boolean parameter_listNT(){

		int savedGlIter = glIter;

		Vector< String > identifiers = new Vector<String>();

		if( identifier_listNT( identifiers ) ){

			assert( 0 < identifiers.size() );

			glIter++;

			if( glIter < allTokens.size() && allTokens.get( glIter ).getType() == EnumToken.COLON ){

				glIter++;

				Symbol newSymbolType = new Symbol( EnumId.FUNCTION, "") ;

				if( typeNT( newSymbolType ) ){
					
					assert( ( newSymbolType.getIdType() == EnumId.VARIABLE
							|| newSymbolType.getIdType() == EnumId.ARRAY )
					     		&& newSymbolType.getVarType() != EnumVar.NULL );

					glIter++;

					if( glIter < allTokens.size() && allTokens.get( glIter ).getType() == EnumToken.SEMICOLON ){

						glIter++;

						if( parameter_listNT() ){
							
							for( int i = 0; i < identifiers.size(); i++){
								symbolTable.add( new Symbol( newSymbolType.getIdType(), newSymbolType.getVarType(),
										newSymbolType.getRows(), newSymbolType.getCols(),
										identifiers.get( i ) ) );
							}

							return true;

						}

					}

				}

			}

		}
		glIter = savedGlIter;

		identifiers.clear();

		if( identifier_listNT( identifiers ) ){

			assert( 0 < identifiers.size() );
			
			glIter++;

			if( glIter < allTokens.size() && allTokens.get( glIter ).getType() == EnumToken.COLON ){

				glIter++;

				Symbol newSymbolType = new Symbol( EnumId.FUNCTION, "" ) ;

				if( typeNT( newSymbolType ) ){
		
					assert( ( newSymbolType.getIdType() == EnumId.VARIABLE
							|| newSymbolType.getIdType() == EnumId.ARRAY )
					     		&& newSymbolType.getVarType() != EnumVar.NULL );

					for( int i = 0; i < identifiers.size(); i++){
						symbolTable.add( new Symbol( newSymbolType.getIdType(), newSymbolType.getVarType(),
							newSymbolType.getRows(), newSymbolType.getCols(),
							identifiers.get( i ) ) );
					}
					
					return true;

				}

			}

		}
		glIter = savedGlIter;

		return false;

	}

	boolean compound_statementNT( CompoundStatementNode compoundStatementNode ){

		int savedGlIter = glIter;

		//"begin"
		if( glIter < allTokens.size() && allTokens.get( glIter ).getType() == EnumToken.BEGIN ){

			glIter++;

			//optional_statements
			if( optional_statementsNT( compoundStatementNode ) ){

				glIter++;

				//"end"
				if( glIter < allTokens.size() && allTokens.get( glIter ).getType() == EnumToken.END ){

					return true;

				}

			}

		}
		glIter = savedGlIter;

		return false;

	}

	boolean optional_statementsNT( CompoundStatementNode compoundStatementNode ){

		int savedGlIter = glIter;

		//statement_list
		if( statement_listNT( compoundStatementNode ) ){

			return true;

		}
		glIter = savedGlIter;

		//empty
		glIter--;
		return true;

	}

	boolean statement_listNT( compoundStatementNode ){

		int savedGlIter = glIter;

		//statement
		if( statementNT( compoundStatementNode ) ){

			glIter++;

			//";"
			if( allTokens.get( glIter ).getType() == EnumToken.SEMICOLON ){

				glIter++;

				//statement_list
				if( statement_listNT( compoundStatementNode ) ){

					return true;

				}

			}

		}
		glIter = savedGlIter;

		compoundStatementNode.clear();

		//statement
		if( statementNT( compoundStatementNode ) ){

			return true;

		}
		glIter = savedGlIter;

		return false;

	}

	boolean statementNT( CompoundStatementNode compoundStatementNode ){

		int savedGlIter = glIter;

		VariableNode variableNode = new VariableNode();

		//variable
		if( variableNT( variableNode ) ){

			glIter++;

			//assignop
			if( glIter < allTokens.size() && allTokens.get( glIter ).getType() == EnumToken.ASSIGNOP ){

				glIter++;

				ExpressionNode expressionNode 
				
				//expression
				if( expressionNT() ){

					return true;

				}

			}

		}
		glIter = savedGlIter;

		//procedure_statement
		if( procedure_statementNT() ){

			return true;

		}
		glIter = savedGlIter;

		//compound_statement
		if( compound_statementNT() ){

			return true;

		}
		glIter = savedGlIter;

		//"if"
		if( glIter < allTokens.size() && allTokens.get( glIter ).getType() == EnumToken.IF ){

			glIter++;

			//expression
			if( expressionNT() ){

				glIter++;

				//"then"
				if( glIter < allTokens.size() && allTokens.get( glIter ).getType() == EnumToken.THEN ){

					glIter++;

					//statement
					if( statementNT() ){

						glIter++;

						//"else"
						if( glIter < allTokens.size() && allTokens.get( glIter ).getType() == EnumToken.ELSE ){

							glIter++;

							//statement
							if( statementNT() ){

								return true;

							}

						}
					
					}

				}

			}

		}
		glIter = savedGlIter;

		//"while"
		if( glIter < allTokens.size() && allTokens.get( glIter ).getType() == EnumToken.WHILE ){

			glIter++;

			//expression
			if( expressionNT() ){

				glIter++;

				//"do"
				if( glIter < allTokens.size() && allTokens.get( glIter ).getType() == EnumToken.DO ){

					glIter++;

					//statement
					if( statementNT() ){

						return true;

					}

				}

			}

		}
		glIter = savedGlIter;

		//"read"
		if( glIter < allTokens.size() && allTokens.get( glIter ).getType() == EnumToken.READ ){

			glIter++;

			//"("
			if( glIter < allTokens.size() && allTokens.get( glIter ).getType() == EnumToken.LRPAREN ){

				glIter++;

				//id
				if( glIter < allTokens.size() && allTokens.get( glIter ).getType() == EnumToken.ID ){

					glIter++;

					//")"
					if( glIter < allTokens.size() && allTokens.get( glIter ).getType() == EnumToken.RRPAREN ){

						return true;

					}

				}

			}

		}
		glIter = savedGlIter;

		//"write"
		if( glIter < allTokens.size() && allTokens.get( glIter ).getType() == EnumToken.WRITE ){

			glIter++;

			//"("
			if( glIter < allTokens.size() && allTokens.get( glIter ).getType() == EnumToken.LRPAREN ){

				glIter++;

				//expression
				if( expressionNT() ){

					glIter++;

					//")"
					if( glIter < allTokens.size() && allTokens.get( glIter ).getType() == EnumToken.RRPAREN ){

						return true;

					}

				}

			}

		}
		glIter = savedGlIter;

		//"return"
		if( glIter < allTokens.size() && allTokens.get( glIter ).getType() == EnumToken.RETURN ){

			glIter++;

			//expression
			if( expressionNT() ){

				return true;

			}

		}
		glIter = savedGlIter;

		return false;

	}

	boolean variableNT(){

		int savedGlIter = glIter;
		
		//id
		if( glIter < allTokens.size() && allTokens.get( glIter ).getType() == EnumToken.ID
		 	&& symbolTable.exists( EnumId.ARRAY, allTokens.get( glIter ).getString() ) ){		//check symbol table

			glIter++;

			//"["
			if( glIter < allTokens.size() && allTokens.get( glIter ).getType() == EnumToken.LSPAREN ){

				glIter++;

				//expression
				if( expressionNT() ){

					glIter++;

					//"]"
					if( glIter < allTokens.size() && allTokens.get( glIter ).getType() == EnumToken.RSPAREN ){

						return true;

					}

				}

			}

		}
		glIter = savedGlIter;

		//id
		if( glIter < allTokens.size() && allTokens.get( glIter ).getType() == EnumToken.ID 
				&& symbolTable.exists( EnumId.VARIABLE, allTokens.get( glIter ).getString() ) ){		//check symbol table

			return true;

		}
		glIter = savedGlIter;

		return false;

	}

	boolean procedure_statementNT(){

		int savedGlIter= glIter;

		//id
		if( glIter < allTokens.size() && allTokens.get( glIter ).getType() == EnumToken.ID 
				&& symbolTable.exists( EnumId.FUNCTION, allTokens.get( glIter ).getString() ) ){		//check symbol table

			glIter++;

			//"("
			if( glIter < allTokens.size() && allTokens.get( glIter ).getType() == EnumToken.LRPAREN ){

				glIter++;

				//expression_list
				if( expression_listNT() ){

					glIter++;

					//")"
					if( glIter < allTokens.size() && allTokens.get( glIter ).getType() == EnumToken.RRPAREN ){

						return true;

					}

				}

			}

		}
		glIter = savedGlIter;

		//id
		if( glIter < allTokens.size() && allTokens.get( glIter ).getType() == EnumToken.ID ){

			return true;

		}
		glIter = savedGlIter;

		return false;

	}

	boolean expression_listNT(){

		int savedGlIter = glIter;

		//expression
		if( expressionNT() ){

			glIter++;

			//","
			if( glIter < allTokens.size() && allTokens.get( glIter ).getType() == EnumToken.COMMA ){

				glIter++;

				//expression_list
				if( expression_listNT() ){

					return true;

				}

			}

		}
		glIter = savedGlIter;

		//expression
		if( expressionNT() ){

			return true;

		}
		glIter = savedGlIter;

		return false;

	}

	boolean expressionNT(){

		int savedGlIter = glIter;

		//simple_expression
		if( simple_expressionNT() ){

			glIter++;

			//relop
			if( allTokens.get( glIter ).getType() == EnumToken.RELOP ){

				glIter++;

				//simple_expression
				if( simple_expressionNT() ){

					return true;

				}

			}

		}
		glIter = savedGlIter;

		//simple_expression
		if( simple_expressionNT() ){

			return true;

		}
		glIter = savedGlIter;

		return false;

	}

	boolean simple_expressionNT(){

		int savedGlIter = glIter;

		//term
		if( termNT() ){

			glIter++;

			//simple_part
			if( simple_partNT() ){

				return true;

			}

		}
		glIter = savedGlIter;

		//sign
		if( signNT() ){

			glIter++;

			//term
			if( termNT() ){

				glIter++;

				//simple_part
				if( simple_partNT() ){

					return true;

				}

			}

		}
		glIter = savedGlIter;

		return false;

	}

	boolean simple_partNT(){

		int savedGlIter = glIter;

		//addop
		if( glIter < allTokens.size() && allTokens.get( glIter ).getType() == EnumToken.ADDOP ){

			glIter++;

			//term
			if( termNT() ){

				glIter++;

				//simple_part
				if( simple_partNT() ){

					return true;

				}
			}

		}
		glIter = savedGlIter;

		//empty
		glIter--;
		return true;

	}

	boolean termNT(){

		int savedGlIter = glIter;

		//factor
		if( factorNT() ){

			glIter++;

			//term_part
			if( term_partNT() ){

				return true;

			}

		}
		glIter = savedGlIter;

		return false;
	}

	boolean term_partNT(){

		int savedGlIter = glIter;

		//mulop
		if( glIter < allTokens.size() && allTokens.get( glIter ).getType() == EnumToken.MULOP ){

			glIter++;

			//factor
			if( factorNT() ){

				glIter++;

				//term_part
				if( term_partNT() ){

					return true;

				}

			}

		}
		glIter = savedGlIter;

		//empty
		glIter--;
		return true;

	}

	boolean factorNT(){

		int savedGlIter = glIter;

		//id
		if( glIter < allTokens.size() && allTokens.get( glIter ).getType() == EnumToken.ID 
		 	&& symbolTable.exists( EnumId.VARIABLE, allTokens.get( glIter ).getString() ) ){		//check symbol table

			glIter++;

			//"["
			if( glIter < allTokens.size() && allTokens.get( glIter ).getType() == EnumToken.LSPAREN ){

				glIter++;

				//expression
				if( expressionNT() ){

					glIter++;

					//"]"
					if( glIter < allTokens.size() && allTokens.get( glIter ).getType() == EnumToken.RSPAREN ){

						return true;

					}

				}

			}

		}
		glIter = savedGlIter;

		//id
		if( glIter < allTokens.size() && allTokens.get( glIter ).getType() == EnumToken.ID 
		 	&& symbolTable.exists( EnumId.VARIABLE, allTokens.get( glIter ).getString() ) ){		//check symbol table

			glIter++;

			//"("
			if( glIter < allTokens.size() && allTokens.get( glIter ).getType() == EnumToken.LRPAREN ){

				glIter++;

				//expression_list
				if( expression_listNT() ){

					glIter++;

					//")"
					if( glIter < allTokens.size() && allTokens.get( glIter ).getType() == EnumToken.RRPAREN ){

						return true;

					}

				}

			}

		}
		glIter = savedGlIter;

		//id
		if( glIter < allTokens.size() && allTokens.get( glIter ).getType() == EnumToken.ID 
		 	&& symbolTable.exists( EnumId.VARIABLE, allTokens.get( glIter ).getString() ) ){		//check symbol table

			return true;

		}
		glIter = savedGlIter;

		//num
		if( glIter < allTokens.size() && allTokens.get( glIter ).getType() == EnumToken.NUM ){

			return true;

		}
		glIter = savedGlIter;

		//"("
		if( glIter < allTokens.size() && allTokens.get( glIter ).getType() == EnumToken.LRPAREN ){

			glIter++;

			if( expressionNT() ){

				glIter++;

				//")"
				if( glIter < allTokens.size() && allTokens.get( glIter ).getType() == EnumToken.RRPAREN ){

					return true;

				}

			}

		}
		glIter = savedGlIter;

		//not
		if( glIter < allTokens.size() && allTokens.get( glIter ).getType() == EnumToken.NOT ){

			glIter++;

			//factor
			if( factorNT() ){

				return true;

			}

		}
		glIter = savedGlIter;

		return false;

	}

	boolean signNT(){

		int savedGlIter = glIter;

		//+
		if( glIter < allTokens.size() && allTokens.get( glIter ).getString().equals( "+" ) ){

			return true;

		}
		glIter = savedGlIter;

		//-
		if( glIter < allTokens.size() && allTokens.get( glIter ).getString().equals( "-" ) ){

			return true;

		}
		glIter = savedGlIter;

		return false;

	}

}


