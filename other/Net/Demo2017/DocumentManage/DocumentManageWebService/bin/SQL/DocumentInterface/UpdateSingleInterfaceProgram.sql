UPDATE  dbo.InterfaceProgram
SET     ProgramName = N'{1}' ,
		SvnUrl = '{3}' ,
		UpdateTime = GETDATE() ,
        OrderIndex = {2}
WHERE   ProgramID = {0}
