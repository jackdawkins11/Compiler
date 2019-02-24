
package Parser;
import java.util.Vector;

import Scanner.*;

public class ProgramNT extends Node{
	
	private boolean tryProgramIdSemicolonDeclarationsSubprogramDeclarationsCompoundStatementDot( final Vector< Token > all_tokens, int i ){

		boolean success = false;

		Program program = new Program( all_tokens, i );

		if( program.accepted() ){

			children.add( program );

			i += program.numLeaves();

			ID id = new ID( all_tokens, i );

			if( id.accepted() ){

				i+= id.numLeaves();

				children.add( id );

				Semicolon semicolon = new Semicolon( all_tokens, i );

				if( semicolon.accepted() ){

					i += semicolon.numLeaves();

					children.add( semicolon );

					Declarations declarations = new Declarations( all_tokens, i );

					if( declarations.accepted() ){

						i += declarations.numLeaves();

						children.add( declarations );

						SubprogramDeclarations subprogramDeclarations = new SubprogramDeclarations( all_tokens, i );

						if( subprogramDeclarations.accepted() ){

							i += subprogramDeclarations.numLeaves();

							children.add( subprogramDeclarations );

							CompoundStatement compoundStatement = new CompoundStatement( all_tokens, i );

							if( compoundStatement.accepted() ){

								i += compoundStatement.numLeaves();

								children.add( compoundStatement );

								Dot dot = new Dot( all_tokens, i );

								if( dot.accepted() ){

									children.add( dot );

									success = true;

								}

							}

						}

					}

				}

			}

		}

		if( !success ){

			children.clear();

		}

		return success;
	
	}
	
	@Override
	public void print(){

		System.out.println( "programNT. Children:  " + children.size() );

		for( int i = 0; i < children.size(); i++){

			children.get( i ).print();

		}

	}

	ProgramNT( final Vector< Token > all_tokens, final int i ){

		isTerminal = false;

		if( tryProgramIdSemicolonDeclarationsSubprogramDeclarationsCompoundStatementDot( all_tokens, i ) ){

			didAccept = true;

		}else{

			didAccept = false;

		}

	}

}


