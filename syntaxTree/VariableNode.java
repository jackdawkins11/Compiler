
package syntaxTree;

public class VariableNode extends ExpressionNode {

	String name;

	public VariableNode( String attr ){ name = attr; }

	public String getName(){ return name; }

	@Override
	public String toString(){ return name; }

	@Override
	public String indentedToString( int level ){

		String answer = indentation( level );

		answer += "Name: " + name + "\n";

		return answer;

	}

	@Override
	public boolean equals( Object o ){

		boolean answer = false;

		if( o instanceof VariableNode ){

			VariableNode other = (VariableNode) o;

			if( name.equals( other.name ) ){

				answer = true;

			}

		}

		return answer;

	}


}
