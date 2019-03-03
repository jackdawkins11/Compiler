
package parser;

//get tokens

import scanner.*;

//symbol table

import symbolTable.*;

//read from a file

import java.io.InputStreamReader;
import java.io.FileInputStream;

//hold the tokens

import java.util.Vector;

public class Recognizer{

	//all the tokens in the file

	Vector< Token > allTokens;

	//shared iterator for keeping track of our place in allTokens

	int glIter;

	//symbolTable
	
	SymbolTable symbolTable = new SymbolTable();

	public void printTable(){ symbolTable.print(); }

	//the constructor takes a filename and fills allTokens 

	public Recognizer( String filename ){

		//create an ISR

		FileInputStream code_fis = null;

		try{

			code_fis = new FileInputStream( filename );

		}catch (Exception e ){ e.printStackTrace(); }

		InputStreamReader code_isr = new InputStreamReader( code_fis );

		/* create the scanner */

		MyScanner code = new MyScanner( code_isr );

		//read it to the vector

		allTokens = new Vector< Token >();

		while( code.hasNext() ){

			allTokens.add( code.next() );

		}

		glIter = 0;

	}

	//all the following functions:
	//  on fail, they leave glIter the same.
	//  on success, glIter ends at the last
	//  token
	
	public boolean programNT(){

		int savedGlIter = glIter;

		//"program"
		if( glIter < allTokens.size() && allTokens.get( glIter ).getType() == EnumToken.PROGRAM ){

			glIter ++;

			//ID
			if( glIter < allTokens.size() && allTokens.get( glIter ).getType() == EnumToken.ID ){
	
				//create program identifier
				Symbol newProgramId = new Symbol( EnumId.PROGRAM, allTokens.get( glIter ).getString() );

				glIter ++;

				//";"
				if( glIter < allTokens.size() && allTokens.get( glIter ).getType() == EnumToken.SEMICOLON ){

					glIter ++;
					
					//add program identifier
					symbolTable.add( newProgramId );

					//non-terminal declarations
					if( declarationsNT() ){

						glIter ++;

						//non-terminal subprogram_declarations
						if( subprogram_declarationsNT() ){

							glIter ++;

							//non-terminal compound_statement
							if( compound_statementNT() ){

								glIter ++;

								//"."
								if( glIter < allTokens.size() && allTokens.get( glIter ).getType() == EnumToken.DOT ){

									return true;

								}

							}

						}

					}

					//remove the program identifier
					symbolTable.delete( EnumId.PROGRAM, newProgramId.getIdentifier() );

				}

			}

		}
		glIter = savedGlIter;

		return false;

	}

	boolean identifier_listNT( Vector< String > identifiers ){

		int savedGlIter = glIter;

		//ID
		if( glIter < allTokens.size() && allTokens.get( glIter ).getType() == EnumToken.ID ){

			identifiers.add( allTokens.get( glIter ).getString() );

			glIter ++;

			//","
			if( glIter < allTokens.size() && allTokens.get( glIter ).getType() == EnumToken.COMMA ){

				glIter++;

				//identifier_list
				if( identifier_listNT( identifiers ) ){

					return true ;

				}

			}

			identifiers.remove( identifiers.size() - 1 );

		}
		glIter = savedGlIter;

		//ID
		if( glIter < allTokens.size() && allTokens.get( glIter ).getType() == EnumToken.ID ){

			identifiers.add( allTokens.get( glIter ).getString() );
			
			return true;

		}
		glIter = savedGlIter;

		return false;

	}

	boolean declarationsNT(){

		int savedGlIter = glIter;

		//"var"
		if( glIter < allTokens.size() && allTokens.get( glIter ).getType() == EnumToken.VAR ){

			glIter++;

			//create the vector of identifiers

			Vector< String > identifiers = new Vector<String>();

			//identifier_list
			if( identifier_listNT( identifiers ) ){

				assert( 0 < identifiers.size() );

				glIter++;

				//":"
				if( glIter < allTokens.size() && allTokens.get( glIter ).getType() == EnumToken.COLON ){

					glIter++;

					//create a dummy symbol to
					//pass to typeNT to discover the type

					Symbol newSymbolType = new Symbol( EnumId.FUNCTION, "" );

					//type
					if( typeNT( newSymbolType ) ){

						assert( ( newSymbolType.getIdType() == EnumId.VARIABLE
								|| newSymbolType.getIdType() == EnumId.ARRAY )
						     		&& newSymbolType.getVarType() != EnumVar.NULL );

						glIter++;

						//";"
						if( glIter < allTokens.size() && allTokens.get( glIter ).getType() == EnumToken.SEMICOLON ){

							glIter++;

							//declarations
							if( declarationsNT() ){

								//add the symbols
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

		}
		glIter = savedGlIter;

		//empty
		glIter --;
		return true;

	}

	boolean typeNT( Symbol newSymbolType ){

		int savedGlIter = glIter;

		//standard_type
		if( standard_typeNT( newSymbolType ) ){

			return true;

		}
		glIter = savedGlIter;

		//"array"
		if( glIter < allTokens.size() && allTokens.get( glIter ).getType() == EnumToken.ARRAY ){

			glIter++;

			//"["
			if( glIter < allTokens.size() && allTokens.get( glIter ).getType() == EnumToken.LSPAREN ){

				glIter++;

				//num
				if( glIter < allTokens.size() && allTokens.get( glIter ).getType() == EnumToken.NUM ){

					int rows = 0;

					try{
						rows = Integer.parseInt( allTokens.get( glIter ).getString() );

					}catch( Exception e ){ e.printStackTrace(); }

					assert( 0 < rows );
					
					glIter++;

					//":"
					if( glIter < allTokens.size() && allTokens.get( glIter ).getType() == EnumToken.COLON ){

						glIter++;

						//num
						if( glIter < allTokens.size() && allTokens.get( glIter ).getType() == EnumToken.NUM ){

							int cols = 0;

							try{
								cols = Integer.parseInt( allTokens.get( glIter ).getString() );

							}catch( Exception e ){ e.printStackTrace(); }
					
							assert( 0 < cols );

							glIter++;

							//"]"
							if( glIter < allTokens.size() && allTokens.get( glIter ).getType() == EnumToken.RSPAREN ){

								glIter++;

								//"of"
								if( glIter < allTokens.size() && allTokens.get( glIter ).getType() == EnumToken.OF ){

									glIter++;

									//standard_type
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

		//"integer"
		if( glIter < allTokens.size() && allTokens.get( glIter ).getType() == EnumToken.REAL ){

			newSymbolType.init( EnumId.VARIABLE, EnumVar.REAL, "" );

			return true;

		}
		glIter = savedGlIter;

		//"real"
		if( glIter < allTokens.size() && allTokens.get( glIter ).getType() == EnumToken.INTEGER ){

			newSymbolType.init( EnumId.VARIABLE, EnumVar.INTEGER, "" );
			
			return true;

		}
		glIter = savedGlIter;

		return false;

	}

	boolean subprogram_declarationsNT(){

		int savedGlIter = glIter;

		//subprogram_declarations
		if( subprogram_declarationNT() ){

			glIter++;

			//";"
			if( glIter < allTokens.size() && allTokens.get( glIter ).getType() == EnumToken.SEMICOLON ){

				glIter++;

				//subprogram_declarations
				if( subprogram_declarationsNT() ){

					return true;

				}

			}

		}
		glIter = savedGlIter;

		//empty
		glIter--;
		return true;

	}

	boolean subprogram_declarationNT(){

		int savedGlIter = glIter;

		//subprogram_head
		if( subprogram_headNT() ){

			glIter++;

			//declarations
			if( declarationsNT() ){

				glIter++;

				//compound_statement
				if( compound_statementNT() ){

					return true;

				}

			}

		}
		glIter = savedGlIter;

		return false;

	}

	boolean subprogram_headNT(){

		int savedGlIter = glIter;

		//"function"
		if( glIter < allTokens.size() && allTokens.get( glIter ).getType() == EnumToken.FUNCTION ){

			glIter++;

			//id
			if( glIter < allTokens.size() && allTokens.get( glIter ).getType() == EnumToken.ID ){

				Symbol functionSymbol = new Symbol( EnumId.FUNCTION, allTokens.get( glIter ).getString() );

				glIter ++;

				//arguments
				if( argumentsNT() ){

					glIter++;

					//":"
					if( glIter < allTokens.size() && allTokens.get( glIter ).getType() == EnumToken.COLON ){

						glIter++;

						Symbol newSymbolType = new Symbol( EnumId.FUNCTION, "" );

						//standard_type
						if( standard_typeNT( newSymbolType ) ){

							glIter++;

							//";"
							if( glIter < allTokens.size() && allTokens.get( glIter ).getType() == EnumToken.SEMICOLON ){
							
								symbolTable.add( functionSymbol );

								return true;

							}

						}

					}

				}

			}

		}
		glIter = savedGlIter;

		//procedure
		if( glIter < allTokens.size() && allTokens.get( glIter ).getType() == EnumToken.PROCEDURE ){

			glIter++;

			//ID
			if( glIter < allTokens.size() && allTokens.get( glIter ).getType() == EnumToken.ID ){

				Symbol functionSymbol = new Symbol( EnumId.FUNCTION, allTokens.get( glIter ).getString() );
				
				glIter++;

				//arguments
				if( argumentsNT() ){

					glIter++;

					//";"
					if( glIter < allTokens.size() && allTokens.get( glIter ).getType() == EnumToken.SEMICOLON ){

						symbolTable.add( functionSymbol );
						
						return true;

					}

				}


			}

		}
		glIter = savedGlIter;

		return false;

	}

	boolean argumentsNT(){

		int savedGlIter = glIter;

		//"("
		if( glIter < allTokens.size() && allTokens.get( glIter ).getType() == EnumToken.LRPAREN ){

			glIter++;

			//parameter_list
			if( parameter_listNT() ){

				glIter++;

				//")"
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

		//identifier_list
		if( identifier_listNT( identifiers ) ){

			assert( 0 < identifiers.size() );

			glIter++;

			//":"
			if( glIter < allTokens.size() && allTokens.get( glIter ).getType() == EnumToken.COLON ){

				glIter++;

				Symbol newSymbolType = new Symbol( EnumId.FUNCTION, "") ;

				//type
				if( typeNT( newSymbolType ) ){
					
					assert( ( newSymbolType.getIdType() == EnumId.VARIABLE
							|| newSymbolType.getIdType() == EnumId.ARRAY )
					     		&& newSymbolType.getVarType() != EnumVar.NULL );

					glIter++;

					//";"
					if( glIter < allTokens.size() && allTokens.get( glIter ).getType() == EnumToken.SEMICOLON ){

						glIter++;

						//parameter_list
						if( parameter_listNT() ){
							
							//add the symbols
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

		//identifier_list
		if( identifier_listNT( identifiers ) ){

			assert( 0 < identifiers.size() );
			
			glIter++;

			//":"
			if( glIter < allTokens.size() && allTokens.get( glIter ).getType() == EnumToken.COLON ){

				glIter++;

				Symbol newSymbolType = new Symbol( EnumId.FUNCTION, "" ) ;

				//type
				if( typeNT( newSymbolType ) ){
		
					assert( ( newSymbolType.getIdType() == EnumId.VARIABLE
							|| newSymbolType.getIdType() == EnumId.ARRAY )
					     		&& newSymbolType.getVarType() != EnumVar.NULL );

					//add the symbols
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

	boolean compound_statementNT(){

		int savedGlIter = glIter;

		//"begin"
		if( glIter < allTokens.size() && allTokens.get( glIter ).getType() == EnumToken.BEGIN ){

			glIter++;

			//optional_statements
			if( optional_statementsNT() ){

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

	boolean optional_statementsNT(){

		int savedGlIter = glIter;

		//statement_list
		if( statement_listNT() ){

			return true;

		}
		glIter = savedGlIter;

		//empty
		glIter--;
		return true;

	}

	boolean statement_listNT(){

		int savedGlIter = glIter;

		//statement
		if( statementNT() ){

			glIter++;

			//";"
			if( allTokens.get( glIter ).getType() == EnumToken.SEMICOLON ){

				glIter++;

				//statement_list
				if( statement_listNT() ){

					return true;

				}

			}

		}
		glIter = savedGlIter;

		//statement
		if( statementNT() ){

			return true;

		}
		glIter = savedGlIter;

		return false;

	}

	boolean statementNT(){

		int savedGlIter = glIter;

		//variable
		if( variableNT() ){

			glIter++;

			//assignop
			if( glIter < allTokens.size() && allTokens.get( glIter ).getType() == EnumToken.ASSIGNOP ){

				glIter++;

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


