USE [DocumentManage]
GO

/****** Object:  Table [dbo].[InterfaceItem]    Script Date: 2018/5/16 15:46:57 ******/
SET ANSI_NULLS ON
GO

SET QUOTED_IDENTIFIER ON
GO

SET ANSI_PADDING ON
GO

CREATE TABLE [dbo].[InterfaceItem](
	[IsDelete] [int] NULL,
	[CreateTime] [datetime] NULL,
	[UpdateTime] [datetime] NULL,
	[InterfaceID] [int] IDENTITY(1,1) NOT NULL,
	[ProgramID] [int] NULL,
	[Title] [nvarchar](20) NULL,
	[Code] [varchar](50) NULL,
	[Info] [nvarchar](200) NULL,
	[Path] [varchar](100) NULL,
	[Method] [varchar](10) NULL,
	[InputParameterJSON] [nvarchar](1000) NULL,
	[OutputParameterJSON] [nvarchar](1000) NULL,
	[OrderIndex] [int] NULL,
 CONSTRAINT [PK_InterfaceItem] PRIMARY KEY CLUSTERED 
(
	[InterfaceID] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]

GO

SET ANSI_PADDING OFF
GO

ALTER TABLE [dbo].[InterfaceItem] ADD  CONSTRAINT [DF_InterfaceItem_OrderIndex]  DEFAULT ((999)) FOR [OrderIndex]
GO


