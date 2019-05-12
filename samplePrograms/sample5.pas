program foo;
var arr : array[ 0 : 2 ] of real;
var x : real;

function addFromConsole( y : real ) : real ;
var z : real;
begin
read( z );
y := y + z;
return y 
end;

begin
read( x );
x := addFromConsole( x );
write( x );
arr[ 0 ] := 10;
arr[ 1 ] := 11;
x := arr[ 0 ] + arr[ 1 ]; 
write( x )
end.
