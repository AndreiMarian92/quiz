/****** Object:  Table [dbo].[quizz_content]    Script Date: 6/25/2021 5:21:29 PM ******/
SET ANSI_NULLS ON
GO

SET QUOTED_IDENTIFIER ON
GO



IF NOT EXISTS (SELECT 1 FROM sys.columns
                     WHERE Name = N'question_position'
                     AND Object_ID = Object_ID(N'dbo.quizz_content'))

BEGIN

    ALTER TABLE dbo.quizz_content
    ADD question_position INT NULL

END

GO


