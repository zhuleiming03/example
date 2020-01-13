UPDATE  dbo.InterfaceItem
SET     Title = N'{1}' ,
        Info = N'{2}' ,
        Path = '{3}' ,
        Method = '{4}' ,
        InputParameterJSON = N'{5}' ,
        OutputParameterJSON = N'{6}' ,
        UpdateTime = GETDATE() ,
        OrderIndex = {7} ,
		Code = '{8}'
WHERE   InterfaceID = {0}
