
package syntaxTree;

/*
 * This class represents a
 * variable.
 */

public abstract class VariableNode extends SyntaxTreeNode {

	//////////////////
	//     Data     //
	//////////////////

	private int beginIndex,
		endIndex;

	EnumStandardType standardType;

	String name;

	//////////////////////////
	//     Constructors     //
	//////////////////////////

	VariableNode( int beginIndexTmp, int endIndexTmp, EnumStandardType standardTypeTmp, String nameTmp ){

		beginIndex = beginIndexTmp;

		endIndex = endIndexTmp;

		standardType = standardTypeTmp;

		name = nameTmp;

	}

	VariableNode( EnumStandardType standardTypeTmp, String nameTmp ){

		beginIndex = 0;

		endIndex = 1;

		standardType = standardTypeTmp;

		name = nameTmp;

	}

	////////////////////////////
	//     Public Methods     //
	////////////////////////////

	public String getName(){

		return name;

	}

	String indentedToString( int level ){

		String answer = indentation( level )
			+ "VariableNode."
			+ " Name: " + name
			+ " Standard Type: " + standardType.toString()
			+ " beginIndex: " + beginIndex
			+ " endIndex: " + endIndex;

		return answer;

	}

}


