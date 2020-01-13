SELECT ProgramID,ProgramName,OrderIndex 
FROM dbo.InterfaceProgram
WHERE IsDelete=0 AND ProgramID={0}