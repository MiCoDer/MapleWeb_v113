

# TMSv113 JSF Rank WEB system

umm
很少人會在GitHub的ReadMe寫教學
不過基於良心還是寫一下好了

總之這就是基於TMSv113的WEB Rank系統
然後這是JSF所以必須用GlassFish或Apache Tomcat之類的WEB Server

而我選擇了GlassFish，可能因為自己也蠻玻璃的。


首先直接下載解壓縮GlassFish，然後照著做到 start-domain 指令
(記得下載 GlassFish - Full Platform 版本)
```
https://www.youtube.com/watch?v=WWCsvea8fGk
```
如果不小心刪除domain資料夾，用此新增
```
create-domain --adminport 4848 domain1
```

然後瀏覽 http://localhost:4848/ 預設GlassFish Admin Console Port
選擇 application -> deploy -> 上傳MapleWeb_v113/dist/MapleWeb.war -> ok -> launch


然後一個簡單的頁面就開好了。

------------------------------
後端連線資訊:
資料庫IP
資料庫PORT
資料庫名
資料庫使用者
資料庫密碼

都在 MapleWeb_v113/src/java/maple/DBOperation.java 進行修改
------------------------------

![alt text](https://i.imgur.com/551Rkab.jpg)
![alt text](https://i.imgur.com/6knEsQE.png)
