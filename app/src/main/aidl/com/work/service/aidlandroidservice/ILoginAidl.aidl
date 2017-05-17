// ILoginAidl.aidl
package com.work.service.aidlandroidservice;
import com.work.service.aidlandroidservice.User; //引入java文件中的User对象，否则找不到类

interface ILoginAidl {

//in --客户端流向服务端，out--服务端流向客户端 inout--双向流通
//定义远端服务方法

boolean isLogin(in User user);//需要客户端传入

User getUser(User user);//客户端传入User对象给服务端，服务端返回

/**1.in:client只需接收服务端返回的数据，需要传输数据给service
* 2. out:client接收服务端数据，不需要传送数据给service
* 3.inout:client需要传送数据给sercive，并且需要处理返回数据**/

int add(int num1,int num2);
}
