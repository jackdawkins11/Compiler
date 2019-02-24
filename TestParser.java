
import Parser.Parser;

import java.io.FileInputStream;
import java.io.InputStreamReader;

import org.junit.*;
import static org.junit.Assert.*;

public class TestParser{

	@Test
	public void testParser(){
		
		String correctFileName = "test_data/correct.pas",
		       incorrectFileName = "test_data/incorrect.pas";
		
		/* open files */

		FileInputStream fis1 = null,
				fis2 = null;

		try{

			fis1 = new FileInputStream( correctFileName );

		}catch (Exception e ){ e.printStackTrace(); }

		InputStreamReader isr1 = new InputStreamReader( fis1 );

		try{

			fis2 = new FileInputStream( incorrectFileName );

		}catch (Exception e ){ e.printStackTrace(); }

		InputStreamReader isr2 = new InputStreamReader( fis2 );

		Parser parser1 = new Parser( isr1 ),
		       parser2 = new Parser( isr2 );

		assertTrue( parser1.isProgram() );

		assertTrue( !parser2.isProgram() );

	}

}
