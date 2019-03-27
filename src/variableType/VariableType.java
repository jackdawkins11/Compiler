
package variableType;

import scanner.*;

/*
 * This class represents a variable
 * type.
 */

public class VariableType{

	//////////////////
	//     Data     //
	//////////////////

	int beginIndex, endIndex;

	EnumStandardType standardType;

	boolean thisIsArray;

	/////////////////////////
	//     Constructor     //
	/////////////////////////

	public VariableType( int beginIndexTmp, int endIndexTmp, EnumStandardType standardTypeTmp ){

		thisIsArray = true;

		beginIndex = beginIndexTmp;

		endIndex = endIndexTmp;

		standardType = standardTypeTmp;

	}
	
	public VariableType( EnumStandardType standardTypeTmp ){

		thisIsArray = false;

		standardType = standardTypeTmp;

	}

	/////////////////////
	//     Getters     //
	/////////////////////

	public boolean isArray(){

		return thisIsArray;

	}

	public int getBeginIndex(){

		return beginIndex;

	}

	public int getEndIndex(){

		return endIndex;

	}
	
	public EnumStandardType getType(){

		return standardType;

	}

}

