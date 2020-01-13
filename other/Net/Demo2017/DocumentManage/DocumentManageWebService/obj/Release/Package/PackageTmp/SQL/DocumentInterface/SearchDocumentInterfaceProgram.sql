SELECT ProgramID,ProgramName,OrderIndex 
FROM dbo.InterfaceProgram
WHERE IsDelete=0 AND ProgramID={0}

SELECT InterfaceID,ProgramID,Title,Info,Path,Method,InputParameterJSON,OutputParameterJSON,UpdateTime,OrderIndex 
FROM dbo.InterfaceItem 
WHERE IsDelete=0 AND ProgramID={0}

SELECT ParameterID,ProgramID,InterfaceID,ParameterCode,ParameterName,ParameterType,DataType,CheckContent,Remark,OrderIndex
FROM dbo.InterfaceParameter
WHERE IsDelete=0 AND ProgramID={0}
