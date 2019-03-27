
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

	///////////////////////////////
	//     Abstract Function     //
	///////////////////////////////
	
	public abstract EnumStandardType getStandardType();

}
