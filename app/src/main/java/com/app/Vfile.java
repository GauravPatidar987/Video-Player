package com.app;

public class Vfile
{
	String title;
	String url;
	String id;
	String bucket;
	public Vfile(String title, String url, String id, String bucket)
	{
		this.title = title;
		this.url = url;
		this.id = id;
		this.bucket = bucket;
	}

	public void setBucket(String bucket)
	{
		this.bucket = bucket;
	}

	public String getBucket()
	{
		return bucket;
	}

	public void setId(String id)
	{
		this.id = id;
	}

	public String getId()
	{
		return id;
	}


	public void setTitle(String title)
	{
		this.title = title;
	}

	public String getTitle()
	{
		return title;
	}

	public void setUrl(String url)
	{
		this.url = url;
	}

	public String getUrl()
	{
		return url;
	}}
