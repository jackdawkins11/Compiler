
package Parser;
import java.util.Vector;

import Scanner.*;

public class Statement extends Node{
	
	private boolean tryVariableAssignopExpression( final Vector< Token > all_tokens, int i ){

		boolean success = false;

		Variable variable = new Variable( all_tokens, i );

		if( variable.accepted() ){

			i += variable.numLeaves();

			children.add( variable );

			Assignop assignop = new Assignop( all_tokens, i );

			if( assignop.accepted() ){

				i += assignop.numLeaves();

				children.add( assignop );

				Expression expression = new Expression( all_tokens, i );

				if( expression.accepted() ){

					children.add( expression );

					success = true;
				
				}

			}

		}

		if( !success ){

			children.clear();

		}

		return success;
	
	}

	private boolean tryIfExpressionThenStatementElseStatement( final Vector< Token > all_tokens, int i ){

		boolean success = false;

		If _if = new If( all_tokens, i );

		if( _if.accepted() ){

			i += _if.numLeaves();

			children.add( _if );

			Expression expression = new Expression( all_tokens, i );

			if( expression.accepted() ){

				i += expression.numLeaves();

				children.add( expression );

				Then then = new Then( all_tokens, i );

				if( then.accepted() ){

					i += then.numLeaves();

					children.add( then );

					Statement statement1 = new Statement( all_tokens, i );

					if( statement1.accepted() ){

						i += statement1.numLeaves();

						children.add( statement1 );

						Else _else = new Else( all_tokens, i );

						if( _else.accepted() ){

							i += _else.numLeaves();

							children.add( _else );

							Statement statement2 = new Statement( all_tokens, i );

							if( statement2.accepted() ){

								children.add( statement2 );

								success = true;

							}

						}

					}

				}

			}

		}

		if( !success ){

			children.clear();

		}

		return success;
	
	}

	private boolean tryWhileExpressionDoStatement( final Vector< Token > all_tokens, int i ){

		boolean success = false;

		While _while = new While( all_tokens, i );

		if( _while.accepted() ){

			i += _while.numLeaves();

			children.add( _while );

			Expression expression = new Expression( all_tokens, i );

			if( expression.accepted() ){

				i += expression.numLeaves();

				children.add( expression );

				Do _do = new Do( all_tokens, i );

				if( _do.accepted() ){

					i += _do.numLeaves();

					children.add( _do );

					Statement statement1 = new Statement( all_tokens, i );

					if( statement1.accepted() ){

						children.add( statement1 );

						success = true;

					}

				}

			}

		}

		if( !success ){

			children.clear();

		}

		return success;
	
	}

	private boolean tryProcedureStatement( final Vector< Token > all_tokens, int i ){

		boolean success = false;

		ProcedureStatement procedureStatement = new ProcedureStatement( all_tokens, i );

		if( procedureStatement.accepted() ){

			children.add( procedureStatement );

			success = true;
		
		}

		if( !success ){

			children.clear();

		}

		return success;

	}
	
	private boolean tryCompoundStatement( final Vector< Token > all_tokens, int i ){

		boolean success = false;

		CompoundStatement compoundStatement = new CompoundStatement( all_tokens, i );

		if( compoundStatement.accepted() ){

			children.add( compoundStatement );

			success = true;
		
		}

		if( !success ){

			children.clear();

		}

		return success;

	}

	private boolean tryReadParenIdParen( final Vector< Token > all_tokens, int i ){

		boolean success = false;

		Read read = new Read( all_tokens, i );

		if( read.accepted() ){

			children.add( read );

			i += read.numLeaves();

			LRParen lrParen = new LRParen( all_tokens, i );

			if( lrParen.accepted() ){

				children.add( lrParen );

				i += lrParen.numLeaves();

				ID id = new ID( all_tokens, i );

				if( id.accepted() ){

					children.add( id );

					i += id.numLeaves();

					RRParen rrParen = new RRParen( all_tokens, i );

					if( rrParen.accepted() ){

						children.add( rrParen );

						success = true;

					}

				}

			}

		}

		if( !success ){

			children.clear();

		}

		return success;

	}

	private boolean tryWriteParenExpressionParen( final Vector< Token > all_tokens, int i ){

		boolean success = false;

		Write write = new Write( all_tokens, i );

		if( write.accepted() ){

			children.add( write );

			i += write.numLeaves();

			LRParen lrParen = new LRParen( all_tokens, i );

			if( lrParen.accepted() ){

				children.add( lrParen );

				i += lrParen.numLeaves();

				Expression expression = new Expression( all_tokens, i );

				if( expression.accepted() ){

					children.add( expression );

					i += expression.numLeaves();

					RRParen rrParen = new RRParen( all_tokens, i );

					if( rrParen.accepted() ){

						children.add( rrParen );

						success = true;

					}

				}

			}

		}

		if( !success ){

			children.clear();

		}

		return success;

	}

	private boolean tryReturnExpression( final Vector< Token > all_tokens, int i ){

		boolean success = false;

		Return _return = new Return( all_tokens, i );

		if( _return.accepted() ){

			children.add( _return );

			i += _return.numLeaves();
				
			Expression expression = new Expression( all_tokens, i );

			if( expression.accepted() ){

				children.add( expression );

				success = true;

			}

		}

		if( !success ){

			children.clear();

		}

		return success;

	}

	@Override
	public void print(){

		System.out.println( "statment. Children:  " + children.size() );

		for( int i = 0; i < children.size(); i++){

			children.get( i ).print();

		}

	}


	Statement( final Vector< Token > all_tokens, final int i ){

		isTerminal = false;

		if( tryVariableAssignopExpression( all_tokens, i ) ){

			didAccept = true;

		}else if( tryProcedureStatement( all_tokens, i ) ){

			didAccept = true;

		}else if( tryCompoundStatement( all_tokens, i ) ){

			didAccept = true;

		}else if( tryIfExpressionThenStatementElseStatement( all_tokens, i ) ){

			didAccept = true;

		}else if( tryWhileExpressionDoStatement( all_tokens, i ) ){

			didAccept = true;

		}else if( tryReadParenIdParen( all_tokens, i ) ){

			didAccept = true;

		}else if( tryWriteParenExpressionParen( all_tokens, i ) ){

			didAccept = true;

		}else if( tryReturnExpression( all_tokens, i ) ){

			didAccept = true;

		}else{

			didAccept = false;

		}

	}

}


