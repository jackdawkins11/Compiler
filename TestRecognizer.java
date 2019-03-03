
//import the recognizer
import parser.Recognizer;

/* import JUnit */

import org.junit.*;
import static org.junit.Assert.*;

public class TestRecognizer{

	/*
	 * This first tests the recognizer on
	 * 6 pascal programs, 3 of which are correct
	 * and 3 which are not but are similar. Then it tests
	 * on files almost exactly like the correct
	 * pascal program it just recognized except
	 * with syntax errors that can only be caught
	 * by the symbolTable. For example
	 * using a variable before it has been declared.
	 * Then it tests on 4 more complex programs
	 * 2 of which are correct and the others
	 * similar but incorrect replicas.
	 */

	@Test
	public void test1(){

		Recognizer recognizer = new Recognizer("test_data/correct1.pas");

		assertTrue( recognizer.programNT() );

		recognizer = new Recognizer("test_data/incorrect1.pas");

		assertFalse( recognizer.programNT() );

		recognizer = new Recognizer("test_data/correct2.pas");

		assertTrue( recognizer.programNT() );

		recognizer = new Recognizer("test_data/incorrect2.pas");

		assertFalse( recognizer.programNT() );

		recognizer = new Recognizer("test_data/correct3.pas");

		assertTrue( recognizer.programNT() );

		recognizer = new Recognizer("test_data/incorrect3.pas");

		assertFalse( recognizer.programNT() );

		recognizer = new Recognizer("test_data/incorrect4.pas");

		assertFalse( recognizer.programNT() );

		recognizer = new Recognizer("test_data/incorrect5.pas");

		assertFalse( recognizer.programNT() );

		recognizer = new Recognizer("test_data/correct4.pas");

		assertTrue( recognizer.programNT() );

		recognizer = new Recognizer("test_data/correct5.pas");

		assertTrue( recognizer.programNT() );

		recognizer = new Recognizer("test_data/incorrect6.pas");

		assertFalse( recognizer.programNT() );

		recognizer = new Recognizer("test_data/incorrect7.pas");

		assertFalse( recognizer.programNT() );

	}

}
