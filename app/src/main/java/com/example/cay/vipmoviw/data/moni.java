package com.example.cay.vipmoviw.data;

import com.example.cay.vipmoviw.bean.SubjectsBean;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Cay on 2017/2/4.
 */

public class Moni {
    public static List<SubjectsBean> hotMovieData() {
        List<SubjectsBean> list = new ArrayList<>();
        for (int i =0;i<21;i++) {
            SubjectsBean bean = new SubjectsBean();
            bean.setAct("靳东 / 陈乔恩 / 赵达 / 付枚 / 金泽灏");
            bean.setDirector(" 孔笙 / 周游 / 孙墨龙");
            bean.setmName("鬼吹灯之精绝古城" + i);
            bean.setNum(21);
            bean.setYear("2016");
            bean.setScore("8.2");
            bean.setCity("中国");
            bean.setSynopsis("《鬼吹灯》故事以一本家传的秘书残卷为引，讲述三位当代摸金校尉，为解开部族消失的千古之谜，利用风水秘术，解读天下大山大川的脉搏，寻找一处处失落在大地深处的龙楼宝殿。毕竟那些龙形虎藏、揭天拔地、倒海翻江的举动，都迵异庸俗，在离奇诡异的地下世界中，历史的神秘面纱正一层层地被揭开……");
            bean.setType("剧情 / 悬疑 / 惊悚");
            bean.setUrl("http://b.hiphotos.baidu.com/baike/w%3D268%3Bg%3D0/sign=00aae222b951f819f125044ce28f2dd0/ae51f3deb48f8c54ad56a12633292df5e0fe7f35.jpg");
            bean.setMovieUrl("http://171.214.200.133:8081/movie/1/116002/");
            bean.setOtherNmae("鬼吹灯网剧版 / Candle in the Tomb");
            list.add(bean);
        }
        return list;
    }
}
