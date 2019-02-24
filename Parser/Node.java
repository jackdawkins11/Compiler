
package Parser;
import java.util.Vector;

public class Node{

	Vector<Node> children = new Vector<Node>();

	boolean didAccept, isTerminal;

	public boolean accepted(){

		return didAccept;

	}

	public int numLeaves(){

		assert( didAccept );

		int result = 0;

		if( isTerminal ){

			assert( children.size() == 0 );

			result = 1;

		}else{

			for( int i = 0; i < children.size(); i++){

				result += children.get( i ).numLeaves();

			}

		}

		return result;

	}

	public void print(){ ;;; }
}


