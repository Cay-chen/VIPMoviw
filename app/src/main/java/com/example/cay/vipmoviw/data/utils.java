package com.example.cay.vipmoviw.data;

import android.content.Context;

import com.example.cay.vipmoviw.R;

/**
 * Created by Cay on 2017/2/6.
 */

public class Utils {
    public static String country(int city, Context context) {
        String countryName =null;
        switch (city) {
            case 1:
                countryName = context.getResources().getString(R.string.CHA);
                break;
            case 2:
                countryName = context.getResources().getString(R.string.USA);
                break;
            case 3:
                countryName = context.getResources().getString(R.string.HK);
                break;
            case 4:
                countryName = context.getResources().getString(R.string.KOR);
                break;
            case 5:
                countryName = context.getResources().getString(R.string.JAP);
                break;
            case 6:
                countryName = context.getResources().getString(R.string.THA);
                break;
        }
        return  countryName;
    }
}
