# 排队叫号后台软件

# `备注：`
    1. 指令名称必须大写；
    2. 指令与报文以"#"隔开；
    3. 报文必须做加密；
    4. 报文内容尽可能抽象成对象；


## 定义udp推送指令


1. 叫号信息推送

    指令名：ALL_OUT#
    
    报文内容：
    ```json
    {
        "category":"1",
        "ipAndPort":"192.168.10.145:8899",
        "datagram":{
            "transact_biz_id":22,
            "id_card":"234234324198822222222",
            "use_name":"chris",
            "use_sex":"0",
            "use_photo":"6NefU0XSWTZIyRH7zrAK201805121621.png",
            "use_birty":"1900年12月31日",
            "use_addr":"上海",
            "screen_num":"81",
            "biz_name":"网上、跨域立案",
            "queue_num":"E018",
            "use_current_photo":"kmMqPqi4uu08OMVKLxM3201805121622.png"
        }
    }
    ```
    
2. 条屏业务推送

    指令名：STRIPED_SCREEN_BIZ#
    
    ```json
    {
        "category":"1/0",
        "ipAndPort":"192.168.10.145:8899",
        "datagram":{
            "transact_biz_id":22,
            "id_card":"10005241988088888888",
            "use_name":"chris",
            "use_sex":"0",
            "use_photo":"6NefU0XSWTZIyRH7zrAK201805121621.png",
            "use_birty":"1008年08月24日",
            "use_addr":"上海上海",
            "screen_num":"81",
            "biz_name":"网上、跨域立案",
            "queue_num":"E018",
            "use_current_photo":"kmMqPqi4uu08OMVKLxM3201805121622.png"
        }
    }
    ```
    
3. 条屏基础业务推送

    指令名称：STRIPED_SCREEN_BASE#
    
   1. screen_mark （取值0-单屏；1-双屏） 显示方式
   2. screen_type (取值0-左；1-右；2-中)
    
    ```json
    {
        "ipAndPort":"192.168.10.142:8899",
        "datagram":{
            "screen_id":1,
            "base_biz_id":13,
            "base_biz":"网上、跨域立案",
            "screen_mark":"0",
            "screen_type":"2",
            "screen_num":"A009",
            "screen_ip":"192.168.10.183"
        }
    }
    
    ```
    
    
    
4. 业务屏基础数据推送

    指令名称：BIZ_SCREE#
    ```json
    {
        "ipAndPort":"192.168.10.142:8899",
        "datagram":{
            "biz_screen_id":1,
            "base_biz_id":0,
            "job_user_id":0,
            "biz_screen_mark":"1",
            "biz_screen_ip":"192.168.10.128",
            "jobUser":{
                "job_user_id":1,
                "job_user_name":"chris",
                "job_user_sex":"0",
                "job_user_num":"NK00089898",
                "job_user_photo":"http://192.168.10.142:8899/image/abc.png",
                "hidden":0
            },
            "baseBiz":{
                "base_biz_id":13,
                "base_biz_name":"网上、跨域立案",
                "base_biz_num":"A002",
                "hidden":0
            }
        }
    }
    ```
   
    
5. 背景图指令

    指令名称：bg_command#
     ```json
    {
        "ipAndPort":"192.168.10.142:8899",
        "datagram":{
            "type":1,
            "bg_fileName":"abc.png"
        }
    }
      ```
    

#   排队叫号接口文档
> ## 1.业务基础操作
>> 1. 1 添加业务

>> 1. 2 修改业务

>> 1. 3 删除业务

>> 1. 4 查询业务

> ## 2.人员基础操作
>> 2. 1 添加人员

>> 2. 2 修改人员

>> 2. 3 删除人员

>> 2. 4 查询人员

> ## 3.业务设置推送
&ensp;&ensp;*`//单个业务屏设置的推送` 请求方式：`POST`* [http://192.168.10.111:8899/queue/baseConf/settingsBizScreen/bizScreenId](http://127.0.0.1:8899/queue/baseConf/settingsBizScreen/bizScreenId "超链接")

&ensp;&ensp;参数：

|参数名|意义|数据类型|备注|
|:---:|:---:|:---:|:---:|
|bizScreenId|业务屏主键|<font color=red>int</font>|\*必传参数\* 例如：http://192.168.10.111:8899/queue/baseConf/settingsBizScreen/1|

&ensp;&ensp;返回：`字符串true 或 false;true为推送成功，false为推送失败`

|字段名|意义|数据类型|备注|
|:---:|:---:|:---:|:---:|
|BIZ_SCREEN|指令类型(业务屏基础数据推送)|<font color=red>String</font>|通过#先进行截取|
|command|指令类型|<font color=red>String</font>||
|ipAndPort|服务器的ip和端口|<font color=red>String</font>|通过此数据可调用评价接口|
|datagram|主要报文数据|<font color=red>对象</font>|包含所需显示的所有数据|
|biz_screen_id|业务屏主键|<font color=red>int</font>|
|base_biz_id|业务屏所对应的业务主键|<font color=red>int</font>||
|job_user_id|业务屏所对应的人员主键|<font color=red>int</font>||
|biz_screen_mark|业务屏所显示的类型<br/>0 代表办理业务人员<br/>1 代表工作人员|<font color=red>String</font>||
|biz_screen_ip|业务屏ip|<font color=red>String</font>|此业务所绑定的屏终端ip|
|jobUser|显示的人员信息|<font color=red>对象</font>|具体字段可看上方人员基础操作|
|baseBiz|显示业务信息|<font color=red>对象</font>|具体字段可看上方业务基础操作|

&ensp;&ensp;通过UDP推送的JSON字符串 如下：

    BIZ_SCREEN# {
    	"command": "BIZ_SCREEN#",
    	"ipAndPort": "192.168.10.111:8899",
    	"datagram": {
    		"biz_screen_id": 1,
    		"base_biz_id": 1,
    		"job_user_id": 1,
    		"biz_screen_mark": "1",
    		"biz_screen_ip": "192.168.10.119",
    		"jobUser": {
    			"job_user_id": 1,
    			"job_user_name": "ygx",
    			"job_user_sex": "0",
    			"job_user_num": "A0001",
    			"job_user_photo": "http://192.168.10.111:8899/image/YeWuRenYuan-1525596535083.jpg"
    		},
    		"baseBiz": {
    			"base_biz_id": 1,
    			"base_biz_name": "测试业务",
    			"base_biz_num": "A007"
    		}
    	}
    }

> ## 4.人员评价接收
&ensp;&ensp;*`//接收评价接口` 请求方式：`POST`* [http://192.168.10.111:8899/queue/evaluate/receive/transactId/evaluateVal](http://192.168.10.111:8899/queue/evaluate/receive/transactId/evaluateVal "超链接")

&ensp;&ensp;参数：

|参数名|意义|数据类型|备注|
|:---:|:---:|:---:|:---:|
|transactId|业务办理记录主键|<font color=red>int</font>|\*必传参数\*|
|evaluateVal|业务办理评价|<font color=red>String</font>|1 好 2 中 3 差 默认好|

&ensp;&ensp;返回：`{
                    "restCode": 0,
                    "message": "true",
                    "restData": null}` 
                
&ensp;&ensp;通过`message判断（true为成功，false为失败）`是否评价成功

> ## 5.条屏基础操作
>> 5. 1 添加条屏

>> 5. 2 修改条屏

>> 5. 3 删除条屏

>> 5. 4 查询条屏
> ## 6.条屏设置推送
&ensp;&ensp;*`//条屏设置的推送` 请求方式：`POST`* [http://192.168.10.111:8899/queue/baseConf/settingsStripedScreen/stripedId](http://192.168.10.111:8899/queue/baseConf/settingsStripedScreen/stripedId "超链接")

&ensp;&ensp;参数：

|参数名|意义|数据类型|备注|
|:---:|:---:|:---:|:---:|
|stripedId|条屏主键|<font color=red>int</font>|\*必传参数\* 例如：http://192.168.10.111:8899/queue/baseConf/settingsStripedScreen/1|

&ensp;&ensp;返回：`字符串true 或 false;true为推送成功，false为推送失败`

|字段名|意义|数据类型|备注|
|:---:|:---:|:---:|:---:|
|STRIPED_SCREEN_BASE|指令类型(条屏业务推送)|<font color=red>String</font>|通过#先进行截取|
|command|指令类型|<font color=red>String</font>|
|datagram|主要报文数据|<font color=red>对象</font>|包含所需显示的所有数据|
|screen_id|条屏主键|<font color=red>int</font>|
|base_biz_id|条屏所对应的业务主键|<font color=red>int</font>|
|screen_mark|条屏所显示的类型<br/>0 代表全屏显示<br/>1 代表分屏显示|<font color=red>String</font>|
|screen_type|条屏显示的效果|<font color=red>String</font>|0 代表屏幕左侧显示 1-右侧 2-中间|
|screen_num|条屏编号|<font color=red>String</font>|用于标识条屏|
|screen_ip|条屏ip|<font color=red>String</font>|

&ensp;&ensp;通过UDP推送的JSON字符串 如下：

    STRIPED_SCREEN_BASE#{
                             "command": "STRIPED_SCREEN_BASE#", 
                             "datagram": {
                                 "screen_id": 1, 
                                 "base_biz_id": 1, 
                                 "base_biz": "测试业务", 
                                 "screen_mark": "0", 
                                 "screen_type": "2", 
                                 "screen_num": "1111", 
                                 "screen_ip": "192.168.10.10"
                             }
                        }
                        
> ## 7.办理业务人员信息推送
&ensp;&ensp;*`//办理业务人员信息的推送 由叫号程序调用` 请求方式：`POST`* [http://192.168.10.111:8899/queue/court/push](http://192.168.10.111:8899/queue/court/push"超链接")

&ensp;&ensp;参数：

    {
        "person": {
            "userName": "张山", 
            "headPic": "http://ip:port/xxxxxx/xxxxx/f25i2552354kjijhadf.png", 
            "idCardNum": "0213154516324542121", 
            "idCardHeadPic": "http://ip:port/xxxxxx/xxxxx/fe5i254kjijh5548wsefe.png", 
            "address": "上海市嘉定区", 
            "birthday": "2018年3月10日", 
            "gender": "男"
        }, 
        "windowNum": "4", 
        "windowBiz": "民事立案", 
        "currentQueueNum": "1001"
    }

|参数名|意义|数据类型|备注|
|:---:|:---:|:---:|:---:|
|person|办理业务的人员信息|<font color=red>对象</font>|\*必传参数\*|
|userName|办理人员姓名|<font color=red>String</font>|
|headPic|摄像头获取到的头像 base64|<font color=red>String</font>|
|idCardNum|证件号|<font color=red>String</font>|
|idCardHeadPic|证件中的头像 base64|<font color=red>String</font>|
|address|住址|<font color=red>String</font>|
|birthday|出生日期|<font color=red>String</font>|
|gender|性别|<font color=red>String</font>|
|windowNum|办理业务的窗口编号|<font color=red>String</font>|
|windowBiz|办理的业务名称|<font color=red>String</font>|
|currentQueueNum|排队号|<font color=red>String</font>|

&ensp;&ensp;返回：`{
                                   "restCode": 0,
                                   "message": "true",
                                   "restData": null}` 
                               
&ensp;&ensp;通过`message判断（true为成功，false为失败）`是否评价成功

|字段名|意义|数据类型|备注|
|:---:|:---:|:---:|:---:|
|CALL_OUT|指令类型(叫号信息推送)|<font color=red>String</font>|通过#先进行截取|
|command|指令类型|<font color=red>String</font>|
|datagram|主要报文数据|<font color=red>对象</font>|包含所需显示的所有数据|
|transact_biz_id|业务办理记录主键|<font color=red>int</font>|在发送评价时需要使用|
|id_card|证件号|<font color=red>String</font>|
|use_name|办理人员姓名|<font color=red>String</font>|
|use_sex|性别|<font color=red>String</font>|0 代表男 1女|
|use_photo|证件中的头像|<font color=red>String</font>|
|use_birty|出生日期|<font color=red>String</font>|
|use_addr|住址|<font color=red>String</font>|
|screen_num|办理业务的窗口编号|<font color=red>String</font>|
|biz_name|办理的业务名称|<font color=red>String</font>|
|queue_num|排队号|<font color=red>String</font>|
|use_current_photo|摄像头获取到的头像 url地址|<font color=red>String</font>|

&ensp;&ensp;通过UDP推送的JSON字符串 如下：

    CALL_OUT# {
    	"command": "CALL_OUT#",
    	"category": "1",
    	"ipAndPort": "192.168.10.171:8899",
    	"datagram": {
    		"transact_biz_id": 16,
    		"id_card": "0213154516324542121",
    		"use_name": "张山",
    		"use_sex": "0",
    		"use_photo": "http://192.168.10.171:8899/image/YeWuRenYuan-1526004227560.jpg",
    		"use_birty": "2018年3月10日",
    		"use_addr": "上海市嘉定区",
    		"screen_num": "4",
    		"biz_name": "民事立案",
    		"queue_num": "1001",
    		"use_current_photo": "http://192.168.10.171:8899/image/YeWuRenYuan-1526004227573.jpg"
    	}
    }
