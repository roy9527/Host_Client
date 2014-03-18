package com.host.j;

import android.annotation.SuppressLint;
import android.os.Parcel;
import android.os.Parcelable;

@SuppressLint("ParcelCreator")
public class PushMsg implements Parcelable {

	private String type = "";
	private String content = "";
	private String action = "";
	private String title = "";
	private String img_url = "";
	
	public static final Parcelable.Creator<PushMsg> CREATOR = new Parcelable.Creator<PushMsg>() {

		@Override
		public PushMsg createFromParcel(Parcel source) {
			return new PushMsg(source);
		}

		@Override
		public PushMsg[] newArray(int size) {
			return new PushMsg[size];
		}
	};

	public PushMsg() {
	}

	private PushMsg(Parcel source) {
		readFromParcel(source);
	}

	@Override
	public int describeContents() {
		return 0;
	}

	@Override
	public void writeToParcel(Parcel dest, int flags) {
		dest.writeString(type);
		dest.writeString(content);
		dest.writeString(action);
		dest.writeString(title);
		dest.writeString(img_url);
	}

	public void readFromParcel(Parcel source) {
		type = source.readString();
		content = source.readString();
		action = source.readString();
		title = source.readString();
		img_url = source.readString();
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String getAction() {
		return action;
	}

	public void setAction(String action) {
		this.action = action;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getImg_url() {
		return img_url;
	}

	public void setImg_url(String img_url) {
		this.img_url = img_url;
	}
}
