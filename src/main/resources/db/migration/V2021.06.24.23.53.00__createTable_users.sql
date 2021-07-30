/****** Object:  Table [dbo].[users]    Script Date: 6/24/2021 11:54:47 PM ******/
SET ANSI_NULLS ON
GO

SET QUOTED_IDENTIFIER ON
GO

if OBJECT_ID ('dbo.users') IS NULL

BEGIN

	CREATE TABLE [dbo].[users](
		[id] [int] IDENTITY(1,1) NOT NULL,
		[first_name] [varchar](100) NOT NULL,
		[last_name] [varchar](100) NOT NULL,
		[email_address] [varchar](320) NOT NULL,
		[password] [varchar](100) NOT NULL,
		[is_active] [bit] NOT NULL,
		[created_date] [datetime] NULL,
	PRIMARY KEY CLUSTERED
	(
		[id] ASC
	)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
	) ON [PRIMARY]

	ALTER TABLE [dbo].[users] ADD  DEFAULT ((0)) FOR [is_active]

	ALTER TABLE [dbo].[users] ADD  DEFAULT (getdate()) FOR [created_date]

END

GO
