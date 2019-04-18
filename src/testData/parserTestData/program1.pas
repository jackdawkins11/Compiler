
program foo;

var x, y, z : integer;

function square( number : integer ) : integer;
var returnval : integer;
begin
	returnval := number * number;
	return (returnval)
end
;

begin

	read( x );

	y := square( x );

	write( y );
	
	read( y );

	z := square( y );

	write( z )

end
.


