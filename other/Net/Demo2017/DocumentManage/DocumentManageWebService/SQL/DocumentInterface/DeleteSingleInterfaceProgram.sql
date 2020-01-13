UPDATE  dbo.InterfaceProgram
SET     IsDelete = 1 ,UpdateTime = GETDATE()
WHERE   ProgramID = {0} 