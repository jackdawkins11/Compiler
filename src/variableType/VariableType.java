
package variableType;

public class VariableType{

	EnumStandardType standardType;

	int beginIndex;

	int endIndex;

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
