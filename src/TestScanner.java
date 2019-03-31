
import org.junit.*;
import static org.junit.Assert.*;
import java.util.Scanner;
import scanner.*;

public class TestScanner{

	@Test 
	public void testOne(){

		String code = 
			 	"program\n"  
			+	"jacksprogram\n"
			+	";\n" 
			+	"thisisanid\n"
			+	"so8isthis\n"
			+	"while\n"
			+	"do\n"
			+	"8.9E4\n"
			+	"002302.9E+4\n"
			+	"0043.3\n"
			+	"423442\n"
			+	"23423E8\n"
			+	"progrm\n"
			+	"jacksprogram\n"
			;

		String [] tokens = 
		{	"program",
			"jacksprogram",
			";",
			"thisisanid",
			"so8isthis",
			"while",
			"do",
			"8.9E4",
			"002302.9E+4",
			"0043.3",
			"423442",
			"23423E8",
			"progrm",
			"jacksprogram"
		};

		EnumToken [] types =
		{
			EnumToken.PROGRAM,
			EnumToken.ID,
			EnumToken.SEMICOLON,
			EnumToken.ID,
			EnumToken.ID,
			EnumToken.WHILE,
			EnumToken.DO,
			EnumToken.NUM,
			EnumToken.NUM,
			EnumToken.NUM,
			EnumToken.NUM,
			EnumToken.NUM,
			EnumToken.ID,
			EnumToken.ID
		};

		MyScanner scanner = new MyScanner( code, false );

		int i = 0;

		while( scanner.hasNext() ){

			Token nextToken = scanner.next();

			assertEquals( nextToken.getString(), tokens[ i ] );
			
			assertEquals( nextToken.getType(), types[ i ] );

			i++;
		}

	}
	
	@Test 
	public void testTwo(){

		String code =
				"{ this is coommentooppp}\n"
			+ 	"program foo;\n"
			+	"var fee, fi, fo, fum: integer;\n"
			+	"begin\n"
			+	"fee := 4;\n"
			+	"fi := 5;\n"
			+	"fo := 3 * fee + fi;\n"
			+	"if fo < 13\n"
			+	"then\n"
			+	"fo := 13\n"
			+	"else\n"
			+	"fo := 26\n"
			+	";\n"
			+	"write( fo );\n"
			+	"end\n"
			+	".\n"
			;

			String [] tokens =
			{
				"program",
				"foo",
				";",
				"var",
				"fee",
				",",
				"fi",
				",",
				"fo",
				",",
				"fum",
				":",
				"integer",
				";",
				"begin",
				"fee",
				":=",
				"4",
				";",
				"fi",
				":=",
				"5",
				";",
				"fo",
				":=",
				"3",
				"*",
				"fee",
				"+",
				"fi",
				";",
				"if",
				"fo",
				"<",
				"13",
				"then",
				"fo",
				":=",
				"13",
				"else",
				"fo",
				":=",
				"26",
				";",
				"write",
				"(",
				"fo",
				")",
				";",
				"end",
				"."
			};
			
			EnumToken [] types =
			{
				EnumToken.PROGRAM,
				EnumToken.ID,
				EnumToken.SEMICOLON,
				EnumToken.VAR,
				EnumToken.ID,
				EnumToken.COMMA,
				EnumToken.ID,
				EnumToken.COMMA,
				EnumToken.ID,
				EnumToken.COMMA,
				EnumToken.ID,
				EnumToken.COLON,
				EnumToken.INTEGER,
				EnumToken.SEMICOLON,
				EnumToken.BEGIN,
				EnumToken.ID,
				EnumToken.ASSIGNOP,
				EnumToken.NUM,
				EnumToken.SEMICOLON,
				EnumToken.ID,
				EnumToken.ASSIGNOP,
				EnumToken.NUM,
				EnumToken.SEMICOLON,
				EnumToken.ID,
				EnumToken.ASSIGNOP,
				EnumToken.NUM,
				EnumToken.MULOP,
				EnumToken.ID,
				EnumToken.ADDOP,
				EnumToken.ID,
				EnumToken.SEMICOLON,
				EnumToken.IF,
				EnumToken.ID,
				EnumToken.RELOP,
				EnumToken.NUM,
				EnumToken.THEN,
				EnumToken.ID,
				EnumToken.ASSIGNOP,
				EnumToken.NUM,
				EnumToken.ELSE,
				EnumToken.ID,
				EnumToken.ASSIGNOP,
				EnumToken.NUM,
				EnumToken.SEMICOLON,
				EnumToken.WRITE,
				EnumToken.LRPAREN,
				EnumToken.ID,
				EnumToken.RRPAREN,
				EnumToken.SEMICOLON,
				EnumToken.END,
				EnumToken.DOT
			};

		MyScanner scanner = new MyScanner( code, false );

		int i = 0;

		while( scanner.hasNext() ){

			Token nextToken = scanner.next();

			assertEquals( nextToken.getString(), tokens[ i ] );
		
			assertEquals( nextToken.getType(), types[ i ] );

			i++;
		}

	}
	
}
