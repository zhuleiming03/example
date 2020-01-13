USE [DocumentManage]
GO

/****** Object:  Table [dbo].[InterfaceProgram]    Script Date: 2018/5/16 15:48:15 ******/
SET ANSI_NULLS ON
GO

SET QUOTED_IDENTIFIER ON
GO

SET ANSI_PADDING ON
GO

CREATE TABLE [dbo].[InterfaceProgram](
	[IsDelete] [int] NULL,
	[CreateTime] [datetime] NULL,
	[UpdateTime] [datetime] NULL,
	[ProgramID] [int] IDENTITY(1,1) NOT NULL,
	[ProgramName] [nvarchar](20) NULL,
	[SvnUrl] [varchar](200) NULL,
	[OrderIndex] [int] NULL,
 CONSTRAINT [PK_InterfaceProgram] PRIMARY KEY CLUSTERED 
(
	[ProgramID] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]

GO

SET ANSI_PADDING OFF
GO

ALTER TABLE [dbo].[InterfaceProgram] ADD  CONSTRAINT [DF_InterfaceProgram_OrderIndex]  DEFAULT ((999)) FOR [OrderIndex]
GO


