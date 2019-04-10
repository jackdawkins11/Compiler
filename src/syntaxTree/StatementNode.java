
package syntaxTree;

/*
 * The base class
 * for all
 * statement
 * nodes.
 */

public abstract class StatementNode extends SyntaxTreeNode {

	public abstract String toMips( String indent );

}
