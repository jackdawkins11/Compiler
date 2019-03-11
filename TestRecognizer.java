
import org.junit.*;
import static org.junit.Assert.*;
import parser.Recognizer;
import scanner.MyScanner;

public class TestRecognizer{
	
	@Test
	public void test1(){

		MyScanner cScanner = null;

		Recognizer cRecognizer = null;

		cScanner = new MyScanner( "testData/recognizerTestData/correct1.pas", true );

		cRecognizer = new Recognizer( cScanner );

		assertTrue( cRecognizer.programNT() );
	
		cScanner = new MyScanner( "testData/recognizerTestData/correct2.pas", true );

		cRecognizer = new Recognizer( cScanner );

		assertTrue( cRecognizer.programNT() );
	
		cScanner = new MyScanner( "testData/recognizerTestData/correct3.pas", true );

		cRecognizer = new Recognizer( cScanner );

		assertTrue( cRecognizer.programNT() );
	
		cScanner = new MyScanner( "testData/recognizerTestData/correct4.pas", true );

		cRecognizer = new Recognizer( cScanner );

		assertTrue( cRecognizer.programNT() );
	
		cScanner = new MyScanner( "testData/recognizerTestData/correct5.pas", true );

		cRecognizer = new Recognizer( cScanner );

		assertTrue( cRecognizer.programNT() );
	
		cScanner = new MyScanner( "testData/recognizerTestData/incorrect1.pas", true );

		cRecognizer = new Recognizer( cScanner );

		assertFalse( cRecognizer.programNT() );
	
		cScanner = new MyScanner( "testData/recognizerTestData/incorrect2.pas", true );

		cRecognizer = new Recognizer( cScanner );

		assertFalse( cRecognizer.programNT() );
	
		cScanner = new MyScanner( "testData/recognizerTestData/incorrect3.pas", true );

		cRecognizer = new Recognizer( cScanner );

		assertFalse( cRecognizer.programNT() );
	
		cScanner = new MyScanner( "testData/recognizerTestData/incorrect4.pas", true );

		cRecognizer = new Recognizer( cScanner );

		assertFalse( cRecognizer.programNT() );
	
		cScanner = new MyScanner( "testData/recognizerTestData/incorrect5.pas", true );

		cRecognizer = new Recognizer( cScanner );

		assertFalse( cRecognizer.programNT() );
	
		cScanner = new MyScanner( "testData/recognizerTestData/incorrect6.pas", true );

		cRecognizer = new Recognizer( cScanner );

		assertFalse( cRecognizer.programNT() );
	
		cScanner = new MyScanner( "testData/recognizerTestData/incorrect7.pas", true );

		cRecognizer = new Recognizer( cScanner );

		assertFalse( cRecognizer.programNT() );

	}

}
