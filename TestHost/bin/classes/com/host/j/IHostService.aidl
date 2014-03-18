package com.host.j;    
import com.host.j.ICallBack;
interface IHostService {     
    void registerCallBack(String packageName, ICallBack callBack);
    void unregisterCallBack(String packageName);
}