
package Parser;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.Vector;
import Scanner.*;

public class Parser{

	public Vector< Token > all_tokens = new Vector< Token >();

	public Parser( InputStreamReader isr ){

		MyScanner sc = new MyScanner( isr );

		while( sc.hasNext() ){

			all_tokens.add( sc.next() );

		}

	}

	public boolean isProgram(){

		ProgramNT root = new ProgramNT( all_tokens, 0 );

		if( root.accepted() ){

			//System.out.println("tree:");

			//root.print();

			return true;

		}else{

			return false;

		}

	}


};
