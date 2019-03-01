
//import the recognizer
import parser.Recognizer;

/* import JUnit */

import org.junit.*;
import static org.junit.Assert.*;

public class TestRecognizer{

	/*
	 * This opens 6 pascal programs, 3 of them 
	 * correct and 3 of them with errorrs
	 * and tests the recognizer on them.
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

	}

}
