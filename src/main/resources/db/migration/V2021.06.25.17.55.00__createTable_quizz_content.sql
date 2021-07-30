/****** Object:  Table [dbo].[quizz_content]    Script Date: 6/25/2021 5:21:29 PM ******/
SET ANSI_NULLS ON
GO

SET QUOTED_IDENTIFIER ON
GO

if OBJECT_ID ('dbo.quizz_content') IS NULL

BEGIN

	CREATE TABLE [dbo].[quizz_content](
		[id] [int] IDENTITY(1,1) NOT NULL,
		[id_quizz] [int] NOT NULL,
		[id_question] [int] NOT NULL,
		[is_correct] [bit] NOT NULL,
	PRIMARY KEY CLUSTERED
	(
		[id] ASC
	)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
	) ON [PRIMARY]


	ALTER TABLE [dbo].[quizz_content]  WITH CHECK ADD FOREIGN KEY([id_quizz])
	REFERENCES [dbo].[quizz_header] ([id])


	ALTER TABLE [dbo].[quizz_content]  WITH CHECK ADD FOREIGN KEY([id_question])
	REFERENCES [dbo].[questions] ([id])

END

GO


