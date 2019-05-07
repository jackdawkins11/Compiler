program foo;
var x : real;

function addFromConsole( y : real ) : real;
var z : real;
begin
read( z );
y := y + z;
return y 
end;

begin
read( x );
x := addFromConsole( x );
write( x )
end.
