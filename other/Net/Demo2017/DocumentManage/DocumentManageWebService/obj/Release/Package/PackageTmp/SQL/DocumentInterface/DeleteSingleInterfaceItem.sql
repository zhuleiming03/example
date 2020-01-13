UPDATE  dbo.InterfaceItem
SET     IsDelete=1,UpdateTime = GETDATE()
WHERE   InterfaceID = {0}