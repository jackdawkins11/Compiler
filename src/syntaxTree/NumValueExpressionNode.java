
package syntaxTree;

import variableType.EnumStandardType;

/*
 * A number.
 */

public class NumValueExpressionNode extends ExpressionNode {

	//////////////////
	//     Data     //
	//////////////////

	EnumStandardType standardType;

	String numString;

	/////////////////////////
	//     Constructor     //
	/////////////////////////

	public NumValueExpressionNode( String numStringTmp ){

		numString = numStringTmp;

		standardType = EnumStandardType.INTEGER;

		for( int i = 0 ; i < numString.length(); i++){

			if( numString.charAt( i ) == '.' ){

				standardType = EnumStandardType.REAL;

			}

		}

	}

	//////////////////////////////
	//     Public Functions     //
	//////////////////////////////

	@Override
	public EnumStandardType getStandardType(){

		return standardType;

	}

	@Override
	public String indentedToString( int level ){

		String answer = indentation( level )
			+ "NumValueExpressionNode."
			+ " Num: " + numString
			+ " Standard Type: " + getStandardType().toString()
			+ "\n";

		return answer;

	}

	@Override
	public String toMips( String indent ){

		//string to be used in the mips code
		String finalNumString = null;

		//string without exponent part
		String noExpString = numString;

		int powTen = 1;

		//check for exponent part
		for( int i = 0; i < numString.length(); i++){

			if( numString.charAt( i ) == 'E' ){

				//set noExpString

				noExpString = numString.substring( 0, i );

				//where the power of ten integer begins
				
				int exponentDigitsBegin = i + 1;

				//see if it is positive or negative

				boolean isPositive = true;

				if( numString.charAt( exponentDigitsBegin ) == '+' ){

					isPositive = true;

					exponentDigitsBegin ++;

				}else if( numString.charAt( exponentDigitsBegin ) == '-' ){

					isPositive = false;

					exponentDigitsBegin ++;

				}

				//set powTen

				try{

					powTen = Integer.parseInt( numString.substring( exponentDigitsBegin ) );
				
				}catch( Exception e ){ System.exit( 1 ); }

				if( !isPositive ){

					powTen *= -1;

				}

			}

		}

		//set finalNumString

		if( standardType == EnumStandardType.REAL ){

			float number = Float.parseFloat( noExpString );

			double finalnumber = Math.pow( number, powTen );

			finalNumString = String.valueOf( finalnumber );

		}else{

			int number = Integer.parseInt( noExpString );

			int finalnumber = (int) Math.pow( number, powTen );

			finalNumString = String.valueOf( finalnumber );

		}

		//the mips code

		String answer = null;

		if( standardType == EnumStandardType.REAL ){

			answer =  indent + "addi $sp, $sp, -4 #make room on stack\n"
				+ indent + "li.s $f12, " + finalNumString + " #put number into $f12\n"
				+ indent + "s.s $f12, 0($sp) #put $f12 on stack\n";

		}else{

			answer =  indent + "addi $sp, $sp, -4 #make room on stack\n"
				+ indent + "li $t0, " + finalNumString + " #put number into $t0\n"
				+ indent + "sw $t0, 0($sp) #put $t0 on stack\n";

		}

		return answer;

	}

}


