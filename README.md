# E_sun_test

## 專案架構
後端採用 SpringBoot框架開發 於Maven資料夾內  
前端採用 Vue.js開發 於Vue資料夾內  
資料庫使用MSSQL開發 DDL與DML於DB資料夾E_sun.sql  

## 專案開啟步驟
E_sun為Vue專案，請先開啟PowerShell界面先行安裝依賴  
  
如使用node.js 請輸入 npm install  
請輸入 npm run dev開啟專案  

後端則採用SpringBoot做開發  
匯入後請先更改資料庫使用人員帳號密碼  
e_sun_test\src\main\resources\application.properties
第4行請輸入使用者帳號
地5行請輸入使用者密碼
開啟程式入口點  
e_sun_test\src\main\java\com\e_sun_test\ESunTestApplication.java  
  
資料庫文件採用MSSQL  
開啟E_SUN.sql檔案後執行  

完成以上步驟請訪問
<a>http://localhost:5173<a/>

