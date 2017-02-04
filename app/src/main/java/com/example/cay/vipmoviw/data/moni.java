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
        for (int i =0;i<30;i++) {
            SubjectsBean bean = new SubjectsBean();
            bean.setAct("胡歌/刘亦菲/安以轩/刘品言/彭于晏"+i);
            bean.setDirector("李国立/吴锦源/梁胜权/麦贯之等" + i);
            bean.setmName("仙剑奇侠传" + i);
            bean.setNum(34);
            bean.setYear("2005");
            bean.setScore("6.9");
            bean.setSynopsis("《仙剑奇侠传》是根据大宇资讯同名RPG游戏改编的古装奇幻爱情剧，由上海唐人电影制作有限公司、云南电视台、上海影视有限公司等联合出品，李国立制作并导演，由胡歌、刘亦菲、安以轩、刘品言、彭于晏等主演。\n" +
                    "该剧讲述了渔村的店小二李逍遥与女娲的后人赵灵儿以及林家堡大小姐林月如等人之间的恩爱情仇、拯救苍生的古装神话故事。该剧是中国第一部由电玩游戏改编的电视剧，开创古装游戏改编剧新潮。");
            bean.setType("古装奇幻");
            bean.setUrl("http://f.hiphotos.baidu.com/baike/w%3D268%3Bg%3D0/sign=96cb82020b23dd542173a06ee932d4e3/562c11dfa9ec8a133effa986f503918fa1ecc0eb.jpg");
            list.add(bean);
        }
        return list;
    }
}
