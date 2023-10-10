package com.as.casovi_plivanja.data;

public class Blog {
    private final int mBlogImageId;
    private final String mBlogName;
    private final String mPostDescription;
    private final String mBlogPhone;
    private final String mBlogWeb;


    public Blog(int postImageId, String postName, String postDescription, String postPhone,
                String postWeb) {
        this.mBlogImageId = postImageId;
        this.mBlogName = postName;
        this.mPostDescription = postDescription;
        this.mBlogPhone = postPhone;
        this.mBlogWeb = postWeb;
    }

    public int getBlogImageId() {
        return mBlogImageId;
    }

    public String getBlogName() {
        return mBlogName;
    }

    public String getBlogDescription() {
        return mPostDescription;
    }

    public String getBlogPhone() {
        return mBlogPhone;
    }

    public String getBlogWeb() {
        return mBlogWeb;
    }
}
