
program foo;

var fee, fi, fo, fum : integer;
	fee := 4;
	fi := 5;
	fo := 3 * fee + fi;
	if fo < 13
		fo := 13
	else
		fo := 26
;

write( fo )
end
.
       		       
