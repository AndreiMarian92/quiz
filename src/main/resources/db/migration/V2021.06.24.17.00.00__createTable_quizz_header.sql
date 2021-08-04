/****** Object:  Table [dbo].[quizz_header]    Script Date: 6/25/2021 12:12:02 AM ******/
SET ANSI_NULLS ON
GO

SET QUOTED_IDENTIFIER ON
GO

if OBJECT_ID ('dbo.quizz_header') IS NULL

BEGIN

	CREATE TABLE [dbo].[quizz_header](
		[id] [int] IDENTITY(1,1) NOT NULL,
		[created_by] [int] NOT NULL,
		[created_date] [datetime] NULL,
		[is_passed] [bit] NOT NULL,
		[total_questions] [int] NOT NULL,
		[correct_answers] [int] NOT NULL,
	PRIMARY KEY CLUSTERED
	(
		[id] ASC
	)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
	) ON [PRIMARY]

	ALTER TABLE [dbo].[quizz_header] ADD  DEFAULT (getdate()) FOR created_date]

	ALTER TABLE [dbo].[quizz_header]  WITH CHECK ADD  CONSTRAINT [fk_quizz_header_created_by] FOREIGN KEY([created_by])
	REFERENCES [dbo].[users] ([id])

	ALTER TABLE [dbo].[quizz_header] CHECK CONSTRAINT [fk_quizz_header_created_by]

END

GO


