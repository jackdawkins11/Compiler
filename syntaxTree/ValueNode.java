
package syntaxTree;

public class ValueNode extends ExpressionNode {

	String attribute;

	public ValueNode( String attr ){ attribute = attr; }

	public String getAttribute(){ return attribute; }

	@Override
	public String toString(){ return attribute; }

	@Override
	public String indentedToString( int level ){

		String answer = indentation( level );

		answer += "Value: " + attribute + "\n";

		return answer;

	}

	@Override
	public boolean equals( Object o ){

		boolean answer = false;

		if( o instanceof ValueNode ){

			ValueNode other = (ValueNode) o;

			if( attribute.equals( other.attribute ) ){

				answer = true;

			}

		}

		return answer;

	}

}
