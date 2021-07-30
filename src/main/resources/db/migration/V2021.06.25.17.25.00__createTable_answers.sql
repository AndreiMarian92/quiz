/****** Object:  Table [dbo].[answers]    Script Date: 6/25/2021 5:25:32 PM ******/
SET ANSI_NULLS ON
GO

SET QUOTED_IDENTIFIER ON
GO

IF OBJECT_ID ('dbo.answers') IS NULL

BEGIN

	CREATE TABLE [dbo].[answers](
		[id] [int] IDENTITY(1,1) NOT NULL,
		[description] [varchar](max) NULL,
		[is_correct] [bit] NOT NULL,
		[question_id] [int] NULL,
		[created_at] [datetime] NULL,
	PRIMARY KEY CLUSTERED
	(
		[id] ASC
	)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
	) ON [PRIMARY] TEXTIMAGE_ON [PRIMARY]


	ALTER TABLE [dbo].[answers] ADD  DEFAULT ((0)) FOR [is_correct]


	ALTER TABLE [dbo].[answers] ADD  DEFAULT (getdate()) FOR [created_at]

END

GO


