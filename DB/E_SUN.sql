CREATE DATABASE E_SunBank
GO

USE E_SunBank
GO

-- 建立 Seating Chart 表
CREATE TABLE seatingChart (
    floorSeatSeq INT IDENTITY(1,1) PRIMARY KEY,
    floorNo INT NOT NULL,
    seatNo INT NOT NULL
);
GO

-- 建立 Employee 表
CREATE TABLE employee (
    empId INT IDENTITY(10000,1) PRIMARY KEY,
    name NVARCHAR(20) NOT NULL,
    email NVARCHAR(50) NOT NULL,
    floorSeatSeq INT NULL,
    FOREIGN KEY (floorSeatSeq) REFERENCES seatingChart(floorSeatSeq),
);
GO

--建立取Employee 表的預存程序
CREATE PROCEDURE showall
AS
BEGIN
SELECT * FROM employee;
END
GO

--建立取Seating Chart 表的預存程序
CREATE PROCEDURE showAllChart
AS
BEGIN
SELECT * FROM seatingChart;
END
GO

--建立更新座位的預存程序
CREATE PROCEDURE UpdateFloorSeatSeq
    @newFloorSeatSeq NVARCHAR(255),
    @empId INT
AS
BEGIN
    SET NOCOUNT ON;

    IF @newFloorSeatSeq = '0' -- 假设零是要用来表示移除的特定值
    BEGIN
        UPDATE employee
        SET floorSeatSeq = NULL
        WHERE empId = @empId;
    END
    ELSE
    BEGIN
        UPDATE employee
        SET floorSeatSeq = @newFloorSeatSeq
        WHERE empId = @empId;
    END
END;
GO

--Insert資料到Seating Chart 表
INSERT INTO seatingChart (floorNo, seatNo) VALUES 
(1, 1),
(1, 2),
(1, 3),
(1, 4),
(2, 1),
(2, 2),
(2, 3),
(2, 4),
(3, 1),
(3, 2),
(3, 3),
(3, 4),
(4, 1),
(4, 2),
(4, 3),
(4, 4);
GO

--Insert資料到Employee 表
INSERT INTO employee (name, email, floorSeatSeq) VALUES 
(N'趙一', N'a@gmail.com', NULL),
(N'錢二', N'b@gmail.com', 6),
(N'孫三', N'c@gmail.com', NULL),
(N'李四', N'd@gmail.com', NULL),
(N'王五', N'e@gmail.com', NULL),
(N'黃六', N'f@gmail.com', NULL),
(N'林七', N'g@gmail.com', NULL),
(N'劉八', N'h@gmail.com', 14),
(N'陳九', N'i@gmail.com', NULL),
(N'沈十', N'j@gmail.com', NULL),
(N'吳十一', N'k@gmail.com', NULL),
(N'鄭十二', N'l@gmail.com', NULL),
(N'楊十三', N'm@gmail.com', 9),
(N'孔十四', N'n@gmail.com', NULL),
(N'曹十五', N'o@gmail.com', NULL),
(N'金十六', N'p@gmail.com', NULL);
GO
