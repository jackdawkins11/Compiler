
package Parser;
import java.util.Vector;

import Scanner.*;

public class Factor extends Node{

	private boolean tryIDParenExpressionParen( final Vector< Token > all_tokens, int i ){

		boolean success = false;

		ID id = new ID( all_tokens, i );

		if( id.accepted() ){

			i += id.numLeaves();

			children.add( id );

			LSParen leftSquareParen = new LSParen( all_tokens, i );

			if( leftSquareParen.accepted() ){

				i += leftSquareParen.numLeaves();

				children.add( leftSquareParen );

				Expression expression = new Expression( all_tokens, i );

				if( expression.accepted() ){

					i += expression.numLeaves();

					children.add( expression );

					RSParen rightSquareParen = new RSParen( all_tokens, i );

					if( rightSquareParen.accepted() ){

						children.add( rightSquareParen );

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
	
	private boolean tryIDParenExpressionListParen( final Vector< Token > all_tokens, int i ){

		boolean success = false;

		ID id = new ID( all_tokens, i );

		if( id.accepted() ){

			i += id.numLeaves();

			children.add( id );

			LRParen leftRoundParen = new LRParen( all_tokens, i );

			if( leftRoundParen.accepted() ){

				i += leftRoundParen.numLeaves();

				children.add( leftRoundParen );

				ExpressionList expressionList = new ExpressionList( all_tokens, i );

				if( expressionList.accepted() ){

					i += expressionList.numLeaves();

					children.add( expressionList );

					RRParen rightRoundParen = new RRParen( all_tokens, i );

					if( rightRoundParen.accepted() ){

						children.add( rightRoundParen );

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

	private boolean tryID( final Vector< Token > all_tokens, int i ){

		boolean success = false;

		ID id = new ID( all_tokens, i );

		if( id.accepted() ){

			children.add( id );

			success = true;

		}

		if( !success ){

			children.clear();

		}

		return success;
	
	}
	
	private boolean tryNum( final Vector< Token > all_tokens, int i ){

		boolean success = false;

		Num num = new Num( all_tokens, i );

		if( num.accepted() ){

			children.add( num );

			success = true;

		}
		
		if( !success ){

			children.clear();

		}

		return success;

	}

	private boolean tryParenExpressionParen( final Vector< Token > all_tokens, int i ){

		boolean success = false;

		LRParen leftRoundParen = new LRParen( all_tokens, i );

		if( leftRoundParen.accepted() ){

			i += leftRoundParen.numLeaves();

			children.add( leftRoundParen );

			Expression expression = new Expression( all_tokens, i );

			if( expression.accepted() ){

				i += expression.numLeaves();

				children.add( expression );

				RRParen rightRoundParen = new RRParen( all_tokens, i );

				if( rightRoundParen.accepted() ){

					children.add( rightRoundParen );

					success = true;

				}

			}

		}

		if( !success ){

			children.clear();

		}

		return success;

	}

	private boolean tryNotFactor( final Vector< Token > all_tokens, int i ){

		boolean success = false;

		Not not = new Not( all_tokens, i );

		if( not.accepted() ){

			i += not.numLeaves();

			children.add( not );

			Factor factor = new Factor( all_tokens, i );

			if( factor.accepted() ){

				children.add( factor );

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

		System.out.println( "Factor. Children:  " + children.size() );

		for( int i = 0; i < children.size(); i++){

			children.get( i ).print();

		}

	}


	public Factor( final Vector< Token > all_tokens, final int i ){

		isTerminal = false;

		if( tryIDParenExpressionParen( all_tokens, i ) ){

			didAccept = true;

		}else if( tryIDParenExpressionListParen( all_tokens, i ) ){

			didAccept = true;

		}else if( tryID( all_tokens, i ) ){

			didAccept = true;

		}else if( tryNum( all_tokens, i ) ){

			didAccept = true;

		}else if( tryParenExpressionParen( all_tokens, i ) ){

			didAccept = true;

		}else if( tryNotFactor( all_tokens, i ) ){

			didAccept = true;

		}else{

			didAccept = false;

		}
	
	}

}


