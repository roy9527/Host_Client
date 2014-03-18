/*
 * This file is auto-generated.  DO NOT MODIFY.
 * Original file: /home/roy/workspace/TestHost/src/com/host/j/ICallBack.aidl
 */
package com.host.j;
public interface ICallBack extends android.os.IInterface
{
/** Local-side IPC implementation stub class. */
public static abstract class Stub extends android.os.Binder implements com.host.j.ICallBack
{
private static final java.lang.String DESCRIPTOR = "com.host.j.ICallBack";
/** Construct the stub at attach it to the interface. */
public Stub()
{
this.attachInterface(this, DESCRIPTOR);
}
/**
 * Cast an IBinder object into an com.host.j.ICallBack interface,
 * generating a proxy if needed.
 */
public static com.host.j.ICallBack asInterface(android.os.IBinder obj)
{
if ((obj==null)) {
return null;
}
android.os.IInterface iin = obj.queryLocalInterface(DESCRIPTOR);
if (((iin!=null)&&(iin instanceof com.host.j.ICallBack))) {
return ((com.host.j.ICallBack)iin);
}
return new com.host.j.ICallBack.Stub.Proxy(obj);
}
@Override public android.os.IBinder asBinder()
{
return this;
}
@Override public boolean onTransact(int code, android.os.Parcel data, android.os.Parcel reply, int flags) throws android.os.RemoteException
{
switch (code)
{
case INTERFACE_TRANSACTION:
{
reply.writeString(DESCRIPTOR);
return true;
}
case TRANSACTION_invokCallBack:
{
data.enforceInterface(DESCRIPTOR);
com.host.j.PushMsg _arg0;
if ((0!=data.readInt())) {
_arg0 = com.host.j.PushMsg.CREATOR.createFromParcel(data);
}
else {
_arg0 = null;
}
this.invokCallBack(_arg0);
reply.writeNoException();
if ((_arg0!=null)) {
reply.writeInt(1);
_arg0.writeToParcel(reply, android.os.Parcelable.PARCELABLE_WRITE_RETURN_VALUE);
}
else {
reply.writeInt(0);
}
return true;
}
}
return super.onTransact(code, data, reply, flags);
}
private static class Proxy implements com.host.j.ICallBack
{
private android.os.IBinder mRemote;
Proxy(android.os.IBinder remote)
{
mRemote = remote;
}
@Override public android.os.IBinder asBinder()
{
return mRemote;
}
public java.lang.String getInterfaceDescriptor()
{
return DESCRIPTOR;
}
@Override public void invokCallBack(com.host.j.PushMsg msg) throws android.os.RemoteException
{
android.os.Parcel _data = android.os.Parcel.obtain();
android.os.Parcel _reply = android.os.Parcel.obtain();
try {
_data.writeInterfaceToken(DESCRIPTOR);
if ((msg!=null)) {
_data.writeInt(1);
msg.writeToParcel(_data, 0);
}
else {
_data.writeInt(0);
}
mRemote.transact(Stub.TRANSACTION_invokCallBack, _data, _reply, 0);
_reply.readException();
if ((0!=_reply.readInt())) {
msg.readFromParcel(_reply);
}
}
finally {
_reply.recycle();
_data.recycle();
}
}
}
static final int TRANSACTION_invokCallBack = (android.os.IBinder.FIRST_CALL_TRANSACTION + 0);
}
public void invokCallBack(com.host.j.PushMsg msg) throws android.os.RemoteException;
}
