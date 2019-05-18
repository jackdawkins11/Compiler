
package symbolTable;

import java.util.HashMap;
import syntaxTree.*;

/*
 * This class provides a HashMap
 * from ID strings to the nodes
 * created from previously parsed
 * ID names. It is used to make sure
 * variables/functions are declared
 * before they are used.
 */

public class SymbolTable{

	/*
	 * A ID to VariableNode HashMap.
	 */

	HashMap< String, VariableNode > variables = new HashMap< String, VariableNode >();

	/*
	 * A ID to SubProgramNode HashMap.
	 */

	HashMap< String, SubProgramNode > subPrograms = new HashMap< String, SubProgramNode >();

	/*
	 * Print all of the IDs in the
	 * HashMaps.
	 */

	public void print(){

		for( String key : variables.keySet() ){

			System.out.println( "Variable: " + key );

		}
	
		for( String key : subPrograms.keySet() ){

			System.out.println( "SubProgram: " + key );

		}
	
	}

	/*
	 * Add elements to the HashMap.
	 */

	public void addVariable( VariableNode variableNode ){

		variables.put( variableNode.getName(), variableNode );

	}

	public void addSubProgram( SubProgramNode subProgramNode ){

		subPrograms.put( subProgramNode.getName(), subProgramNode );

	}

	/*
	 * Check if IDs have been parsed
	 * already.
	 */

	public boolean isVariableName( String name ){

		if( variables.containsKey( name ) ){

			return true;

		}else{

			return false;

		}

	}

	public boolean isSubProgramName( String name ){

		if( subPrograms.containsKey( name ) ){

			return true;

		}else{

			return false;

		}

	}

	/*
	 * Get the node associated with an ID.
	 */

	public VariableNode getVariableByName( String name ){

		return variables.get( name );

	}

	public SubProgramNode getSubProgramByName( String name ){

		return subPrograms.get( name );

	}

}



