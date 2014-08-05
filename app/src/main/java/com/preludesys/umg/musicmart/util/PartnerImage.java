package com.preludesys.umg.musicmart.util;

import com.preludesys.umg.musicmart.R;
import com.preludesys.umg.musicmart.model.SalesRecordDetail;

/**
 * Created by varunsundaramoorthy on 5/13/14.
 */
public class PartnerImage {

    public static int partnerImage(SalesRecordDetail salesRecordDetail){
        String partner = salesRecordDetail.getPartner();
        if("itunes".equals(partner))
            return (R.drawable.i_tunes);
        else if ("spotify".equals(partner))
            return (R.drawable.spotify);
        else if ("googleplay".equals(partner))
            return (R.drawable.googleplay);
        else if ("iradio".equals(partner))
            return (R.drawable.iradio);
        else if ("amazonmusic".equals(partner))
            return (R.drawable.amazon);
        return 0;
    }
}
