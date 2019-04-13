
package syntaxTree;

import variableType.EnumStandardType;

/*
 * The base class
 * class for
 * any node
 * representing
 * an expression.
 */

public abstract class ExpressionNode extends SyntaxTreeNode{

	////////////////////////////////
	//     Abstract Functions     //
	////////////////////////////////
	
	public abstract EnumStandardType getStandardType();

	public abstract String indentedToString( int level );
	
	public abstract String toMips();
}

