package com.lastartupsaas.workbench.domain;

import org.apache.commons.lang3.builder.ReflectionToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

/**
 * Author: alvin
 * Date: 2016-08-26
 */
public class BaseObject {

    @Override
    public String toString(){
        return ReflectionToStringBuilder.toString(this, ToStringStyle.SHORT_PREFIX_STYLE, false);
    }
}
