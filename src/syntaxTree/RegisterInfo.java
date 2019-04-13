
public class RegisterInfo{

	int cRegister, lastRegister;

	String name;
	
	public RegisterInfo( int firstRegisterTmp,
		       int lastRegisterTmp,
		       String nameTmp){
		
		cRegister = firstRegisterTmp;

		lastRegister = lastRegisterTmp;

		name = nameTmp;

	}

	String nextRegister(){

		if( lastRegister < nextRegister ){

			System.out.println("too many declarations..");

			System.exit( 1 );

		}

		String answer = name + String.valueOf( cRegister );

		cRegister++;

		return answer;

	}

}

