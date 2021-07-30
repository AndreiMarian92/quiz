/****** Object:  Table [dbo].[quizz_header]    Script Date: 6/25/2021 12:12:02 AM ******/
SET ANSI_NULLS ON
GO

SET QUOTED_IDENTIFIER ON
GO


if EXISTS (SELECT 1 FROM sys.columns
                    WHERE Name = N'correct_answers'
                    AND Object_ID = Object_ID(N'dbo.quizz_header'))

BEGIN

	ALTER TABLE dbo.quizz_header
	ALTER COLUMN correct_answers int NULL

END

GO


