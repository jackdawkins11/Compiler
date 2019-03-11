
package syntaxTree;

import scanner.*;

public class OperationNode extends ExpressionNode {

	private ExpressionNode left;

	private ExpressionNode right;

	private EnumToken operation;

	public OperationNode( EnumToken op ){ operation = op ; }

	public void setLeft( ExpressionNode leftTmp ){ left = leftTmp; }

	public void setRight( ExpressionNode rightTmp ){ right = rightTmp; }

	public void setOperation( EnumToken op ){ operation = op; }

	public ExpressionNode getLeft(){ return left; }

	public ExpressionNode getRight(){ return right; }

	public EnumToken getOperation(){ return operation; }

	public String toString(){ return operation.toString(); }

	public String indentedToString( int level ){

		String answer = this.indentation( level );

		answer += "Operation: " + operation.toString() + "\n";

		answer += left.indentedToString( level + 1 );

		answer += right.indentedToString( level + 1 );

		return answer;

	}

	public boolean equals( Object o ){

		boolean answer = false;

		if( o instanceof OperationNode ){

			OperationNode other = (OperationNode ) o;

			if( operation == other.operation 
					&& left.equals( other.left )
					&& right.equals( other.right )
					){

				answer = true;

			}
		}

		return answer;

	}

}


