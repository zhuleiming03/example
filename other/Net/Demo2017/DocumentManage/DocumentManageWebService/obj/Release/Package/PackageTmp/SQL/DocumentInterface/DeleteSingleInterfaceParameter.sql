UPDATE  dbo.InterfaceParameter
SET     IsDelete = 1 ,
        UpdateTime = GETDATE()
WHERE   ParameterID = {0}
