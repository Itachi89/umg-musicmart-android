package com.preludesys.umg.musicmart.util;

import com.preludesys.umg.musicmart.model.SalesRecordDetail;

/**
 * Created by varunsundaramoorthy on 5/8/14.
 */
public class NullPointerCheck  {

    public SalesRecordDetail nullPointerCheck(SalesRecordDetail x){

        if(x.getDate()==null)
            x.setDate("-");
        if(x.getItunesPreviousRank()==null)
            x.setItunesPreviousRank("-");
        if(x.getItunesRank()==null)
            x.setItunesRank("-");
        if(x.getLd()==null)
            x.setLd("-");
        if(x.getLw()==null)
            x.setLw("-");
        if(x.getUpdatedDate()==null)
            x.setUpdatedDate("-");
        if(x.getPercentage()==null)
            x.setPercentage(0);
        if(x.getRank()==null)
            x.setRank("-");
        return x;
    }
}
