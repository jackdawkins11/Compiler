
package variableType;

/*
 * Subtletie: Treating
 * all variables as arrays. A
 * variable that isn't an
 * array is stored as an
 * array with 
 * 	- beginIndex = 0, and
 * 	- endIndex = 1.
 */

public class VariableType{

	//////////////////
	//     Data     //
	//////////////////

	EnumStandardType standardType;

	int beginIndex;

	int endIndex;

	/////////////////////////
	//     Constructor     //
	/////////////////////////

	public VariableType( int beginIndexTmp,
			int endIndexTmp, 
			EnumStandardType standardTypeTmp ){

		beginIndex = beginIndexTmp;

		endIndex = endIndexTmp;

		standardType = standardTypeTmp;

	}

	//////////////////////////////
	//     Public Functions     //
	//////////////////////////////
	
	public EnumStandardType getStandardType(){

		return standardType;

	}

	public int getBeginIndex(){

		return beginIndex;

	}

	public int getEndIndex(){

		return endIndex;

	}

}
