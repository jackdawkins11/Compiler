program foo;
var x : integer;

function addFromConsole( y : integer ) : integer;
var z : integer;
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
