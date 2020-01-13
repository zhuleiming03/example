SELECT InterfaceID,ProgramID,Title,Code,Info,Path,Method,InputParameterJSON,OutputParameterJSON,UpdateTime,OrderIndex 
FROM dbo.InterfaceItem 
WHERE IsDelete=0 AND InterfaceID={0}