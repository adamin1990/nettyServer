package com.adamin.appserver.netty;

import io.netty.util.AttributeKey;

public interface Constants {
    AttributeKey<String> SN=AttributeKey.newInstance("adamin_sn");
    final String SP="$$";
}
