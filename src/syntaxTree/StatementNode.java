
package syntaxTree;

/*
 * The base class
 * for all
 * statement
 * nodes.
 */

public abstract class StatementNode extends SyntaxTreeNode {

	////////////////////////////////
	//     Abstract Functions     //
	////////////////////////////////

	public abstract String indentedToString( int level );

	public abstract String toMips();

}
