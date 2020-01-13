UPDATE  dbo.InterfaceParameter
SET     ParameterType = {1} ,
        ParameterCode = '{2}' ,
        ParameterName = N'{3}' ,
        DataType = '{4}' ,
        CheckContent = N'{5}' ,
        Remark = N'{6}' ,
        OrderIndex = {7} ,
        UpdateTime = GETDATE()
WHERE   ParameterID = {0}
