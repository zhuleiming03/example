USE [DocumentManage]
GO

/****** Object:  Table [dbo].[InterfaceParameter]    Script Date: 2018/5/16 15:47:39 ******/
SET ANSI_NULLS ON
GO

SET QUOTED_IDENTIFIER ON
GO

SET ANSI_PADDING ON
GO

CREATE TABLE [dbo].[InterfaceParameter](
	[IsDelete] [int] NULL,
	[CreateTime] [datetime] NULL,
	[UpdateTime] [datetime] NULL,
	[ParameterID] [int] IDENTITY(1,1) NOT NULL,
	[ProgramID] [int] NOT NULL,
	[InterfaceID] [int] NULL,
	[ParameterType] [int] NULL,
	[ParameterCode] [varchar](50) NULL,
	[ParameterName] [nvarchar](50) NULL,
	[CheckContent] [nvarchar](50) NULL,
	[DataType] [varchar](20) NULL,
	[Remark] [nvarchar](200) NULL,
	[OrderIndex] [int] NULL,
 CONSTRAINT [PK_InterfaceParameter_1] PRIMARY KEY CLUSTERED 
(
	[ParameterID] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]

GO

SET ANSI_PADDING OFF
GO

ALTER TABLE [dbo].[InterfaceParameter] ADD  CONSTRAINT [DF_InterfaceParameter_OrderIndex]  DEFAULT ((999)) FOR [OrderIndex]
GO

EXEC sys.sp_addextendedproperty @name=N'MS_Description', @value=N'1-入参；2-出参' , @level0type=N'SCHEMA',@level0name=N'dbo', @level1type=N'TABLE',@level1name=N'InterfaceParameter', @level2type=N'COLUMN',@level2name=N'ParameterType'
GO


