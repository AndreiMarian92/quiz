/****** Object:  Table [dbo].[quizz_header]    Script Date: 6/25/2021 12:12:02 AM ******/
SET ANSI_NULLS ON
GO

SET QUOTED_IDENTIFIER ON
GO


if EXISTS (SELECT 1 FROM sys.columns
                    WHERE Name = N'image_URL'
                    AND Object_ID = Object_ID(N'dbo.questions'))

BEGIN

	ALTER TABLE dbo.questions

	ALTER COLUMN image_URL VARCHAR (2082)

END

GO



