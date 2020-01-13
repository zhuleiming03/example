SELECT ParameterID,ProgramID,InterfaceID,ParameterCode,ParameterName,ParameterType,DataType,CheckContent,Remark,OrderIndex
FROM dbo.InterfaceParameter
WHERE IsDelete=0 AND ParameterID={0}
