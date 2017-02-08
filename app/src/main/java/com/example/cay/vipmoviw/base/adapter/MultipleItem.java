package com.example.cay.vipmoviw.base.adapter;

import com.chad.library.adapter.base.entity.MultiItemEntity;

/**
 * https://github.com/CymChad/BaseRecyclerViewAdapterHelper
 */
public class MultipleItem implements MultiItemEntity {
    public static final int ONE = 1;
    public static final int TWO = 2;
    public static final int TRE = 3;
    private int itemType;
    private String title;
    private String ima_url_one;
    private String content_one;
    private String ima_url_two;
    private String content_two;
    private String ima_url_tre;
    private String content_tre;

    public MultipleItem(int itemType, String title, String ima_url_one, String content_one, String ima_url_two, String content_two, String ima_url_tre, String content_tre) {
        this.itemType = itemType;
        this.title = title;
        this.ima_url_one = ima_url_one;
        this.content_one = content_one;
        this.ima_url_two = ima_url_two;
        this.content_two = content_two;
        this.ima_url_tre = ima_url_tre;
        this.content_tre = content_tre;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getIma_url_one() {
        return ima_url_one;
    }

    public void setIma_url_one(String ima_url_one) {
        this.ima_url_one = ima_url_one;
    }

    public String getContent_one() {
        return content_one;
    }

    public void setContent_one(String content_one) {
        this.content_one = content_one;
    }

    public String getIma_url_two() {
        return ima_url_two;
    }

    public void setIma_url_two(String ima_url_two) {
        this.ima_url_two = ima_url_two;
    }

    public String getContent_two() {
        return content_two;
    }

    public void setContent_two(String content_two) {
        this.content_two = content_two;
    }

    public String getIma_url_tre() {
        return ima_url_tre;
    }

    public void setIma_url_tre(String ima_url_tre) {
        this.ima_url_tre = ima_url_tre;
    }

    public String getContent_tre() {
        return content_tre;
    }

    public void setContent_tre(String content_tre) {
        this.content_tre = content_tre;
    }

    public void setItemType(int itemType) {
        this.itemType = itemType;
    }

    @Override
    public int getItemType() {
        return itemType;
    }
}
