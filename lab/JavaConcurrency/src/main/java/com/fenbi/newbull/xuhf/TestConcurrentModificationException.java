/**
 * @(#)TestConcurrentModify.java, Jul 19, 2018.
 * <p>
 * Copyright 2018 fenbi.com. All rights reserved.
 * FENBI.COM PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.fenbi.newbull.xuhf;

import java.util.ArrayList;
import java.util.List;

/**
 * @author xuhongfeng
 */
public class TestConcurrentModificationException {

    public static void main(String[] args) {

        List<String> list = new ArrayList<String>();
        list.add("hello");
        list.add("world");
        list.add("!");

        for (String s : list) {
            if (s.equals("hello")) {
                list.remove(s);
            }
        }

        /*
        Iterator<String> itr = list.iterator();
        while (itr.hasNext()) {
            String s = itr.next();
            if (s.equals("hello")) {
//                list.remove(s);
                itr.remove();
            }
        }
        */
    }
}