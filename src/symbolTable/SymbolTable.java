
package symbolTable;

import java.util.HashMap;
import syntaxTree.*;

public class SymbolTable{

	HashMap< String, VariableNode > variables = new HashMap< String, VariableNode >();
	
	HashMap< String, SubProgramNode > subPrograms = new HashMap< String, SubProgramNode >();

	public void print(){

		for( String key : variables.keySet() ){

			System.out.println( "Variable: " + key );

		}
	
		for( String key : subPrograms.keySet() ){

			System.out.println( "SubProgram: " + key );

		}
	
	}

	public void addVariable( VariableNode variableNode ){

		variables.put( variableNode.getName(), variableNode );

	}

	public void addSubProgram( SubProgramNode subProgramNode ){

		subPrograms.put( subProgramNode.getName(), subProgramNode );

	}

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

	public VariableNode getVariableByName( String name ){

		return variables.get( name );

	}

	public SubProgramNode getSubProgramByName( String name ){

		return subPrograms.get( name );

	}

}



