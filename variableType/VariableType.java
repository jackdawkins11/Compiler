
package variableType;

import scanner.*;

public class VariableType{

	int rows, cols;

	EnumToken standardType;

	boolean thisIsArray;

	public VariableType( int rowsTmp, int colsTmp, EnumToken standardTypeTmp ){

		thisIsArray = true;

		rows = rowsTmp;

		cols = colsTmp;

		standardType = standardTypeTmp;

	}
	
	public VariableType( EnumToken standardTypeTmp ){

		thisIsArray = false;

		standardType = standardTypeTmp;

	}

	public boolean isArray(){

		return thisIsArray;

	}

	public int getCols(){

		return cols;

	}

	public int getRows(){

		return rows;

	}

	public EnumToken getType(){

		return standardType;

	}

}

