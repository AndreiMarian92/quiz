/****** Object:  Table [dbo].[questions]    Script Date: 6/25/2021 5:23:19 PM ******/
SET ANSI_NULLS ON
GO

SET QUOTED_IDENTIFIER ON
GO

IF OBJECT_ID ('dbo.questions') IS NULL

BEGIN

	CREATE TABLE [dbo].[questions](
		[id] [int] IDENTITY(1,1) NOT NULL,
		[description] [varchar](max) NULL,
		[created_at] [datetime] NULL,
	PRIMARY KEY CLUSTERED
	(
		[id] ASC
	)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
	) ON [PRIMARY] TEXTIMAGE_ON [PRIMARY]


	ALTER TABLE [dbo].[questions] ADD  DEFAULT (getdate()) FOR [created_at]

END

GO


