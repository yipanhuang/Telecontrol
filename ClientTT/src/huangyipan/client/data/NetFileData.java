package huangyipan.client.data;

import java.io.File;

import android.os.Parcel;
import android.os.Parcelable;
import android.util.Log;

public class NetFileData implements Parcelable{
	private long fileSize=0;
	private String fileName="$error";
	private String filePath=".\\";
	private String fileSizeStr="0";
	private boolean isDireatory=false;
	private boolean isDisk=false;
	private String fileType="";

	private String fileModeifiedDate="1997-01-01 00:00:00";
	public NetFileData(String fileInfo,String filePath)
	{
		this.filePath=filePath;
		String []file=new String[20];
		file=fileInfo.split(">");
		
			this.fileName=file[0];
			this.fileModeifiedDate=file[1];
			this.fileSize=Long.parseLong(file[2]);
			if(file[3].equals("0"))
			{
				this.isDireatory=false;
				this.isDisk=false;
			}
			else
			{
				if(file[3].equals("1"))
				{
					this.isDireatory=true;
					this.isDisk=false;
				}
				else
				{
					this.isDireatory=false;
					this.isDisk=true;
				}
				
			}
			if(this.fileSize<1024)
			{
				this.fileSizeStr=fileSize+"B";
			}
			else if(this.fileSize<1024*1024)
			{
				this.fileSizeStr=fileSize/1024+"KB";
			}
			else if(this.fileSize<1024*1024*1024)
			{
				this.fileSizeStr=fileSize/(1024*1024)+"MB";
			}
			else 
			{
				this.fileSizeStr=fileSize/(1024*1024*1024)+"GB";
			}
			if(this.isDireatory==false&&this.isDisk==false&&this.fileName.indexOf(".")!=-1)
			{
				this.fileType=this.fileName.substring(this.fileName.lastIndexOf(".")+1);
			}
		
	}
	 public String getFileRealPath() {
			// TODO Auto-generated method stub
	    	String fileRealPath;
			if(filePath.equals("..."))
			{
				fileRealPath=fileName;
				
			}else
			{
				if(filePath.endsWith("/")|filePath.endsWith("\\"))
				{
					fileRealPath=filePath+fileName;
				}
				else
				{
					fileRealPath=filePath+File.separator+fileName;
				}
				
			}
			return fileRealPath;
		}
	public boolean isDisk() {
		return isDisk;
	}
	public void setDisk(boolean isDisk) {
		this.isDisk = isDisk;
	}
	public NetFileData(Parcel source) {
		// TODO Auto-generated constructor stub
		
		
		this.fileSize=source.readLong();
		this.fileName=source.readString();
		this.filePath=source.readString();
		this.fileSizeStr=source.readString();
		this.isDireatory = source.readByte() != 0;
		this.fileModeifiedDate=source.readString();
	}
	public String getFileName() {
		return fileName;
	}
	public String getFileType()
	{
		return fileType;
	}
	public void setFileName(String fileName) {
		this.fileName = fileName;
	}
	public String getFilePath() {
		return filePath;
	}
	public void setFilePath(String filePath) {
		this.filePath = filePath;
	}
	public String getFileSizeStr() {
		return fileSizeStr;
	}
	public void setFileSizeStr(String fileSizeStr) {
		this.fileSizeStr = fileSizeStr;
	}
	public boolean isDireatory() {
		return isDireatory;
	}
	public void setDireatory(boolean isDireatory) {
		this.isDireatory = isDireatory;
	}
	public String getFileModeifiedDate() {
		return fileModeifiedDate;
	}
	public void setFileModeifiedDate(String fileModeifiedDate) {
		this.fileModeifiedDate = fileModeifiedDate;
	}
	@Override
	public int describeContents() {
		// TODO Auto-generated method stub
		return 0;
	}
	@Override
	public void writeToParcel(Parcel dest, int flags) {
		// TODO Auto-generated method stub
		
		dest.writeLong(fileSize);
		dest.writeString(fileName);
		dest.writeString(filePath);
		dest.writeString(fileSizeStr);
		dest.writeByte((byte) (isDireatory ? 1 : 0)); 
		dest.writeString(fileModeifiedDate);
	}
	Parcelable.Creator<NetFileData> CREATOR=new Creator<NetFileData>() {

		@Override
		public NetFileData createFromParcel(Parcel source) {
			// TODO Auto-generated method stub
			return new NetFileData(source);
		}

		@Override
		public NetFileData[] newArray(int size) {
			// TODO Auto-generated method stub
			return new NetFileData[size];
		}
	};
}
