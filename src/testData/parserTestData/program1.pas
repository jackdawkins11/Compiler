
program foo;

var fee, fi, fo, fum : integer;
begin
	fee := 4;
	fi := 5;
	fo := 3 * fee + fi;
	if fo < 13
	then
		fo := 13
	else
		fo := 26
;

write( fo )
end
.

ProgramNode. Name: foo
|-- DeclarationsNode.
|-- --- StandardVariableNode. Name: fee
|-- --- StandardVariableNode. Name: fi
|-- --- StandardVariableNode. Name: fo
|-- --- StandardVariableNode. Name: fum
|-- SubProgramDeclarationsNode.
|-- CompoundStatementNode.
|-- --- VariableAssignmentStatementNode.
|-- --- --- StandardVariableNode. Name: fee
|-- --- --- NumValueExpressionNode. Num: 4
|-- --- VariableAssignmentStatementNode.
|-- --- --- StandardVariableNode. Name: fi
|-- --- --- NumValueExpressionNode. Num: 5
|-- --- VariableAssignmentStatementNode.
|-- --- --- StandardVariableNode. Name: fo
|-- --- --- OperationExpressionNode. Operation: +
|-- --- --- --- OperationExpressionNode. Operation: *
|-- --- --- --- --- NumValueExpressionNode. Num: 3
|-- --- --- --- --- VariableValueExpressionNode.
|-- --- --- --- --- --- StandardVariableNode. Name: fee
|-- --- --- --- VariableValueExpressionNode.
|-- --- --- --- --- StandardVariableExpressionNode. Name: fi
|-- --- IfThenStatementNode.
|-- --- --- OperationExpressionNode. Operation: <
|-- --- --- --- VariableValueExpressionNode.
|-- --- --- --- --- StandardVariableNode. Name: fo
|-- --- --- --- NumValueExpressionNode. Num: 13
|-- --- --- VariableAssignmentStatementNode.
|-- --- --- --- VariableValueExpressionNode.
|-- --- --- --- --- StandardVariableNode. Name: fo
|-- --- --- --- NumValueExpressionNode. Num: 13
|-- --- --- VariableAssignmentStatementNode.
|-- --- --- --- VariableValueExpressionNode.
|-- --- --- --- --- StandardVariableNode. Name: fo
|-- --- --- --- NumValueExpressionNode. Num: 26
|-- --- WriteStatementNode.
|-- --- --- VariableValueExpressionNode.
|-- --- --- --- StandardVariableNode. Name: fo

