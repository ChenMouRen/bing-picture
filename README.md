# bing-picture
一个使用Kotlin编写的必应日图接口,采用SpringBoot+Kotlin+Redis+Mysql搭建,每天自动获取当天的必应日图<br>
请求路径：<br>
* /picture/all 获取所有图片数据<br>
* /picture/date?date=2018-08-19 获取指定日期的图片数据,最早到2019-05-13,前面的没有去搞,日期格式必须是这种格式,如果不正确则无法获取到数据<br>
* /picture 获取目前最新的数据,每天上午9点30分更新

demo地址:  http://39.108.228.210/picture
